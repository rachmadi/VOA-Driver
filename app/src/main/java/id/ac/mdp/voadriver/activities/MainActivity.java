package id.ac.mdp.voadriver.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.fragments.OrderFragment;
import id.ac.mdp.voadriver.fragments.ProfileFragment;
import id.ac.mdp.voadriver.fragments.ReimburseFragment;


public class MainActivity extends AppCompatActivity {

    FragmentManager ftBottomNavigation=getSupportFragmentManager();
    Fragment fragment=OrderFragment.newInstance();
    BottomNavigationViewEx navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    fragment = OrderFragment.newInstance();
                    break;
                case R.id.navigation_profile:
                    fragment = ProfileFragment.newInstance();
                    break;
                case R.id.navigation_help:
                    fragment = ReimburseFragment.newInstance();
                    break;
            }

            if(ftBottomNavigation.getBackStackEntryCount()==0){
                ftBottomNavigation.beginTransaction().replace(R.id.content, fragment)
                        .addToBackStack(fragment.getTag())
                        .commit();
            }else {
                ftBottomNavigation.beginTransaction().replace(R.id.content, fragment)
                        .commit();
            }

            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ftBottomNavigation.beginTransaction().replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigation.getMenu().getItem(0).setChecked(true);
    }
}
