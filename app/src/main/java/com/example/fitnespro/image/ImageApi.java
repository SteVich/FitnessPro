package com.example.fitnespro.image;

import com.example.fitnespro.image.data.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

interface ImageApi {

    @GET("api/v2/exerciseimage/")
    @Headers({"Content-Type: application/json",
            "Authorization: Token af23e51228bdf9a0b85092bf365455a9f702c60b"})
    Call<ApiResponse> getImageObject();
}