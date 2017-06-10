package id.ac.mdp.voadriver.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.mdp.voadriver.R;

/*
 * Created by Tengku Kevin on 3/24/2017.
 */

public class HelpFragment extends Fragment {

    View view;

    public HelpFragment(){
        // Constructor
    }

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bantuan, container, false);
        return view;
    }
}
