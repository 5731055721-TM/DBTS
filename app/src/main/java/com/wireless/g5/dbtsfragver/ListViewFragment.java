package com.wireless.g5.dbtsfragver;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wireless.g5.dbtsfragver.content.StationContent;
import com.wireless.g5.dbtsfragver.content.StationContent.Station;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    private ArrayList<Station> sukhumvit_stations = StationContent.STATIONS_SUKHUMVIT;
    private ArrayList<Station> silom_stations = StationContent.STATIONS_SILOM;

    private boolean expand1=false, expand2=false, liked=false;

    private ListView sukhumvitListView,silomListView;
    private ImageView like;
    public static StationAdapter sukhumvitAdapter, silomAdapter;
    private LinearLayout toggleExpand1, toggleExpand2;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sukhumvitListView = (ListView) view.findViewById(R.id.sukhumvit_list);
        silomListView = (ListView) view.findViewById(R.id.silom_list);

        sukhumvitAdapter = new StationAdapter(getContext(), sukhumvit_stations,"list");
        sukhumvitListView.setAdapter(sukhumvitAdapter);
        silomAdapter = new StationAdapter(getContext(), silom_stations, "list");
        silomListView.setAdapter(silomAdapter);

        toggleExpand1 = (LinearLayout) view.findViewById(R.id.toggle_expand1);
        toggleExpand2 = (LinearLayout) view.findViewById(R.id.toggle_expand2);

        like = (ImageView) view.findViewById(R.id.fav);

        toggleExpand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (expand1) {
                expand((LinearLayout)getActivity().findViewById(R.id.sukhumvit_layout));
                ((ImageView) getActivity().findViewById(R.id.expand_icon1)).setBackgroundResource(R.drawable.expand_less);
                expand1=false;
            }
            else {
                collapse((LinearLayout)getActivity().findViewById(R.id.sukhumvit_layout));
                ((ImageView) getActivity().findViewById(R.id.expand_icon1)).setBackgroundResource(R.drawable.expand_more);
                expand1=true;
            }
            }
        });
        toggleExpand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (expand2) {
                expand(getActivity().findViewById(R.id.silom_layout));
                ((ImageView) getActivity().findViewById(R.id.expand_icon2)).setBackgroundResource(R.drawable.expand_less);
                expand2=false;
            }
            else {
                collapse(getActivity().findViewById(R.id.silom_layout));
                ((ImageView) getActivity().findViewById(R.id.expand_icon2)).setBackgroundResource(R.drawable.expand_more);
                expand2=true;
            }
            }
        });

        sukhumvitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Station station = sukhumvit_stations.get(position);
                if(station.id == "8") MainActivity.line = "cen";
                else MainActivity.line = "sukhumvit";
                MainActivity.station = Integer.parseInt(station.id);
                startActivity(new Intent(getContext(), Popup.class));
            }
        });
        silomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Station station = silom_stations.get(position);
                if(station.id == "2") MainActivity.line = "cen";
                else MainActivity.line = "silom";
                MainActivity.station = Integer.parseInt(station.id);
                startActivity(new Intent(view.getContext(),Popup.class));
            }
        });
    }

    public void addFav(Station station) {
        FavoriteStationFragment.favStations.add(station);
        FavoriteStationFragment.favAdapter.notifyDataSetChanged();
    }

    public void removeFav(Station staton) {
        FavoriteStationFragment.favStations.remove(staton);
        FavoriteStationFragment.favAdapter.notifyDataSetChanged();
    }

    public void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}

