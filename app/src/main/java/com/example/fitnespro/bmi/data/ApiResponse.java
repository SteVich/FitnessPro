package com.example.fitnespro.bmi.data;

import com.squareup.moshi.Json;

public class ApiResponse {

    @Json(name = "bmi")
    private BMI bmi;

    public BMI getBmi() {
        return bmi;
    }

    public void setBmi(BMI bmi) {
        this.bmi = bmi;
    }
}
