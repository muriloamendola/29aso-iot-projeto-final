package br.com.fiap.iot.superapp.event;

import br.com.fiap.iot.superapp.model.Temperature;

/**
 * Created by iggabsi on 27/02/18.
 */

public class TemperatureEvent {

    public Temperature temperature;

    public TemperatureEvent(Temperature temperature) { this.temperature = temperature; }
}
