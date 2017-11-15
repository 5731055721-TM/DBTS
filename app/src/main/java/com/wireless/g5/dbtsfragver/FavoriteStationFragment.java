package com.wireless.g5.dbtsfragver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FavoriteStationFragment extends Fragment {

    private static final String ARG_FAV_STATION = "param1";
    public static ArrayList<Station> favStations = new ArrayList<>();

    private boolean expand1=false, expand2=false;

    private ListView favListView;
    private ImageView favIcon;
    public static StationAdapter favAdapter;

    public FavoriteStationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        favStations.add(new Station("a","b","c",false));
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favListView = (ListView) view.findViewById(R.id.fav_list);
        favAdapter = new StationAdapter(getContext(), favStations, "list");
        favListView.setAdapter(favAdapter);

//        toggleExpand1 = (LinearLayout) view.findViewById(R.id.toggle_expand1);
//        toggleExpand2 = (LinearLayout) view.findViewById(R.id.toggle_expand2);

//        toggleExpand1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (expand1) {
//                    expand((LinearLayout)getActivity().findViewById(R.id.sukhumvit_layout));
//                    ((ImageView) getActivity().findViewById(R.id.expand_icon1)).setBackgroundResource(R.drawable.expand_less);
//                    expand1=false;
//                }
//                else {
//                    collapse((LinearLayout)getActivity().findViewById(R.id.sukhumvit_layout));
//                    ((ImageView) getActivity().findViewById(R.id.expand_icon1)).setBackgroundResource(R.drawable.expand_more);
//                    expand1=true;
//                }
//            }
//        });
//        toggleExpand2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (expand2) {
//                    expand(getActivity().findViewById(R.id.silom_layout));
//                    ((ImageView) getActivity().findViewById(R.id.expand_icon2)).setBackgroundResource(R.drawable.expand_less);
//                    expand2=false;
//                }
//                else {
//                    collapse(getActivity().findViewById(R.id.silom_layout));
//                    ((ImageView) getActivity().findViewById(R.id.expand_icon2)).setBackgroundResource(R.drawable.expand_more);
//                    expand2=true;
//                }
//            }
//        });

//        for (int a=0; a<ListViewFragment.sukhumvitAdapter.getStations().size(); a++) {
//            Station station = ListViewFragment.sukhumvitAdapter.getStations().get(a);
//            if(station.fav) addFav(station);
//            else removeFav(station);
//        }

        favListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            Station station = favStations.get(position);
            Log.v("station: ","\""+station.details.substring(0,1)+"\"");
            if(station.details.substring(0,1).equals("N") || station.details.substring(0,1).equals("E")) MainActivity.line = "sukhumvit";
            else if (station.details.substring(0,1).equals("W") || station.details.substring(0,1).equals("S")) MainActivity.line = "silom";
            else MainActivity.line = "cen";
            MainActivity.station = Integer.parseInt(station.id);
            startActivity(new Intent(getContext(), Popup.class));
            }
        });
    }

//    public static void addFav(View v) {
//        favStations.add("Clicked : "+clickCounter++);
//        adapter.notifyDataSetChanged();
//    }

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
