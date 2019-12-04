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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.lang.ref.WeakReference;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.helpers.PermissionManager;

public class ScanFragment extends Fragment {

    private SurfaceView mSurfaceScanner;
    private TextView mTextScanResult;
    private CameraSource mCameraSource;
    private ScanViewModel mScanViewModel;

    private final String SCANNER_TAG = "SCANNER";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scan, container, false);

        mSurfaceScanner = root.findViewById(R.id.surface_scanner);
        mTextScanResult = root.findViewById(R.id.text_scanResult);

        //Set the initial result to an empty string.
        mTextScanResult.setText(String.format(getString(R.string.scan_result), ""));

        //Set the viewmodel.
        //I am currently working with a viewmodel to avoid passing the context I need to change the textfield.
        mScanViewModel = ViewModelProviders.of(this).get(ScanViewModel.class);

        //Create observer that updates the UI.
        final Observer<String> resultObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newValue) {
                mTextScanResult.setText(String.format(getString(R.string.scan_result), newValue));
            }
        };

        //Observe the scanned result and await instructions.
        mScanViewModel.getScannedValue().observe(this, resultObserver);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                    Log.d(SCANNER_TAG, ex.getMessage());
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
            new ScanResultAsyncTask(mScanViewModel, displayValue).execute();
        }
    }

    //This sets the newly scanned value to the text box.
    //TODO: Remove this bit because we will probably navigate to the plant page right after recognizing the scan result.
    public static class ScanResultAsyncTask extends AsyncTask<String, Void, String>{

        private WeakReference<ScanViewModel> mScanViewModel;
        private String mNewScanValue;

        public ScanResultAsyncTask(ScanViewModel model, String newScanValue){
            mScanViewModel = new WeakReference(model);
            mNewScanValue = newScanValue;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mScanViewModel.get().getScannedValue().setValue(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            return mNewScanValue;
        }
    }
}