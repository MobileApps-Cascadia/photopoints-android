package edu.cascadia.mobas.photopoints.helpers;

import android.content.Context;
import android.content.pm.PackageManager;
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

    private Context context;

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

    public static void setPermissions(PermissionType type, boolean permissionReceived) {
        permissions.put(PermissionType.FineLocation, permissionReceived);
    }

    public static boolean permissionGranted(PermissionType type){
        return permissions.get(type);
    }
}
