package br.com.fiap.iot.superapp.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Timestamp(System.currentTimeMillis()));
        
        new WebClient().getTemperatures(timeStamp);

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
