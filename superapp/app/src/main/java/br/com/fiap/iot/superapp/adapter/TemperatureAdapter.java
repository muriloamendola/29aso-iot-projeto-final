package br.com.fiap.iot.superapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.fiap.iot.superapp.R;
import br.com.fiap.iot.superapp.model.Temperature;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iggabsi on 27/02/18.
 */

public class TemperatureAdapter extends RecyclerView.Adapter {

    private List<Temperature> temperatures;

    public TemperatureAdapter(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperature_item_a, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        Temperature temperature = temperatures.get(position);
        viewHolder.binder(temperature);
    }

    @Override
    public int getItemCount() {
        return temperatures.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.temperature_value)
        TextView temperatureValue;
        @BindView(R.id.marking_date)
        TextView markingDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void binder(Temperature temperature) {
            this.temperatureValue.setText(temperature.getTemperature() + " graus");
        }

    }
}
