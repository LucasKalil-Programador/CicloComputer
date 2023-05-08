package br.com.ciclocomputer;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int SUCCESS_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPermissions(getApplicationContext(), this);
        setContentView(R.layout.activity_main);
    }

    // try get all Permissions

    public void getPermissions(Context context, Activity activity) {
        requestLocationPermission(context, activity);
    }

    // LocationPermission

    public void requestLocationPermission(Context context, Activity activity) {
        if(!checkLocationPermissions(context)) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_COARSE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed for calculate Speed, distance and others information's")
                        .setPositiveButton("ok", (dialog, which) ->
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[] { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION }, SUCCESS_PERMISSION)
                        ).setNegativeButton("cancel", (dialog, which) -> dialog.dismiss()).create().show();

            } else {

                ActivityCompat.requestPermissions(this, new String[] { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION }, SUCCESS_PERMISSION);
            }
        }
    }

    public static boolean checkLocationPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // Permissions Listener

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if (requestCode == SUCCESS_PERMISSION) {
                if (grantResults.length > i && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " Permission GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, permissions[i] + "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
