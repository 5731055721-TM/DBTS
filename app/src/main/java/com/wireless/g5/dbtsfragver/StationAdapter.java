package com.wireless.g5.dbtsfragver;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wireless.g5.dbtsfragver.content.StationContent.*;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Station> stations;
    private String type;
    private ArrayList<Station> favorites = new ArrayList<>();

    public StationAdapter(Context context, ArrayList<Station> stations, String type) {
        this.mContext = context;
        this.stations = stations;
        this.type = type;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Station> getFavorites() {
        return favorites;
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
        final ImageView favImageView = (ImageView)convertView.findViewById(R.id.fav);

        nameTextView.setText(station.content);
        idTextView.setText(station.details);

        if(station.fav) favImageView.setImageResource(R.drawable.like);
        else favImageView.setImageResource(R.drawable.like_grey);
        favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (station.fav) {
                favImageView.setImageResource(R.drawable.like_grey);
                station.fav = false;
                FavoriteStationFragment.favStations.remove(station);
                notifyDataSetChanged();
            } else {
                favImageView.setImageResource(R.drawable.like);
                station.fav = true;
                FavoriteStationFragment.favStations.add(station);
                Log.e("add >> ","fav station");
                notifyDataSetChanged();
            }
            }
        });

        return convertView;
    }

}

