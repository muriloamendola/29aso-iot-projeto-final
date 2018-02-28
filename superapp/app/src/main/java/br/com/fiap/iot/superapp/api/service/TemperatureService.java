package br.com.fiap.iot.superapp.api.service;

import java.util.List;

import br.com.fiap.iot.superapp.model.MaxTemperature;
import br.com.fiap.iot.superapp.model.Temperature;
import br.com.fiap.iot.superapp.model.TemperatureList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by iggabsi on 24/02/18.
 */

public interface TemperatureService {

    @Headers({
            "Content-Type: application/json",
    })
    @GET("dev/temperatures/{timestamp}")
    Call<TemperatureList> temperatureList(@Path("timestamp") String timestamp);

    @PUT("dev/temperature/max")
    Call<Void> updateMaxTemperature(@Body MaxTemperature max);

    @GET("dev/temperature/max")
    Call<MaxTemperature> maxTemperature();

}
