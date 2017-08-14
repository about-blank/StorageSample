package com.vishal.storagesample;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!(externalStorageAvailable()))
            findViewById(R.id.external_storage_btn).setEnabled(false);

    }


    private boolean externalStorageAvailable() {

        String externalStorageState = Environment.getExternalStorageState();

        Log.v(getString(R.string.app_name), "External Storage state : " + externalStorageState);
        Log.v(getString(R.string.app_name), "External Storage Directory : " + Environment.getExternalStorageDirectory());
        Log.v(getString(R.string.app_name), "External Storage Public Directory (Pictures) : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        Log.v(getString(R.string.app_name), "External Storage Public Directory (Downloads) : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        Log.v(getString(R.string.app_name), "External Storage Public Directory (Documents) : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        Log.v(getString(R.string.app_name), "External Storage Public Directory (Movies) : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
        Log.v(getString(R.string.app_name), "External Storage Public Directory (Music) : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));

        if(!(externalStorageState.equals(Environment.MEDIA_UNKNOWN))
                && !(externalStorageState.equals(Environment.MEDIA_UNMOUNTABLE))
                && !(externalStorageState.equals(Environment.MEDIA_UNMOUNTED))
                ) {


            return true;
        }

        return false;
    }
    public void onClick(View view) {

        Log.v(getString(R.string.app_name), "onClick called ....");
        Intent i1;
        switch (view.getId()) {

            case R.id.shared_preferences_btn:
                i1 = new Intent(this, SharedPreferencesActivity.class);
                startActivity(i1);
                break;

            case R.id.internal_storage_btn:
                i1 = new Intent(this, InternalStorageActivity.class);
                startActivity(i1);
                break;

            case R.id.external_storage_btn:
                i1 = new Intent(this, InternalStorageActivity.class);
                i1.putExtra("external-storage", true);
                startActivity(i1);
                break;
        }
    }

}
