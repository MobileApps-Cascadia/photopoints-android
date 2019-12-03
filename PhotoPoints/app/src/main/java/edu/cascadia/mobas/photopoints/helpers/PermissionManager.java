package edu.cascadia.mobas.photopoints.helpers;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import java.util.HashMap;

/*
 * This class helps managing the permissions for the app.
 * It keeps a HashMap of the permissions based on their type.
 * It also processes incoming permission results and maps them to the correct key.
 */
public class PermissionManager {

    public enum PermissionType {
        FineLocation,   //0
        Camera   //1
    }

    private static HashMap<PermissionType, Boolean> permissions = new HashMap<>();

    /*
     * Accepts the request code and the results to set the correct permissions and add them to the
     * hashmap if they do not exist yet.
     * */
    public static void processPermissionResult(int requestCode, int[] grantResults){

        PermissionType type = PermissionType.values()[requestCode];

        //No permissions received, so set the permissions for that one to false and head out.
        if (grantResults.length <= 0){
            setPermissions(type, false);
            return;
        }

        switch (type) {
            case Camera:
                setPermissions(PermissionType.Camera, grantResults[0] == PackageManager.PERMISSION_GRANTED);
            case FineLocation:
                setPermissions(PermissionType.FineLocation, grantResults[0] == PackageManager.PERMISSION_GRANTED);
            default:
                //nothing
        }
    }

    //Checks the permission in the app context.
    //The user can change this while using the app so this should always be called on the creation of the activity.
    public static boolean checkPermission(Context context, String permission, PermissionType permissionType){
        boolean permissionGranted = ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
        setPermissions(permissionType, permissionGranted);
        return permissionGranted;
    }

    //Sets the permission.
    public static void setPermissions(PermissionType type, boolean permissionReceived) {
        permissions.put(type, permissionReceived);
    }

    //Returns the permissionresult for the user.
    public static boolean permissionIsGranted(PermissionType type){
        return permissions.get(type);
    }
}
