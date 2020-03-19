package edu.cascadia.mobas.photopoints.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.lang.ref.WeakReference;
import edu.cascadia.mobas.photopoints.ui.MainActivity;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;

/*This activity is not only used as a splash screen. It also instantiates the database.
* the onCreate method of the database is triggered so it can populate initial data.
* In the future we could expand this to sync data between FNCF servers and our app.
* It shows a spinner while the screen is loading.*/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new SyncDataBaseAsyncTask(this).execute();
    }

    public static class SyncDataBaseAsyncTask extends AsyncTask<Void, Void, Boolean>{

        private WeakReference<Activity> mActivity;
        private final String TAG = "Splash_DataSync";

        public SyncDataBaseAsyncTask(Activity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                PhotoPointsDatabase.getAppDatabase(mActivity.get().getApplicationContext());
            }
            catch(Exception ex){
                Log.d(TAG, ex.getMessage());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if(success){
                Intent main = new Intent(mActivity.get().getApplicationContext(), MainActivity.class);
                mActivity.get().startActivity(main);
            }
        }
    }
}
