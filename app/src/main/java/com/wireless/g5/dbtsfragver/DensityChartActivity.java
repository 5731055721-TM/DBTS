package com.wireless.g5.dbtsfragver;

import android.content.DialogInterface;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.*;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DensityChartActivity extends AppCompatActivity {

    private final static int INTERVAL = 1000 * 60 / 60 ; //2 minutes
    private Handler mHandler = new Handler();
    private BufferedReader reader;
    private String[] buffer = new String[4];
    private int line_count=0;
    private int i=0,a=5;
    private TextView t,n,v,d;

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {

        }
    };

    Thread simulate = new Thread(new Runnable() {

        @Override
        public void run() {
            // we add 100 new entries
            for (i = 0; i < line_count; i++) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        int result = Integer.parseInt(n.getText().toString().trim());

                        if(a>=0&&i>10&&i<20) { d.setText("มาถึงในอีก "+a+" นาที"); a--; }
                        if(a<0) d.setText("กรุณารอที่ชานชลา");
                        if(i==20) a=7;
                        if(i>20) { d.setText("มาถึงในอีก "+a+" นาที"); a--; }
                        if(a<0) d.setText("กรุณารอที่ชานชลา");

                        int populate1 = Integer.parseInt(readLine(buffer[0])[i]);
                        int populate2 = Integer.parseInt(readLine(buffer[1])[i]);
                        int populate3 = Integer.parseInt(readLine(buffer[2])[i]);
                        int populate4 = Integer.parseInt(readLine(buffer[3])[i]);

                        LinearLayout train1 = (LinearLayout) findViewById(R.id.arriving1);
                        LinearLayout train2 = (LinearLayout) findViewById(R.id.arriving2);

                        for(int x=0; x<train2.getChildCount(); x++) {

                            float scalingFactor = 0.7f; // scale down to half the size
                            train2.setScaleX(scalingFactor);
                            train2.setScaleY(scalingFactor);
                        }

                        updateDensityLayout(train1,populate1,populate2,populate3,populate4);
                        updateDensityLayout(train2,populate1,populate2,populate3,populate4);

//                        result = populate1 + populate2 + populate3 + populate4;

//                        t.setText("("+populate1+","+populate2+","+populate3+","+populate4+") -> "+i); //test
//                        n.setText(result+"");  //show total
                    }
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {  }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density_chart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        t = (TextView) findViewById(R.id.debug);
        v = (TextView) findViewById(R.id.desc);
//        n = (TextView) findViewById(R.id.num_people);
        d = (TextView) findViewById(R.id.detail);
        v.setText(Popup.station+" - "+MainActivity.direction);
        buffer[0] = readFile("test1.txt");
        buffer[1] = readFile("test2.txt");
        buffer[2] = readFile("test3.txt");
        buffer[3] = readFile("test4.txt");
        simulate.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    String[] readLine(String buffer) {
        return buffer.split(":");
    }
    void updateDensityLayout(LinearLayout layout, int pop1, int pop2, int pop3, int pop4) {
        updateDensityChart(layout.findViewById(R.id.first),pop1);
        updateDensityChart(layout.findViewById(R.id.second),pop2);
        updateDensityChart(layout.findViewById(R.id.third),pop3);
        updateDensityChart(layout.findViewById(R.id.fourth),pop4);

        Animation moving = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moving);
        ((LinearLayout) layout.findViewById(R.id.moving_line1)).startAnimation(moving);
        ((LinearLayout) layout.findViewById(R.id.moving_line2)).startAnimation(moving);
        ((LinearLayout) layout.findViewById(R.id.moving_line3)).startAnimation(moving);
        ((LinearLayout) layout.findViewById(R.id.moving_line4)).startAnimation(moving);
        ((LinearLayout) layout.findViewById(R.id.moving_line5)).startAnimation(moving);
    }
    void updateDensityChart(View boggie, int populate) {
//        LinearLayout chart = (LinearLayout) findViewById(boggie);
        LinearLayout chart = (LinearLayout) boggie;
        if (populate>=0&&populate<10) chart.setBackgroundColor(getResources().getColor(R.color.green));
        else if (populate>=10) chart.setBackgroundColor(getResources().getColor(R.color.yellow));
        else if (populate>=20) chart.setBackgroundColor(getResources().getColor(R.color.orange));
        else chart.setBackgroundColor(getResources().getColor(R.color.red));
    }

    void alert() {
        AlertDialog alertDialog = new AlertDialog.Builder(DensityChartActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    void startRepeatingTask() {
        mHandlerTask.run();
    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask);
    }
    void  setLocked(ImageView v) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);  //0 means grayscale
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        v.setColorFilter(cf);
        v.setImageAlpha(128);   // 128 = 0.5
    }
    void  setGreyScale(ImageView v) {
        v.setColorFilter(null);
        v.setImageAlpha(255);
    }
    private String readFile(String file) {
        String line, buffer="";
        line_count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            Log.d("Reader Stuff", reader.readLine());
            line = reader.readLine();
            buffer += line.split(" ")[2];
            if(line!=null) line_count++;
            while ((line = reader.readLine()) != null) {
                Log.d("code", line);
                buffer += ":"+line.split(" ")[2];
                line_count++;
            }
        } catch (Exception e) {
            Log.e("err : ", e.toString());
        }
        Log.v("line count: ",line_count+"");
        return buffer;
    }
}

