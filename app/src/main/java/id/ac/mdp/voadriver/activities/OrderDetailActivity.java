package id.ac.mdp.voadriver.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.model.LatLng;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import id.ac.mdp.voadriver.R;
import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.mdp.voadriver.fragments.MapFragment;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.models.UserOrder;

public class OrderDetailActivity extends AppCompatActivity {

    TextView mTextMessage, tvOrderAddress, tvOrderTime;
    TextView tvTujuan,tvWaktu,tvDurasi,tvKegiatan,tvBiaya;
    Bundle bundle;
    CircleImageView ivUserProfile;
    AlertDialog alertDialog;
    UserOrder datasetOrder;
    User datasetUser;
    ImageView mapOrder;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_call:
                    //startActivity(new Intent(ActivityOrderDetail.this, ActivityMaps.class));
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:="+datasetUser.getPhoneNumber()));

                    if (ActivityCompat.checkSelfPermission(OrderDetailActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    }
                    startActivity(callIntent);
                    return true;
                case R.id.action_insert_key:
                    // Start Activity
                    startActivity(new Intent(OrderDetailActivity.this, ArgoMapsActivity.class));
                    return true;

                case R.id.navigation_add_chat:
                    startActivity(new Intent(OrderDetailActivity.this, ChatActivity.class));
                    //dispatchTakePictureIntent();
                    return true;

                /*
                case R.id.navigation_cancel:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                    */
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[] {
                Color.GRAY,
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };


        mTextMessage = (TextView) findViewById(R.id.tvOrderUsername);
        tvOrderAddress = (TextView) findViewById(R.id.tvOrderAddress);
        tvOrderTime = (TextView) findViewById(R.id.tvOrderTime);
        ivUserProfile = (CircleImageView) findViewById(R.id.ivUserProfile);

        tvTujuan = (TextView)findViewById(R.id.tv_detail_tujuan);
        tvWaktu = (TextView)findViewById(R.id.tv_detail_waktu);
        tvDurasi = (TextView)findViewById(R.id.tv_detail_durasi);
        tvKegiatan = (TextView)findViewById(R.id.tv_detail_kegiatan);

        mapOrder = (ImageView) findViewById(R.id.img_map_order_detail);


        //tvBiaya = (TextView)findViewById(R.id.tv_detail_biaya);

        //llContainer = (LinearLayout) findViewById(R.id.ll_map_container);

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation_order_detail);

        navigation.setItemIconTintList(new ColorStateList(states,colors));
        navigation.setItemTextColor(new ColorStateList(states,colors));
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.getMenu().getItem(1).setTitle(null);
        navigation.setIconCenter(1);
        navigation.setIconSizeAt(1, 36, 36);
        navigation.setIconTintList(1, getResources().getColorStateList(R.color.colorWhite));
        navigation.setItemBackground(1,R.color.colorPrimary);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        datasetOrder = getIntent().getParcelableExtra("datasetOrder");
        datasetUser = getIntent().getParcelableExtra("datasetUser");

        tvTujuan.setText(datasetOrder.getDestination_location());
        //tvWaktu.setText(Utilities.getDateTime(datasetOrder.getBooking_order(),"yyyy:MM:dd"));
        tvWaktu.setText(datasetOrder.getBooking_order());
        tvDurasi.setText("3 jam");
        tvKegiatan.setText(datasetOrder.getKegiatan());
        //tvBiaya.setText(Utilities.getCurrency(String.valueOf(datasetOrder.getCost_estimation())));
        //username = bundle.getString("orderUserName");
        //String address = bundle.getString("orderAddress");
        //String time = bundle.getString("orderTime");
        //int image = (int)bundle.get("orderProfil");

