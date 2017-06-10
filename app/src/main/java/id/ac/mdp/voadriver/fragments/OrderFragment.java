package id.ac.mdp.voadriver.fragments;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.activities.OrderHistoryActivity;
import id.ac.mdp.voadriver.adapters.OrderViewAdapter;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.models.UserOrder;
import id.ac.mdp.voadriver.utils.AppController;
import id.ac.mdp.voadriver.utils.CustomRequest;
import id.ac.mdp.voadriver.utils.Utilities;


/*
 * Created by Tengku Kevin on 3/24/2017.
 */

public class OrderFragment extends Fragment {
    RecyclerView rvOrderList;
    OrderViewAdapter viewAdapterOrder;
    SwipeRefreshLayout swipeRefreshLayout;

    FragmentTransaction ft;

    List<UserOrder> datasetOrder;
    List<User> datasetUser;

    /* ------------------DUMMY DATA [START]------------------ */

    String[] username = {"John Mickley","John Connor","Mary Blaster","Kennan Lee","Rock Boy","Regent Key","Ken White","John Mickley","John Connor","Mary Blaster","Kennan Lee","Rock Boy","Regent Key","Ken White"};
    String[] time = {"12:10","14:20","23:11","14:22","08:21","02:09","17:30","12:10","14:20","23:11","14:22","08:21","02:09","17:30"};
    String[] address = {"Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Those pilots elected Bill captain of our team, Alfred niece keeps the room warm.",
            "I called him 'the science teacher.'",
            "Those flight attendants had him drive.",
            "Those flight attendants had him drive.",
            "Jalan Srijaya Negara, Lrg. Jaya Sempurna, No.43, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Putri Rambut Selako, Padang Selasa, Bukit Besar, Palembang, Sumatera Selatan",
            "Jalan Rajawali, No.12, Palembang, Sumatera Selatan.",
            "Those pilots elected Bill captain of our team, Alfred niece keeps the room warm.",
            "I called him 'the science teacher.'",
            "Those flight attendants had him drive.",
            "Those flight attendants had him drive."};
    int[] profileImage = {R.drawable.avatar,R.drawable.profil,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.profil,R.drawable.avatar,R.drawable.avatar,R.drawable.profil,R.drawable.avatar};

    /* ------------------DUMMY DATA [END]------------------ */

