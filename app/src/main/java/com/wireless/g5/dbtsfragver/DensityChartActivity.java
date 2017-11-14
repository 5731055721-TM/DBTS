package com.wireless.g5.dbtsfragver;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DensityChartActivity extends AppCompatActivity {

    private final static int INTERVAL = 1000 * 60 / 60 ; //2 minutes
    Handler mHandler = new Handler();
    BufferedReader reader;
    String[] buffer = new String[4];
    int line_count=0;
    int i=0;
    TextView t,n;

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
                        int result = Integer.parseInt(n.getText().toString().trim());

                        int populate1 = Integer.parseInt(readLine(buffer[0])[i]);
                        int populate2 = Integer.parseInt(readLine(buffer[1])[i]);
                        int populate3 = Integer.parseInt(readLine(buffer[2])[i]);
                        int populate4 = Integer.parseInt(readLine(buffer[3])[i]);

                        updateDensityChart(R.id.first+0,populate1+0);
                        updateDensityChart(R.id.second+0,populate2+0);
                        updateDensityChart(R.id.third+0,populate3+0);
                        updateDensityChart(R.id.fourth+0,populate4+0);

                        result = populate1 + populate2 + populate3 + populate4;

                        t.setText("("+populate1+","+populate2+","+populate3+","+populate4+") -> "+i); //test
                        n.setText(result+"");  //show total
                    }
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {  }
            }
        }
    });

    String[] readLine(String buffer) {
        return buffer.split(":");
    }

    void updateDensityChart(int boggie, int populate) {
        LinearLayout chart = (LinearLayout) findViewById(boggie);
        if (populate>=0&&populate<=10) chart.setBackgroundColor(getResources().getColor(R.color.green));
        if (populate>10&&populate<=30) chart.setBackgroundColor(getResources().getColor(R.color.yellow));
        if (populate>30) chart.setBackgroundColor(getResources().getColor(R.color.red));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density_chart);
        t = (TextView) findViewById(R.id.debug);
        TextView v = (TextView) findViewById(R.id.desc);
        n = (TextView) findViewById(R.id.num_people);
        v.append(Popup.station+" - "+MainActivity.direction);
        buffer[0] = readFile("test1.txt");
        buffer[1] = readFile("test2.txt");
        buffer[2] = readFile("test3.txt");
        buffer[3] = readFile("test4.txt");
        simulate.start();
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

