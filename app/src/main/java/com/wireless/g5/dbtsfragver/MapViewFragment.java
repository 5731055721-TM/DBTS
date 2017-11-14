package com.wireless.g5.dbtsfragver;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapViewFragment extends Fragment {

    private int x,y;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        map = (ImageView) view.findViewById(R.id.map);
        map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.v("x >>", motionEvent.getX() + "");
                Log.v("y >>", motionEvent.getY() + "\n");
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                if(x>457&&x<578&&y>230&&y<278) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 1;
                    startActivity(new Intent(view.getContext(),Popup.class));
                } else if (x>457&&x<606&&y>290&&y<330) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 2;
                    startActivity(new Intent(view.getContext(),Popup.class));
                } else if (x>454&&x<556&&y>340&&y<380) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 3;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>448&&x<606&&y>392&&y<439) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 4;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>451&&x<649&&y>454&&y<500) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 5;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>451&&x<600&&y>504&&y<553) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 6;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>451&&x<612&&y>559&&y<593) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 7;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>476&&x<535&&y>646&&y<757) {
                    MainActivity.line = "cen";
                    MainActivity.station = 8;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>528&&x<593&&y>602&&y<704) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 9;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>603&&x<658&&y>609&&y<701) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 10;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>677&&x<736&&y>646&&y<698) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 11;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>705&&x<801&&y>683&&y<738) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 12;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>726&&x<903&&y>745&&y<791) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 13;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>742&&x<890&&y>800&&y<843) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 14;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>764&&x<903&&y>856&&y<887) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 15;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>779&&x<968&&y>905&&y<939) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 16;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>801&&x<937&&y>952&&y<998) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 17;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>822&&x<983&&y>1004&&y<1038) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 18;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>847&&x<1002&&y>1057&&y<1097) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 19;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>866&&x<1017&&y>1103&&y<1143) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 20;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>881&&x<1017&&y>1146&&y<1193) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 21;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>903&&x<1051&&y>1199&&y<1245) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 22;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>924&&x<1060&&y>1254&&y<1295) {
                    MainActivity.line = "sukhumvit";
                    MainActivity.station = 23;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>256&&x<467&&y>674&&y<729) {
                    MainActivity.line = "silom";
                    MainActivity.station = 1;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>544&&x<692&&y>714&&y<763) {
                    MainActivity.line = "silom";
                    MainActivity.station = 3;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>535&&x<683&&y>769&&y<816) {
                    MainActivity.line = "silom";
                    MainActivity.station = 4;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>504&&x<671&&y>819&&y<871) {
                    MainActivity.line = "silom";
                    MainActivity.station = 5;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>445&&x<525&&y>843&&y<930) {
                    MainActivity.line = "silom";
                    MainActivity.station = 6;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>380&&x<451&&y>847&&y<945) {
                    MainActivity.line = "silom";
                    MainActivity.station = 7;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>306&&x<374&&y>794&&y<887) {
                    MainActivity.line = "silom";
                    MainActivity.station = 8;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>253&&x<318&&y>843&&y<939) {
                    MainActivity.line = "silom";
                    MainActivity.station = 9;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>194&&x<256&&y>791&&y<890) {
                    MainActivity.line = "silom";
                    MainActivity.station = 10;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>145&&x<191&&y>837&&y<955) {
                    MainActivity.line = "silom";
                    MainActivity.station = 11;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>11&&x<130&&y>843&&y<899) {
                    MainActivity.line = "silom";
                    MainActivity.station = 12;
                    startActivity(new Intent(view.getContext(), Popup.class));
                } else if (x>20&&x<108&&y>732&&y<831) {
                    MainActivity.line = "silom";
                    MainActivity.station = 13;
                    startActivity(new Intent(view.getContext(), Popup.class));
                }
                return false;
            }
        });
    }
}