    public OrderFragment(){
        // Constructor
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*INITIALIZE*/
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_order);
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swpOrder);
        rvOrderList = (RecyclerView)view.findViewById(R.id.rc_order);
        rvOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrderList.setItemAnimator(new DefaultItemAnimator());

        Utilities utilities=new Utilities();
        final User currentUser=utilities.getUser(getContext());

        //====================
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
                ,"01/12/17\n17:10"
                ,"01/12/17\n17:10"
                ,"01/12/17\n17:10"
                ,"Jalan Perintis Kemerdekaan"
                ,"Jalan Merdeka, Palembang, Sumatera Selatan"
                ,5.5
                ,25500
                ,27000
                ,"harian"
                ,"Success"
                ,"Rapat");
        datasetOrder.add(tempOrder);
        viewAdapterOrder = new OrderViewAdapter(getContext(),datasetOrder,datasetUser);
        rvOrderList.setAdapter(viewAdapterOrder);
        //======================

        //refreshDriverOrderData(currentUser.getUserId());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshDriverOrderData(currentUser.getUserId());
                //swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void refreshDriverOrderData(String driverId) {
        if(haveNetworkConnection()){
            ProgressDialog progressDialog = new ProgressDialog(getContext(),
                    R.style.Theme_AppCompat_DayNight_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Refreshing...");
            progressDialog.show();

            final Utilities util=new Utilities();
            String url=util.getConnectionUrl("viewOrderDriver");

            HashMap<String,String> param=new HashMap<>();
            param.put("driver_id",driverId);
            CustomRequest customRequest=new CustomRequest(Request.Method.POST, url, param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.print(response);
                    try {
                        int success=response.getInt("success");
                        if(success==1){
                            JSONArray jsonUser = response.getJSONArray("data");
                            try{
                                datasetOrder=new ArrayList<>();
                                datasetUser=new ArrayList<>();
                                for(int i=0;i<jsonUser.length();i++){
                                    JSONObject jsonobject= (JSONObject) jsonUser.get(i);
                                    UserOrder temp=new UserOrder(jsonobject.optString("order_id")
                                            ,jsonobject.optString("driver_id")
                                            ,jsonobject.optString("user_id")
                                            ,jsonobject.optString("booking_order")
                                            ,jsonobject.optString("start_order")
                                            ,jsonobject.optString("finish_order")
                                            ,jsonobject.optString("origin_location")
                                            ,jsonobject.optString("destination_location")
                                            ,jsonobject.optDouble("total_distance")
                                            ,jsonobject.optDouble("cost_estimation")
                                            ,jsonobject.optDouble("real_cost")
                                            ,jsonobject.optString("order_type")
                                            ,jsonobject.optString("order_status")
                                            ,jsonobject.optString("kegiatan"));
                                    datasetOrder.add(temp);

                                    addUser(jsonobject.optString("user_id"));
                                }
                                viewAdapterOrder = new OrderViewAdapter(getContext(),datasetOrder,datasetUser);
                                rvOrderList.setAdapter(viewAdapterOrder);
                            }catch(JSONException e){
                                Log.e("log_tag", "Error parsing data "+e.toString());
                            }
                        }else{
                            onRefreshFailed("Connection Failure");
                        }
                    } catch (JSONException e) {
                        onRefreshFailed("Json error: "+e);
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onRefreshFailed("volley error: "+error.getMessage());
                }
            });
            AppController.getInstance().addToRequestQueue(customRequest);
            progressDialog.dismiss();
        }else {
            onRefreshFailed("No Connection!");
        }
    }

    private void addUser(String user_id) {
        final Utilities util=new Utilities();
        String url=util.getConnectionUrl("viewUser");

        HashMap<String,String> param=new HashMap<>();
        param.put("user_id",user_id);
        CustomRequest customRequest=new CustomRequest(Request.Method.POST, url, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.print(response);
                try {
                    int success=response.getInt("success");
                    if(success==1){
                        JSONArray jsonUser = response.getJSONArray("data");
                        try{
                            for(int i=0;i<jsonUser.length();i++){
                                JSONObject jsonobject= (JSONObject) jsonUser.get(i);
                                User temp=new User(jsonobject.optString("user_id")
                                        ,jsonobject.optString("domain")
                                        ,jsonobject.optString("name")
                                        ,jsonobject.optString("position")
                                        ,jsonobject.optString("photo")
                                        ,jsonobject.optString("phone_number")
                                        ,jsonobject.optString("pin")
                                        ,jsonobject.optInt("current_quota")
                                        ,jsonobject.optInt("maximum_quota"));
                                datasetUser.add(temp);
                            }
                        }catch(JSONException e){
                            Log.e("log_tag", "Error parsing data "+e.toString());
                        }
                    }else{
                        onRefreshFailed("Connection Failure");
                    }
                } catch (JSONException e) {
                    onRefreshFailed("Json error: "+e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onRefreshFailed("volley error: "+error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(customRequest);
    }

    public void onRefreshFailed(String message) {
        Utilities.showAsToast(getContext(), message, Toast.LENGTH_SHORT);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.order, menu);
        super.onCreateOptionsMenu(menu, inflater);
        ft=getFragmentManager().beginTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_notification) {
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.content, new NotificationFragment(), "Fragment")
                    .addToBackStack("Fragment")
                    .commit();
            return true;
        /*}else if(id == R.id.action_help){
            DriverHelp();
            return true;*/
        }else if(id == R.id.action_history){
            startActivity(new Intent(getContext(), OrderHistoryActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DriverHelp(){
        final CharSequence[] options = { "Terima Order", "Ubah Profil", "Reimburse" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bantuan")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Terima Order")) {
                            Utilities.showAsToast(getContext(), "Terima Order", Toast.LENGTH_SHORT);
                        }
                        else if (options[item].equals("Ubah Profil")) {
                            Utilities.showAsToast(getContext(), "Ubah Profil", Toast.LENGTH_SHORT);
                        }
                        else if (options[item].equals("Reimburse")) {
                            Utilities.showAsToast(getContext(), "Reimburse", Toast.LENGTH_SHORT);
                        }
                    }
                }).show();
    }
}

