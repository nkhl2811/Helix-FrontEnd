package com.learnprogramming.helix_ps1_proj;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CreateEventAPI {

    @FormUrlEncoded
    @POST("createEvent")
    Call<CreateEventResponse> createEvent(
            @Header("Token") String token,
            @Field("desc") String desc,
            @Field("name") String name,
            @Field("time") String time,
            @Field("genre") String genre,
            @Field("picture") String picture,
            @Field("date") String date
    );
}
