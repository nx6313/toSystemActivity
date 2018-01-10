package com.hmj.nx6313.tosystemactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SystemActivity extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("toActivity")) {
            String actType = args.getString(0);
            if(actType.equals('appSystemSet')) {
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(Build.VERSION.SDK_INT >= 11){
                    localIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    localIntent.setData(Uri.fromParts("package", cordova.getActivity().getPackageName(), null));
                }else if(Build.VERSION.SDK_INT >= 9){
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", cordova.getActivity().getPackageName(), null));
                }else if(Build.VERSION.SDK_INT <= 8){
                    localIntent.setAction(Intent.ACTION_VIEW);
                    localIntent.setClassName("com.android.settings","com.android.setting.InstalledAppDetails");
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", cordova.getActivity().getPackageName());
                }
                cordova.getActivity().startActivity(localIntent);
                return true;
            }
        }
        return super.execute(action, args, callbackContext);
    }

}
