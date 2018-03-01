package br.com.fiap.iot.superapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.com.fiap.iot.superapp.R;
import br.com.fiap.iot.superapp.api.WebClient;
import br.com.fiap.iot.superapp.event.ListTemperatureEvent;
import br.com.fiap.iot.superapp.event.MaxTemperatureEvent;
import br.com.fiap.iot.superapp.event.TemperatureEvent;
import br.com.fiap.iot.superapp.model.MaxTemperature;
import br.com.fiap.iot.superapp.model.Temperature;
import br.com.fiap.iot.superapp.ui.fragment.TemperatureListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.value_max_temperature)
    TextView textMaxTemperature;

    private MaxTemperature maxTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        new WebClient().getMaxTemperature();

        ButterKnife.bind(this);
    }

    @Subscribe
    public void eventError(Throwable throwable) {
        Toast.makeText(this, "Não foi possível se conectar ao servidor.", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.search_temperature_by_day)
    public void searchForTemperatures() {
        Intent intent = new Intent(this, TemperatureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("MAX", this.maxTemperature);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Subscribe
    public void updateTemperatureEvent(TemperatureEvent temperatureEvent) {
        textMaxTemperature.setText(temperatureEvent.temperature.getTemperature());
        Toast.makeText(this, "Temperatura máxima atualizada com sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void maxTemperatureEvent(MaxTemperatureEvent maxTemperatureEvent) {
        maxTemperature = maxTemperatureEvent.maxTemperature;
        textMaxTemperature.setText(maxTemperatureEvent.maxTemperature.getMaxTemperature());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick(R.id.update_temperature)
    public void updateMaxTemperature() {
        String maxTemperatureValue = textMaxTemperature.getText().toString().trim();
        MaxTemperature maxTemperature = new MaxTemperature(maxTemperatureValue);
        this.maxTemperature = maxTemperature;
        new WebClient().updateMaxTemperature(maxTemperature);
    }
}
