package com.example.interview;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    public String BASE_URL="http://172.17.239.137:1235";
    @FormUrlEncoded
    @POST("/login")
    Call<authres> signin(@Field("user") String user, @Field("pass") String pass);
}
