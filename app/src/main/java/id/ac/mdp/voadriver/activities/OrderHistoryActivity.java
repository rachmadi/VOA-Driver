package id.ac.mdp.voadriver.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.adapters.OrderHistoryViewAdapter;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.models.UserOrder;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView rvOrderHistoryList;
    OrderHistoryViewAdapter orderHistoryViewAdapter;

    //Dummy Data

    List<UserOrder> datasetOrder;
    List<User> datasetUser;

    String[] username = {"John Mickley","John Connor","Mary Blaster","Kennan Lee","Rock Boy","Regent Key","Ken White","John Mickley","John Connor","Mary Blaster","Kennan Lee","Rock Boy","Regent Key","Ken White"};
    String[] time = {"12:10","14:20","23:11","14:22","08:21","02:09","17:30","12:10","14:20","23:11","14:22","08:21","02:09","17:30"};
    String[] address = {"Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",};
    String[] statusOrder = {"Selesai","Sedang Dalam Perjalanan","Menunggu","Dibatalkan","Selesai","Sedang Dalam Perjalanan","Menunggu","Dibatalkan","Selesai","Sedang Dalam Perjalanan","Menunggu","Dibatalkan","Sedang Dalam Perjalanan","Menunggu"};
    int[] profileImage = {R.drawable.avatar,R.drawable.profil,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.avatar};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        datasetUser=new ArrayList<>();
        User tempUser=new User("user1"
                ,"dom1"
                ,"Susi Susanti"
                ,"PIC"
                ,"string photo"
                ,"085689063274"
                ,"1234"
                ,86000
                ,90000);
        datasetUser.add(tempUser);

        datasetOrder=new ArrayList<>();
        UserOrder tempOrder=new UserOrder("order01"
                ,"driver1"
                ,"user1"
                ,"29/11/17\n16:00"
                ,"29/11/17\n16:00"
                ,"29/11/17\n16:00"
                ,"Jalan Perintis Kemerdekaan"
                ,"Jalan Merdeka, Palembang, Sumatera Selatan"
                ,5.5
                ,25500
                ,27000
                ,"harian"
                ,"Success"
                ,"Rapat");
        datasetOrder.add(tempOrder);

        rvOrderHistoryList = (RecyclerView)findViewById(R.id.rc_order_history);
        rvOrderHistoryList.setLayoutManager(new LinearLayoutManager(this));
        rvOrderHistoryList.setItemAnimator(new DefaultItemAnimator());

        //orderHistoryViewAdapter = new OrderHistoryViewAdapter(OrderHistoryActivity.this,username,address,time,profileImage);
        orderHistoryViewAdapter = new OrderHistoryViewAdapter(OrderHistoryActivity.this,datasetOrder,datasetUser);
        rvOrderHistoryList.setAdapter(orderHistoryViewAdapter);
        setTitle("History Order");

    }
}
