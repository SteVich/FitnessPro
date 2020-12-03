package com.example.fitnespro.bmi.data;

import com.squareup.moshi.Json;

public class BMI {

    @Json(name = "value")
    private String value;

    @Json(name = "status")
    private String status;

    @Json(name = "risk")
    private String risk;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
