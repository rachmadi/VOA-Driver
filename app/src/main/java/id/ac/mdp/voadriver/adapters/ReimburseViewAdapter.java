package id.ac.mdp.voadriver.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.activities.ReimburseViewActivity;
import id.ac.mdp.voadriver.models.PettyCash;

/**
 * Created by Marhadi Wijaya on 5/9/2017.
 */

public class ReimburseViewAdapter extends RecyclerView.Adapter<ReimburseViewAdapter.ViewHolderReimburse>{

    private Context context;
    private List<PettyCash> datasetPettyCash;

    public ReimburseViewAdapter(Context context, List<PettyCash> datasetPettyCash){
        this.context = context;
        this.datasetPettyCash = datasetPettyCash;
    }

    @Override
    public ViewHolderReimburse onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reimburse, null);
        return new ViewHolderReimburse(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderReimburse holder, final int position) {
        holder.tvReimburseID.setText("ID : "+datasetPettyCash.get(position).getPetty_cash_id());
        holder.tvReimburseStatus.setText(datasetPettyCash.get(position).getStatus());
        holder.tvReimburseTimestamp.setText(datasetPettyCash.get(position).getTimestamp());
        holder.tvReimburseAmount.setText(String.valueOf(datasetPettyCash.get(position).getAmount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReimburseViewActivity.class);
                intent.putExtra("pettyCashID", datasetPettyCash.get(position).getPetty_cash_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datasetPettyCash.size();
    }

    class ViewHolderReimburse extends RecyclerView.ViewHolder {
        TextView tvReimburseID, tvReimburseStatus, tvReimburseTimestamp, tvReimburseAmount;
        ViewHolderReimburse(View itemView) {
            super(itemView);
            tvReimburseID = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_id);
            tvReimburseStatus = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_status);
            tvReimburseTimestamp = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_timestamp);
            tvReimburseAmount = (TextView)itemView.findViewById(R.id.tv_listview_reimburse_amount);
        }
    }
}

