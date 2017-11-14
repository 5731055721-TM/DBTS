package com.wireless.g5.dbtsfragver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wireless.g5.dbtsfragver.content.StationContent.*;

import java.util.List;

public class StationAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Station> stations;
    private String type;

    public StationAdapter(Context context, List<Station> stations, String type) {
        this.mContext = context;
        this.stations = stations;
        this.type = type;
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Station station = stations.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            if (type == "list") convertView = layoutInflater.inflate(R.layout.station_list, null);
            else convertView = layoutInflater.inflate(R.layout.station_grid, null);
        }

        final TextView nameTextView = (TextView)convertView.findViewById(R.id.station_name);
        final TextView idTextView = (TextView)convertView.findViewById(R.id.station_id);

        nameTextView.setText(station.content);
        idTextView.setText(station.details);

        return convertView;
    }

}

