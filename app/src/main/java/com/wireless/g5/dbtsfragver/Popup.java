package com.wireless.g5.dbtsfragver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wireless.g5.dbtsfragver.content.StationContent;

public class Popup extends Activity {

    LinearLayout siam_layout = null;
    static String station,term1,term2,term3="สนามกีฬาแห่งชาติ",term4="บางหว้า",line;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        if (MainActivity.line == "sukhumvit") {
            line = "สายสุขุมวิท";
            station = StationContent.STATIONS_SUKHUMVIT.get(MainActivity.station-1).content;
            term1 = StationContent.TER_SUKHUMVIT[0];
            term2 = StationContent.TER_SUKHUMVIT[1];
        } else if (MainActivity.line == "silom") {
            line = "สายสีลม";
            station = StationContent.STATIONS_SILOM.get(MainActivity.station-1).content;
            term1 = StationContent.TER_SILOM[0];
            term2 = StationContent.TER_SILOM[1];
        } else {
            line = "สถานีกลาง";
            station = StationContent.STATIONS_SUKHUMVIT.get(MainActivity.station-1).content;
            term1 = StationContent.TER_SUKHUMVIT[0];
            term2 = StationContent.TER_SUKHUMVIT[1];
//            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            LinearLayout parent = (LinearLayout) findViewById(R.id.pop_content);
//            inflater.inflate(R.layout.siam_term, parent);
            LinearLayout pop_content = (LinearLayout) findViewById(R.id.pop_content);
            siam_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.siam_term, pop_content,false);
            pop_content.addView(siam_layout);

            ImageView siam_green = (ImageView) siam_layout.findViewById(R.id.green_arrow_siam);
            ImageView siam_red = (ImageView) siam_layout.findViewById(R.id.red_arrow_siam);
            Animation swing = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing);
            siam_green.startAnimation(swing);
            siam_red.startAnimation(swing);
        }

        ((TextView) findViewById(R.id.title_station)).setText(station);
        ((TextView) findViewById(R.id.title_line)).setText(" ( "+line+" )");
        ((TextView) findViewById(R.id.term1)).setText(term1);
        ((TextView) findViewById(R.id.term2)).setText(term2);

        LinearLayout t1 = (LinearLayout) findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.direction = term1;
                startActivity(new Intent(view.getContext(),DensityChartActivity.class));
            }
        });

        LinearLayout t2 = (LinearLayout) findViewById(R.id.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.direction = term2;
                startActivity(new Intent(view.getContext(),DensityChartActivity.class));
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        if(MainActivity.line == "cen") getWindow().setLayout((int) (width*.8),(int) (height*.75));
        else getWindow().setLayout((int) (width*.8),(int) (height*.45));
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Refer the ImageView like this
        ImageView green_arr = (ImageView) findViewById(R.id.green_arrow);
        ImageView red_arr = (ImageView) findViewById(R.id.red_arrow);
        Animation swing = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.swing);
        green_arr.startAnimation(swing);
        red_arr.startAnimation(swing);
    }

    public void siam3(View view) {
        MainActivity.direction = term3;
        startActivity(new Intent(view.getContext(),DensityChartActivity.class));
    }
    public void siam4(View view) {
        MainActivity.direction = term4;
        startActivity(new Intent(view.getContext(),DensityChartActivity.class));
    }
}
