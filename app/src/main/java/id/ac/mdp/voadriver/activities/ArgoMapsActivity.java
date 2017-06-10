/*package id.ac.mdp.voadriver;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {
    private Button btnRequestDirection;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyA8sY4Y071UiLhgcDiMW5eNSypIvn_uD58";
    private LatLng camera = new LatLng(37.782437, -122.4281893);
    //private LatLng origin = new LatLng(37.7849569, -122.4068855);
    private LatLng origin = new LatLng(37.782437, -122.4281893);
    private LatLng destination = new LatLng(37.7814432, -122.4460177);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnRequestDirection = (Button) findViewById(R.id.btn_request_direction);
        btnRequestDirection.setOnClickListener(this);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 13));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_request_direction) {
            requestDirection();
        }
    }

    public void requestDirection() {
        Snackbar.make(btnRequestDirection, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            googleMap.addMarker(new MarkerOptions().position(origin));
            googleMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));

            btnRequestDirection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
*/
package id.ac.mdp.voadriver.activities;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import id.ac.mdp.voadriver.R;

public class ArgoMapsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {
    private Button btnRequestDirection;
    private GoogleMap googleMap;
    private LatLng camera = new LatLng(-2.973660,104.764094);
    private LatLng origin = new LatLng(-2.973660,104.764094);
    private LatLng destination = new LatLng(-2.983658,104.759266);
    private int i = 0;
    ImageButton btnPause, btnFocus, btnFollow;

    private String status = "On the way";

    FloatingActionButton onLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnPause = (ImageButton) findViewById(R.id.btn_wait);
        btnFocus = (ImageButton) findViewById(R.id.btn_focus);
        btnFollow = (ImageButton) findViewById(R.id.btn_follow);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Argo");
        }

        onLocation = (FloatingActionButton) findViewById(R.id.btn_set_onLocation);
        onLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_location));

        onLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equals("On the way")){

                    onLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_location));
                    Toast.makeText(ArgoMapsActivity.this, status, Toast.LENGTH_SHORT).show();
                    onLocation.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#1976D2")));
                    confirmOnLocation();


                }else if(status.equals("On Location")){

                    status = "On Progress";
                    onLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_car));
                    Toast.makeText(ArgoMapsActivity.this, status, Toast.LENGTH_SHORT).show();
                    onLocation.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#388E3C")));

                }else if(status.equals("On Progress")){
                    finish();
                }

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArgoMapsActivity.this, "hahahaha", Toast.LENGTH_SHORT).show();
            }
        });


        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 16));
    }

    @Override
    public void onClick(View v) {
        /*int id = v.getId();
        if (id == R.id.btn_request_direction) {
            requestDirection();
        }*/
    }

    public void requestDirection() {
        Snackbar.make(btnRequestDirection, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        String serverKey = "AIzaSyA8sY4Y071UiLhgcDiMW5eNSypIvn_uD58";
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.TRANSIT)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getSectionPoint();
            for (LatLng position : sectionPositionList) {
                googleMap.addMarker(new MarkerOptions().position(position));
            }

            List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
            ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
            for (PolylineOptions polylineOption : polylineOptionList) {
                googleMap.addPolyline(polylineOption);
            }

            btnRequestDirection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void confirmOnLocation(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ArgoMapsActivity.this);
        builder.setTitle("Konfirmasi On Location");
        builder.setMessage("Konfirmasi anda telah sampai lokasi penjemputan");
        builder.setPositiveButton("Ya, saya telah sampai", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                status = "On Location";
                //onLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_car));
            }
        });
        builder.setNegativeButton("Belum Sampai", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
