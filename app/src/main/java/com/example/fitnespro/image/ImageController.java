package com.example.fitnespro.image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitnespro.image.data.ApiResponse;
import com.example.fitnespro.image.data.Results;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ImageController implements Callback<ApiResponse> {

    private static final String BASE_URL = "https://wger.de/";

    List<String> images = new ArrayList<>();

    private MutableLiveData<List<String>> liveData = new MutableLiveData<>();

    LiveData<List<String>> getData() {
        return liveData;
    }

    private final Moshi moshi = new Moshi.Builder().build();

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        ImageApi api = retrofit.create(ImageApi.class);

        Call<ApiResponse> call = api.getImageObject();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        if (response.isSuccessful()) {
            ApiResponse body = response.body();

            images = body.getResults().stream()
                    .map(Results::getImage)
                    .limit(3)
                    .collect(Collectors.toList());

            liveData.setValue(images);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        t.printStackTrace();
    }
}