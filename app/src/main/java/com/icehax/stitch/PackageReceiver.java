package com.icehax.stitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.JobIntentService;


public class PackageReceiver extends BroadcastReceiver {

    private static final String TAG = "PackageReceiver";

    private Context context;


    public PackageReceiver(Context context) {
        this.context = context;
    }

    private void snitch(String packageName) {
        // start service
        Log.i(TAG, "Snitching....");
        Intent serviceIntent = new Intent(this.context, SnitchService.class);
        serviceIntent.putExtra("appId", packageName);
        JobIntentService.enqueueWork(this.context, SnitchService.class, 1, serviceIntent);
        //this.context.(serviceIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Received broadcast");
        String action = intent.getAction();

        Uri data = intent.getData();
        String packageName = "";
        if (data != null) {
            packageName = data.getSchemeSpecificPart();
        }

        Log.i(TAG, "-- onReceive action=" + action + " | package=" + packageName);

        if (TextUtils.equals(action, Intent.ACTION_PACKAGE_ADDED)) {
            Log.i(TAG, "Package added" + packageName);
            snitch(packageName);
        } else if (TextUtils.equals(action, Intent.ACTION_PACKAGE_REPLACED)) {
            Log.i(TAG, "Package replaced " + packageName);
            snitch(packageName);
        }
    }

    public void register() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        filter.addDataScheme("package");
        this.context.registerReceiver(this, filter);
    }
}
