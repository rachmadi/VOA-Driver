package id.ac.mdp.voadriver.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ac.mdp.voadriver.R;

public class ArgoActivity extends AppCompatActivity {

    /*Button btnStartOrder, btnStopOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argo);

        btnStartOrder = (Button)findViewById(R.id.btn_argo_start);
        btnStopOrder = (Button)findViewById(R.id.btn_argo_stop);

        btnStartOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ArgoActivity.this, AddPinActivity.class));
                btnStartOrder.setVisibility(View.GONE);
                btnStopOrder.setVisibility(View.VISIBLE);
            }
        });

        btnStopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ArgoActivity.this);
                alert.setMessage("Order Telah Selesai ?");
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(ArgoActivity.this, AddPinActivity.class));
                    }
                });
                alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });
    }*/

    TextView tvTime;
    Button btnStartArgo, btnStopArgo;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argo);

        tvTime = (TextView)findViewById(R.id.tv_argo_time);
        btnStartArgo = (Button)findViewById(R.id.btn_argo_start);
        btnStopArgo = (Button)findViewById(R.id.btn_argo_stop);
        handler = new Handler();

        btnStartArgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                btnStartArgo.setVisibility(View.GONE);
                btnStopArgo.setVisibility(View.VISIBLE);
            }
        });

        btnStopArgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ArgoActivity.this);
                alertDialogBuilder.setTitle("Konfirmasi.");
                alertDialogBuilder.setMessage("Order telah diselesaikan?");
                alertDialogBuilder.setNegativeButton("Tidak, belum selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialogBuilder.setPositiveButton("Ya, untuk menyelesaikan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        return;
                    }
                });
                alertDialogBuilder.show();
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int)(UpdateTime % 1000);
            tvTime.setText("" + Minutes + ":" + String.format("%02d", Seconds) + ":" + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }
    };
}
