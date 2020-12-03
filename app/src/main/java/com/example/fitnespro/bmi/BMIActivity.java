package com.example.fitnespro.bmi;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnespro.R;
import com.example.fitnespro.bmi.data.ApiRequest;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class BMIActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private EditText waist;
    private EditText hip;
    private EditText age;
    private RadioButton isFemale;
    private RadioButton isMale;

    private final Moshi moshi = new Moshi.Builder().build();

    private static final String BASE_URL = "https://bmi.p.rapidapi.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        waist = findViewById(R.id.waist_input);
        hip = findViewById(R.id.hip_input);
        age = findViewById(R.id.age_input);
        isMale = findViewById(R.id.isMale);
        isFemale = findViewById(R.id.isFemale);

        findViewById(R.id.calculate_btn).setOnClickListener(view -> calculate());
    }

    private void calculate() {
        ApiRequest.Weight weightObject = new ApiRequest.Weight();
        weightObject.setWeight(Double.parseDouble(weight.getText().toString().trim()));

        ApiRequest.Height heightObject = new ApiRequest.Height();
        heightObject.setHeight(Double.parseDouble(height.getText().toString().trim()));

        ApiRequest request = new ApiRequest();
        request.setWeight(weightObject);
        request.setHeight(heightObject);
        request.setAge(Integer.parseInt(age.getText().toString().trim()));
        request.setHip(Double.parseDouble(hip.getText().toString().trim()));
        request.setWaist(Double.parseDouble(waist.getText().toString().trim()));

        String gender = "m";
        if (isFemale.isChecked()) {
            gender = "f";
        }
        request.setSex(gender);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        BMIApi api = retrofit.create(BMIApi.class);

        api.postProcedure(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    interface BMIApi {
        @POST("/")
        @Headers({"Content-Type: application/json",
                "x-rapidapi-key: da35921f87mshc33db2526e7de97p1fb04ejsn00851345d67e",
                "x-rapidapi-host: bmi.p.rapidapi.com"})
        Call<String> postProcedure(@Body ApiRequest request);

    }

}