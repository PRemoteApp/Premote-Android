package com.example.finkacho.premote_android.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionManager {


    private Context context;
    private Activity activity;

    public PermissionManager(Context context,Activity activity){

        this.context = context;
        this.activity = activity;
    }
    public boolean permissionStatus(String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(int constantInt,String permission){
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                constantInt);
    }
}
