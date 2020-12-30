package com.icehax.stitch;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.icehax.stitch.api.KurapikaAPI;
import com.icehax.stitch.models.NewAppBody;
import com.icehax.stitch.models.NewAppResponse;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Service snitches to our backend about apks that have been installed or updated on our device.
 * It relies on things like the package broadcast receiver to give it info and it
 * handles the actual snitching (i.e. talks to our backend)
 */
public class        SnitchService extends JobIntentService {
    private static final String TAG = "SnitchService";
    private KurapikaAPI kurapikaApi;


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (intent == null) {
            Log.i(TAG, "Skipping due to NULL intent");
            return;
        }

        Log.i(TAG, "Handling intent: " + intent.getStringExtra("appId"));
        NewAppBody body = new NewAppBody();
        body.setAppId(intent.getStringExtra("appId"));
        body.setOs("android");
        body.setDeviceCodeName("walleye"); // TODO: get this dynamically
        body.setAppVersion(intent.getStringExtra("appVersion"));

        Response<NewAppResponse> response;
        try {
            response = this.kurapikaApi.newApp(body).execute();
        } catch (IOException e) {
            Log.i(TAG, "ERROR! Exception on request");
            e.printStackTrace();
            return;
        }
        if (response.code() != 200) {
            Log.i(TAG, "ERROR! Request got a failure response code: " + response.code());
            return;
        }
        Log.i(TAG, "Response message:" + response.message());

    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Creating service");
        super.onCreate();

        // retrofit api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://138.68.42.219:8081/") // TODO: get from config file
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        kurapikaApi = retrofit.create(KurapikaAPI.class);

        Toast.makeText(this, "snitch service created", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
