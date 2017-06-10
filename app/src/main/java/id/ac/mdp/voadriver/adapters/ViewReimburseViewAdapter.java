package id.ac.mdp.voadriver.adapters;

import android.content.Context;
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
import id.ac.mdp.voadriver.models.PettyCashUsage;

/**
 * Created by Marhadi Wijaya on 5/10/2017.
 */

public class ViewReimburseViewAdapter extends RecyclerView.Adapter<ViewReimburseViewAdapter.ViewHolderViewReimburse>{
    private Context context;
    private List<PettyCashUsage> datasetPettyCashUsage;

    public ViewReimburseViewAdapter(Context context, List<PettyCashUsage> datasetPettyCashUsage){
        this.context = context;
        this.datasetPettyCashUsage = datasetPettyCashUsage;
    }

    @Override
    public ViewReimburseViewAdapter.ViewHolderViewReimburse onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reimburse_view, null);
        return new ViewReimburseViewAdapter.ViewHolderViewReimburse(view);
    }

    @Override
    public void onBindViewHolder(final ViewReimburseViewAdapter.ViewHolderViewReimburse holder, final int position) {
        Glide.with(context)
                .load(R.drawable.nota)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivReimburseViewPicture);
        holder.tvReimburseViewDescription.setText(datasetPettyCashUsage.get(position).getDescription());
        holder.tvReimburseViewTimestamp.setText(datasetPettyCashUsage.get(position).getTimestamp());
        holder.tvReimburseViewAmount.setText(String.valueOf(datasetPettyCashUsage.get(position).getAmount()));

        holder.ivReimburseViewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datasetPettyCashUsage.size();
    }

    class ViewHolderViewReimburse extends RecyclerView.ViewHolder {
        ImageView ivReimburseViewPicture;
        TextView tvReimburseViewDescription, tvReimburseViewTimestamp, tvReimburseViewAmount;
        ViewHolderViewReimburse(View itemView) {
            super(itemView);
            ivReimburseViewPicture = (ImageView) itemView.findViewById(R.id.iv_reimburse_view_picture);
            tvReimburseViewDescription = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_view_description);
            tvReimburseViewTimestamp = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_view_timestamp);
            tvReimburseViewAmount = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_view_amount);
        }
    }
}
