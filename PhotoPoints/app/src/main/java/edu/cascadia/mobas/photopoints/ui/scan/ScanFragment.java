package edu.cascadia.mobas.photopoints.ui.scan;

import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.lang.ref.WeakReference;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.helpers.PermissionManager;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;
import edu.cascadia.mobas.photopoints.ui.upload.UploadPhotoPointDataFragment;

public class ScanFragment extends Fragment {

    //TODO: This will have to change to an authentication model. But for the primary prototyping purposes, this is semi-hardcoded.
    private boolean mUserIsRegistered;

    private SurfaceView mSurfaceScanner;
    private TextView mTextScanResult;
    private CameraSource mCameraSource;

    private static final String TAG = "SCANNER";

    /*Another sucky part, so I am open to suggestions. This boolean is used to check if the scanner found a real QR Code and managed to navigate.
    * This was my work-around to the issue of the AsyncTask being triggered multiple times (Scanner keeps on scanning even after having identified a QR Code).
    * Because the scanner was triggered multiple times, the controller tried to navigate multiple times.
    * Every task that passes after the first task would crash, because the controller would not exist anymore.
    * */
    private static Boolean mNavigated;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scan, container, false);
        mNavigated = false;

        mSurfaceScanner = root.findViewById(R.id.surface_scanner);
        mTextScanResult = root.findViewById(R.id.text_scanResult);

        //TODO: Cache user and check that property.
        Bundle args = getArguments();
        if (args != null){
            mUserIsRegistered = args.getBoolean("Registered");
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!mUserIsRegistered){
            NavController nav = Navigation.findNavController(view);
            nav.navigate(R.id.action_navigation_scan_to_navigation_signin2);
            return;
        }

        //Check if the user has granted permission to use the camera.
        //If we have permission, we start the camera. If not, we request permission.
        if(PermissionManager.checkPermission(getContext(), Manifest.permission.CAMERA, PermissionManager.PermissionType.Camera)){
            startCamera();
        }
        else{
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, PermissionManager.PermissionType.Camera.ordinal());
        }
    }

    //Starts the camera on the surface view.
    private void startCamera(){

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getContext()).build();
        barcodeDetector.setProcessor(new MyBarcodeDetector());

        mCameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1024, 768)
                .setAutoFocusEnabled(true)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .build();

        mSurfaceScanner.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    mCameraSource.start(mSurfaceScanner.getHolder());
                }
                catch(Exception ex){
                    Log.d(TAG, ex.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCameraSource.stop();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionManager.processPermissionResult(requestCode, grantResults);

        if(PermissionManager.permissionIsGranted(PermissionManager.PermissionType.Camera)){
            /*Time to do some funky stuff. The surface is already created when the view is loaded,
            * so when granting permissions, the surface would not show the first time.
            * The user would have to navigate back and forth to see the camera. We don't really want that...
            * A solution to this is removing the current surface scanner, and building a new one.
             */
            SurfaceView newSurfaceLayout = new SurfaceView(getContext());
            LinearLayout scanLayout = getActivity().findViewById(R.id.layout_scannerSurface);
            scanLayout.removeView(mSurfaceScanner);
            mSurfaceScanner = newSurfaceLayout;
            scanLayout.addView(newSurfaceLayout);
            startCamera();
        }
        else{
            mSurfaceScanner.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), getActivity().getText(R.string.camera_start_error), Toast.LENGTH_SHORT).show();
        }
    }

    class MyBarcodeDetector implements Detector.Processor{
        @Override
        public void release() {

        }

        //Receives the detections from the scanner and executes a task that puts them in a text view.
        @Override
        public void receiveDetections(Detector.Detections detections) {
            SparseArray<Barcode> codes = detections.getDetectedItems();

            if(codes == null || codes.size() == 0){
                return;
            }

            //TODO: Navigate to the plant page instead of displaying the result.
            String displayValue = codes.valueAt(0).displayValue;

            if(displayValue.equals("")){
                return;
            }

            new ScanResultAsyncTask(getFragmentManager().getPrimaryNavigationFragment(), mTextScanResult).execute(displayValue);
        }
    }

    //This sets the newly scanned value to the text box.
    //TODO: Remove this bit because we will probably navigate to the plant page right after recognizing the scan result.
    public static class ScanResultAsyncTask extends AsyncTask<String, Void, Integer>{

        private WeakReference<Fragment> mFragment;
        private WeakReference<TextView> mTextScanResult;

        public ScanResultAsyncTask(Fragment fragment, TextView textScanResult) {
            this.mFragment = new WeakReference<>(fragment);
            this.mTextScanResult = new WeakReference<>(textScanResult);
        }

        @Override
        protected void onPostExecute(Integer photoPointID) {
            super.onPostExecute(photoPointID);

            if(photoPointID != 0){

                if(mNavigated){
                   return;
                }

                mNavigated = true;
                Bundle bundle = new Bundle();
                bundle.putInt(UploadPhotoPointDataFragment.PHOTOPOINT_ID, photoPointID);

                Navigation.findNavController(mFragment.get().getView())
                        .navigate(R.id.action_navigation_scan_to_navigation_uploadPhotoPointData, bundle);

            }else{
                mTextScanResult.get().setText(mFragment.get().getString(R.string.qr_code_not_found));
            }
        }

        @Override
        protected Integer doInBackground(String... strings) {
            return new PhotoPointsRepository(mFragment.get().getContext()).getIDByQRCode(strings[0]);
        }
    }
}