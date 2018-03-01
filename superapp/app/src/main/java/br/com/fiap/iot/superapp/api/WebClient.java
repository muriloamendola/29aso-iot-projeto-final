package br.com.fiap.iot.superapp.api;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.fiap.iot.superapp.api.service.TemperatureService;
import br.com.fiap.iot.superapp.event.ListTemperatureEvent;
import br.com.fiap.iot.superapp.event.MaxTemperatureEvent;
import br.com.fiap.iot.superapp.event.TemperatureEvent;
import br.com.fiap.iot.superapp.model.MaxTemperature;
import br.com.fiap.iot.superapp.model.Temperature;
import br.com.fiap.iot.superapp.model.TemperatureList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iggabsi on 24/02/18.
 */

public class WebClient {

    private final String BASE_URL = "https://15m3sva997.execute-api.us-east-1.amazonaws.com/";

    public WebClient() { }

    public void getTemperatures(String timestamp) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TemperatureService service = client.create(TemperatureService.class);

        Call<TemperatureList> request = service.temperatureList(timestamp);

        request.enqueue(new Callback<TemperatureList>() {
            @Override
            public void onResponse(Call<TemperatureList> call, Response<TemperatureList> response) {
                TemperatureList temperatures = response.body();
                EventBus.getDefault().post(new ListTemperatureEvent(temperatures));
            }
            @Override
            public void onFailure(Call<TemperatureList> call, Throwable t) {
                EventBus.getDefault().post(new Throwable(t));
            }
        });

    }

    public void updateMaxTemperature(final MaxTemperature maxTemperature) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TemperatureService service = client.create(TemperatureService.class);

        Call<Void> request = service.updateMaxTemperature(maxTemperature);

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Temperature maxTemperatureObject = new Temperature(null, null, maxTemperature.getMaxTemperature());
                EventBus.getDefault().post(new TemperatureEvent(maxTemperatureObject));
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                EventBus.getDefault().post(new Throwable(t));
            }
        });

    }

    public void getMaxTemperature() {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TemperatureService service = client.create(TemperatureService.class);

        Call<MaxTemperature> request = service.maxTemperature();

        request.enqueue(new Callback<MaxTemperature>() {
            @Override
            public void onResponse(Call<MaxTemperature> call, Response<MaxTemperature> response) {
                MaxTemperature maxTemperature = response.body();
                EventBus.getDefault().post(new MaxTemperatureEvent(maxTemperature));
            }

            @Override
            public void onFailure(Call<MaxTemperature> call, Throwable t) {
                EventBus.getDefault().post(new Throwable(t));
            }
        });
    }
}
