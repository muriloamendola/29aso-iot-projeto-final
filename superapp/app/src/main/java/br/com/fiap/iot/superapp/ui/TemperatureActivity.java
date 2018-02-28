package br.com.fiap.iot.superapp.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.com.fiap.iot.superapp.R;
import br.com.fiap.iot.superapp.api.WebClient;
import br.com.fiap.iot.superapp.event.ListTemperatureEvent;
import br.com.fiap.iot.superapp.ui.fragment.TemperatureListFragment;

/**
 * Created by iggabsi on 27/02/18.
 */

public class TemperatureActivity extends AppCompatActivity {


    private TemperatureListFragment temperatureListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        EventBus.getDefault().register(this);

        new WebClient().getTemperatures("1519441200000");

        temperatureListFragment = new TemperatureListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_principal, temperatureListFragment);
        transaction.commit();

    }

    @Subscribe
    public void getTemperatureList(ListTemperatureEvent listTemperatureEvent) {
        temperatureListFragment.loadTemperatureList(listTemperatureEvent.temperatures);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
