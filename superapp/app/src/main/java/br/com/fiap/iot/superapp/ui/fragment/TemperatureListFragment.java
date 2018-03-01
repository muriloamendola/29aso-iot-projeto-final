package br.com.fiap.iot.superapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.iot.superapp.R;
import br.com.fiap.iot.superapp.adapter.TemperatureAdapter;
import br.com.fiap.iot.superapp.model.Temperature;
import br.com.fiap.iot.superapp.model.TemperatureList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iggabsi on 27/02/18.
 */

public class TemperatureListFragment extends Fragment {

    @BindView(R.id.temperature_list)
    RecyclerView recyclerView;

    private List<Temperature> temperatures = new ArrayList<Temperature>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temperature_list, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(new TemperatureAdapter(temperatures));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

    public void loadTemperatureList(TemperatureList temperatures) {
        this.temperatures.addAll(temperatures.getTemperatureList());
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
