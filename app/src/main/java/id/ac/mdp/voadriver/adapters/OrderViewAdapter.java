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
import id.ac.mdp.voadriver.utils.Utilities;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.models.UserOrder;

/*
 * Created by Tengku Kevin on 3/26/2017.
 */

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.ViewHolderReimburse>{

    private Context context;
    private String[] username, address, time;
    private int[] profileImage;
    private List<UserOrder> datasetOrder;
    private List<User> datasetUser;

    public OrderViewAdapter(Context context, List<UserOrder> datasetOrder, List<User> datasetUser){
        this.context = context;
        this.datasetOrder = datasetOrder;
        this.datasetUser = datasetUser;
    }

    OrderViewAdapter(Context context, String[] username, String[] address, String[] time, int[] profileImage){
        this.context = context;
        this.address =address;
        this.username=username;
        this.time=time;
        this.profileImage=profileImage;
    }

    @Override
    public ViewHolderReimburse onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, null);
        return new ViewHolderReimburse(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderReimburse holder, final int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_order, null);

        holder.tvUserName.setText(datasetOrder.get(position).getUser_id());
        holder.tvAddress.setText(datasetOrder.get(position).getDestination_location());
        //holder.tvTime.setText(Utilities.getDateTime(datasetOrder.get(position).getBooking_order(),"yyyy:MM:dd"));
        holder.tvTime.setText(datasetOrder.get(position).getBooking_order().toString());
        //Glide.clear(holder.ivUserProfile);
        Glide.with(context)
                .load(R.drawable.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivUserProfile);

        /*holder.tvUserName.setText(username[position]);
        holder.tvAddress.setText(address[position]);
        holder.tvTime.setText(time[position]);
        //Glide.clear(holder.ivUserProfile);
        Glide.with(context)
                .load(profileImage[position])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivUserProfile);*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("datasetOrder", datasetOrder.get(position));
                intent.putExtra("datasetUser", datasetUser.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datasetOrder.size();
    }

    class ViewHolderReimburse extends RecyclerView.ViewHolder {
        ImageView ivUserProfile;
        TextView tvUserName, tvAddress, tvTime;
        ViewHolderReimburse(View itemView) {
            super(itemView);
            ivUserProfile = (ImageView)itemView.findViewById(R.id.img_cardview_order_usr_profile);
            tvUserName = (TextView)itemView.findViewById(R.id.tv_cardview_order_username);
            tvAddress = (TextView)itemView.findViewById(R.id.tv_cardview_order_address);
            tvTime = (TextView)itemView.findViewById(R.id.tv_cardview_order_time);
        }
    }
}
