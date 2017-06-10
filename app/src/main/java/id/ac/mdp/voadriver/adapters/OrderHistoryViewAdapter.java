package id.ac.mdp.voadriver.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.activities.OrderDetailActivity;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.models.UserOrder;
import id.ac.mdp.voadriver.utils.Utilities;

/**
 * Created by Tengku Kevin on 09/05/2017.
 */

public class OrderHistoryViewAdapter extends RecyclerView.Adapter<OrderHistoryViewAdapter.ViewHolderViewReimburse>{

    private Context context;
    private String[] username, address, time;
    private int[] profileImage;

    private List<UserOrder> datasetOrder;
    private List<User> datasetUser;

    /*public OrderHistoryViewAdapter(Context context, String[] username, String[] address, String[] time, int[] profileImage){
        this.context = context;
        this.username = username;
        this.address = address;
        this.time = time;
        this.profileImage = profileImage;
    }*/

    public OrderHistoryViewAdapter(Context context, List<UserOrder> datasetOrder, List<User> datasetUser){
        this.context = context;
        this.datasetOrder = datasetOrder;
        this.datasetUser = datasetUser;
    }

    @Override
    public OrderHistoryViewAdapter.ViewHolderViewReimburse onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, null);
        OrderHistoryViewAdapter.ViewHolderViewReimburse holder = new OrderHistoryViewAdapter.ViewHolderViewReimburse(view);

        return holder;
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, null);
//        return new OrderHistoryViewHolder(view);
    }

    OrderHistoryViewAdapter(Context context, String[] username, String[] address, String[] time, int[] profileImage){
        this.context = context;
        this.address =address;
        this.username=username;
        this.time=time;
        this.profileImage=profileImage;
    }

    @Override
    public void onBindViewHolder(OrderHistoryViewAdapter.ViewHolderViewReimburse holder, final int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_order_history, null);

        /*holder.tvOrderHistoryUserName.setText(username[position]);
        holder.tvOrderHistoryAddress.setText(address[position]);
        holder.tvOrderHistoryTime.setText(time[position]);
        //Glide.clear(holder.ivUserProfile);
        Glide.with(context)
                .load(profileImage[position])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivOrderHistoryUserProfile);*/

        holder.tvOrderHistoryUserName.setText(datasetOrder.get(position).getUser_id());
        holder.tvOrderHistoryAddress.setText(datasetOrder.get(position).getDestination_location());

        holder.tvOrderHistoryTime.setText(datasetOrder.get(position).getBooking_order().toString());
        //holder.tvOrderHistoryTime.setText(Utilities.getDateTime(datasetOrder.get(position).getBooking_order(),"yyyy:MM:dd"));
        //Glide.clear(holder.ivUserProfile);
        Glide.with(context)
                .load(R.drawable.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivOrderHistoryUserProfile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                /*intent.putExtra("orderUserName", username[position]);
                intent.putExtra("orderAddress", address[position]);
                intent.putExtra("orderTime", time[position]);
                intent.putExtra("orderProfil", profileImage[position]);*/

                intent.putExtra("datasetOrder", datasetOrder.get(position));
                intent.putExtra("datasetUser", datasetUser.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return username.length;
        return  datasetOrder.size();
    }

    class ViewHolderViewReimburse extends RecyclerView.ViewHolder {
        ImageView ivOrderHistoryUserProfile;
        TextView tvOrderHistoryUserName, tvOrderHistoryAddress, tvOrderHistoryTime;
        ViewHolderViewReimburse(View itemView) {
            super(itemView);
            ivOrderHistoryUserProfile = (ImageView)itemView.findViewById(R.id.img_cardview_order_history_usr_profile);
            tvOrderHistoryUserName = (TextView)itemView.findViewById(R.id.tv_cardview_order_history_username);
            tvOrderHistoryAddress = (TextView)itemView.findViewById(R.id.tv_cardview_order_history_address);
            tvOrderHistoryTime = (TextView)itemView.findViewById(R.id.tv_cardview_order_history_time);

        }
    }
}