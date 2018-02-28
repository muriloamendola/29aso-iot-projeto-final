package br.com.fiap.iot.superapp.event;

import java.util.List;

import br.com.fiap.iot.superapp.model.Temperature;
import br.com.fiap.iot.superapp.model.TemperatureList;

/**
 * Created by iggabsi on 26/02/18.
 */

public class ListTemperatureEvent {

    public TemperatureList temperatures;

    public ListTemperatureEvent(TemperatureList temperatures) {
        this.temperatures = temperatures;
    }

}
