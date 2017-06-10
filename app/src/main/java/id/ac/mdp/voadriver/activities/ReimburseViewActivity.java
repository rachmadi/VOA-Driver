package id.ac.mdp.voadriver.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.mdp.voadriver.adapters.ViewReimburseViewAdapter;
import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.models.PettyCashUsage;

public class ReimburseViewActivity extends AppCompatActivity {

    RecyclerView rvReimburseView;
    ViewReimburseViewAdapter adapterChat;
    List<PettyCashUsage> DB =new ArrayList<>();
    List<PettyCashUsage> datasetPettyCashUsage =new ArrayList<>();
    String pettyCashID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_view);

        DB.add(new PettyCashUsage("1001a","1001","May 29,10:35","Bayar Tol Cipinang",5000));
        DB.add(new PettyCashUsage("1001b","1001","May 29,14:30","Isi Bensin",200000));
        DB.add(new PettyCashUsage("1001c","1001","May 29,15:23","Parkir Plaza Jakarta",12000));
        DB.add(new PettyCashUsage("1001d","1001","May 30,16:43","Cuci Mobil",35000));

        DB.add(new PettyCashUsage("1002a","1002","Apr 1,10:35","Belanja keperluan rapat",43000));
        DB.add(new PettyCashUsage("1002b","1002","Apr 1,14:30","Isi Bensin",50000));

        DB.add(new PettyCashUsage("1003a","1003","Apr 3,6:53","Isi Bensin",50000));

        DB.add(new PettyCashUsage("1004a","1004","Apr 8,16:43","Bayar Parkir Airport",7000));
        DB.add(new PettyCashUsage("1004b","1004","Apr 9,12:35","Belanja keperluan rapat",45000));
        DB.add(new PettyCashUsage("1004c","1004","Apr 10,08:30","Isi Bensin",65000));

        pettyCashID = getIntent().getStringExtra("pettyCashID");
        getSupportActionBar().setTitle("ID : "+pettyCashID);

        for (PettyCashUsage pj: DB) {
            if(pj.getPetty_cash_id().equals(pettyCashID)){
                datasetPettyCashUsage.add(pj);
            }
        }


        rvReimburseView = (RecyclerView)findViewById(R.id.rc_reimburse_view);
        rvReimburseView.setLayoutManager(new LinearLayoutManager(ReimburseViewActivity.this));
        rvReimburseView.setItemAnimator(new DefaultItemAnimator());

        adapterChat = new ViewReimburseViewAdapter(ReimburseViewActivity.this,datasetPettyCashUsage);
        rvReimburseView.setAdapter(adapterChat);
    }
}
