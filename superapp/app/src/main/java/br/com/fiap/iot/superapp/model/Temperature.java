package br.com.fiap.iot.superapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by iggabsi on 24/02/18.
 */

public class Temperature implements Serializable {

    @SerializedName("humidity")
    String humidity;

    @SerializedName("markingAt")
    String markingAt;

    @SerializedName("temperature")
    String temperature;

    public Temperature(String humidity, String markingAt, String temperature) {
        this.humidity = humidity;
        this.markingAt = markingAt;
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMarkingAt() {
        return markingAt;
    }

    public void setMarkingAt(String markingAt) {
        this.markingAt = markingAt;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
