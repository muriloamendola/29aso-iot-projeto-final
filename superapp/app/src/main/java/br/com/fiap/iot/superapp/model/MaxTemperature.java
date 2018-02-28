package br.com.fiap.iot.superapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by iggabsi on 26/02/18.
 */

public class MaxTemperature implements Serializable {

    @SerializedName("max")
    String maxTemperature;

    public MaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
