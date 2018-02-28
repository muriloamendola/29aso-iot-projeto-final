package br.com.fiap.iot.superapp.event;


import br.com.fiap.iot.superapp.model.MaxTemperature;

/**
 * Created by iggabsi on 27/02/18.
 */

public class MaxTemperatureEvent {

    public MaxTemperature maxTemperature;

    public MaxTemperatureEvent(MaxTemperature maxTemperature) { this.maxTemperature = maxTemperature; }

}
