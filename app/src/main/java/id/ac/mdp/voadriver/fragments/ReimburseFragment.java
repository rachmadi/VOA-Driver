package id.ac.mdp.voadriver.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.adapters.ReimburseViewAdapter;
import id.ac.mdp.voadriver.models.PettyCash;
import id.ac.mdp.voadriver.utils.Utilities;


/**
 * Created by Tengku Kevin on 3/31/2017.
 */

public class ReimburseFragment extends Fragment {

    RecyclerView rvReimburse;
    ReimburseViewAdapter adapterChat;
    List<PettyCash> datasetPettyCash;

    public ReimburseFragment(){
        // Constructor
    }

    public static ReimburseFragment newInstance() {
        return new ReimburseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*INITIALIZE*/
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_reimburse);
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_reimburse, container, false);

        datasetPettyCash=new ArrayList<>();
        datasetPettyCash.add(new PettyCash("1001","driver01","Apr 1,12:15","Dikonfirmasi",252000));
        datasetPettyCash.add(new PettyCash("1002","driver01","Apr 3,19:47","Dikonfirmasi",92000));
        datasetPettyCash.add(new PettyCash("1003","driver01","Apr 5,07:33","Dikonfirmasi",52000));
        datasetPettyCash.add(new PettyCash("1004","driver01","Apr 19,13:40","Diproses",112000));

        rvReimburse = (RecyclerView)view.findViewById(R.id.rc_reimburse);
        rvReimburse.setLayoutManager(new LinearLayoutManager(getContext()));
        rvReimburse.setItemAnimator(new DefaultItemAnimator());

        adapterChat = new ReimburseViewAdapter(getContext(),datasetPettyCash);
        rvReimburse.setAdapter(adapterChat);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.reimburse, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_reimburse){
            Utilities.showAsToast(getContext(),"New Reimburse", Toast.LENGTH_SHORT);
        }
        return super.onOptionsItemSelected(item);
    }
}
