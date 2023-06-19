package br.com.ciclocomputador.Permission;


import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

/**
 * Helper class for requesting location permissions in an Android application.
 */
public class LocationPermissionManager {

    /**
     * Array of required location permissions.
     */
    public static final String[] NEEDED_LOCATION_PERMISSIONS = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION };

    /**
     * Request code for location permission successfully.
     */
    public static final int LOCATION_PERMISSION_SUCCESS = 1001;

    /**
     * Requests location permission from the user.
     * @param context The application context.
     * @param activity The current activity.
     * @return True if the permissions are already granted, or if the user grants them; false otherwise.
     */
    public static boolean requestLocationPermission(Context context, Activity activity) {
        if(!checkLocationPermissions(context)) {
            if(shouldShowRequestPermissionRationale(activity)) {
                new AlertDialog.Builder(activity)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed for calculate Speed, distance and others information's")
                        .setPositiveButton("ok", (dialog, which) ->
                                ActivityCompat.requestPermissions(activity, NEEDED_LOCATION_PERMISSIONS, LOCATION_PERMISSION_SUCCESS)
                        )
                        .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(activity, NEEDED_LOCATION_PERMISSIONS, LOCATION_PERMISSION_SUCCESS);
            }
            return checkLocationPermissions(context);
        } else {
            return true;
        }
    }

    /**
     * Checks if the rationale for requesting permissions should be shown.
     * @param activity The current activity.
     * @return True if rationale should be shown, false otherwise.
     */
    private static boolean shouldShowRequestPermissionRationale(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_COARSE_LOCATION);
    }

    /**
     * Checks if location permissions are granted.
     * @param context The application context.
     * @return True if both location permissions are granted, false otherwise.
     */
    public static boolean checkLocationPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Handles the result of permission request.
     * @param activity The current activity.
     * @param requestCode The request code for permissions.
     * @param permissions The requested permissions.
     * @param grantResults The grant results for the requested permissions.
     */
    public static void defaultOnRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_SUCCESS) {
            for (int i = 0; i < grantResults.length; i++) {
                boolean requestResult = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                String text = permissions[i] + (requestResult? " Permission GRANTED": " Permission DENIED");

                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
