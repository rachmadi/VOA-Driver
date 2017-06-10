package id.ac.mdp.voadriver.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.ac.mdp.voadriver.R;

/**
 * Created by Tengku Kevin on 4/21/2017.
 */

public class NotificationViewAdapter extends RecyclerView.Adapter<NotificationViewAdapter.NotificationViewHolder>{

    private Context context;
    private String[] title, description, time;
    private int[] image;

    public NotificationViewAdapter(Context context, String[] username, String[] address, String[] time, int[] profileImage){
        this.context = context;
        this.title = username;
        this.description = address;
        this.time = time;
        this.image = profileImage;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, null);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationViewHolder holder, final int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_notification, null);

        holder.tvNotificationTitle.setText(title[position]);
        holder.tvNotificationDesc.setText(description[position]);
        //Glide.clear(holder.ivUserProfile);
        Glide.with(context)
                .load(image[position])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivNotificationImage);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNotificationImage;
        TextView tvNotificationTitle, tvNotificationDesc, tvNotificationTime, tvRating;
        NotificationViewHolder(View itemView) {
            super(itemView);
            ivNotificationImage = (ImageView)itemView.findViewById(R.id.img_cardview_notification);
            tvNotificationTitle = (TextView)itemView.findViewById(R.id.tv_cardview_notification_title);
            tvNotificationDesc = (TextView)itemView.findViewById(R.id.tv_cardview_notification_desc);
            tvNotificationTime = (TextView)itemView.findViewById(R.id.tv_cardview_notification_time);

        }
    }
}