package com.example.fitnespro.bmi.data;

import com.squareup.moshi.Json;

public class ApiRequest {

    @Json(name = "weight")
    private Weight weight;

    @Json(name = "height")
    private Height height;

    @Json(name = "sex")
    private String sex;

    @Json(name = "age")
    private int age;

    @Json(name = "waist")
    private double waist;

    @Json(name = "hip")
    private double hip;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public static class Weight {
        @Json(name = "value")
        private double value;

        @Json(name = "unit")
        private String unit = "kg";

        public double getWeight() {
            return value;
        }

        public void setWeight(double weight) {
            this.value = weight;
        }

        public String getUnit() {
            return unit;
        }
    }

    public static class Height {
        @Json(name = "value")
        private double value;

        @Json(name = "unit")
        private String unit = "cm";

        public double getHeight() {
            return value;
        }

        public void setHeight(double height) {
            this.value = height;
        }

        public String getUnit() {
            return unit;
        }
    }
}
