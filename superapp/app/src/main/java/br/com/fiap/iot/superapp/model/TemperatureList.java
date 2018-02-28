package br.com.fiap.iot.superapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iggabsi on 27/02/18.
 */

public class TemperatureList implements Serializable {

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    @SerializedName("temperatures")
    private List<Temperature> temperatureList;

    public TemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

}
