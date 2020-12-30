package com.icehax.stitch.api;

import com.icehax.stitch.models.NewAppBody;
import com.icehax.stitch.models.NewAppResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface KurapikaAPI {
    @POST("new_app")
    Call<NewAppResponse> newApp(@Body NewAppBody body);
}