        // MAPS FRAGMENT

        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mapContainer, new MapFragment(), "Fragment").commit();*/

        /*mTextMessage.setText(username);
        tvOrderAddress.setText(address);
        tvOrderTime.setText(time);
        Glide.clear(ivUserProfile);
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivUserProfile);*/

        mTextMessage.setText(datasetOrder.getUser_id());
        tvOrderAddress.setText(datasetOrder.getDestination_location());
        //tvOrderTime.setText(Utilities.getDateTime(datasetOrder.getBooking_order(),"yyyy:MM:dd"));
        tvOrderTime.setText(datasetOrder.getBooking_order());
        Glide.clear(ivUserProfile);
        Glide.with(this)
                .load(R.drawable.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivUserProfile);


        //String url = "http://maps.google.com/maps/api/staticmap?center=" + -2.973652 + "," + 104.764091 + "&zoom=16&size=500x300&markers=color:0xff0000%7Clabel:%7C" + -2.973652 + "," + 104.764091 + "&key=AIzaSyD0Sntn9DyXQmvJH5xr9xRfClQlLtqhuIg";
        //String url = "https://maps.googleapis.com/maps/api/staticmap?size=600x300&maptype=roadmap&markers=icon:http://api.appcargo.com/app_images/ic_pin.png|-2.973660, 104.764094&markers=icon:http://api.appcargo.com/app_images/ic_pin_destination_toolbar.png|-2.983658, 104.759266&path=color:0xff0000ff|weight:5|enc:m_mpG{y~{Bi@p@yF_F`@sA&format=jpg";
        //String url = "https://maps.googleapis.com/maps/api/staticmap?size=1000x500&maptype=roadmap&markers=icon:http://api.appcargo.com/app_images/ic_pin.png|44.80006858,20.4791788&markers=icon:http://api.appcargo.com/app_images/ic_pin_destination_toolbar.png|44.8013566,20.4804733&path=color:0xff0000ff|weight:5|enc:m_mpG{y~{Bi@p@yF_F`@sA&format=jpg";
        LatLng source = new LatLng(-2.973660,104.764094);
        LatLng destination = new LatLng(-2.983658,104.759266);
        PolylineTask polyTask = new PolylineTask(source, destination);
        polyTask.execute();

    }
    private class PolylineTask extends AsyncTask<Void,Void,String>{
        LatLng source,destination;

        public PolylineTask(LatLng source, LatLng destination){
            this.source = source;
            this.destination = destination;
        }

        @Override
        protected String doInBackground(Void... param) {
            String result="";
            String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+source.latitude+","+source.longitude+"&destination="+destination.latitude+","+destination.longitude+"&mode=driving&key=AIzaSyDLhSOBWUKZ3EhAEQKPD9TeUxBGFOGhepE";
            try {
                result = getEncodedPolyline(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            String url = "https://maps.googleapis.com/maps/api/staticmap?size=600x500&center="+source.latitude+",%20"+source.longitude+"&zoom=14&size=600x300&maptype=roadmap&markers=icon:http://api.appcargo.com/app_images/ic_pin.png|-2.973660,%20104.764094&markers=icon:http://api.appcargo.com/app_images/ic_pin_destination_toolbar.png|"+destination.latitude+",%20"+destination.longitude+"&path=color:0xff0000ff|weight:5|enc:"+result+"&format=jpg";

            Glide.with(OrderDetailActivity.this).load(url).into(mapOrder);
            mapOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderDetailActivity.this, MapsDetailActivity.class));
                }
            });
        }


        private String getEncodedPolyline(String strUrl) throws IOException {
            String result="";
            HttpURLConnection urlConnection = null;
            InputStream iStream = null;
            try {

                URL url = new URL(strUrl);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                String jsonString = sb.toString();

                JSONObject obj = new JSONObject(jsonString);

                result = obj.getJSONArray("routes").getJSONObject(0).getJSONObject("overview_polyline").getString("points");


            }
            catch (Exception e){
                Log.d("Exception: ", e.toString());
            }finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return result;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.order_detail_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_insert_key) {
            // Start Activity
            startActivity(new Intent(OrderDetailActivity.this, ArgoActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
