package com.zpauly.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zpauly on 2016/11/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context mContext;
    private int mNum;

    public CustomAdapter(Context context, int adapterNum) {
        this.mContext = context;
        this.mNum = adapterNum;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_nav_item_layout, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (mNum == 0) {
            holder.mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else if (mNum == 1) {
            holder.mLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.background_light));
        }
        switch (position) {
            case 0:
                holder.mNavIV.setImageResource(R.drawable.ic_account_circle_black_24dp);
                holder.mNavTV.setText("ACCOUNT");
                break;
            case 1:
                holder.mNavIV.setImageResource(R.drawable.ic_event_black_24dp);
                holder.mNavTV.setText("EVENTS");
                break;
            case 2:
                holder.mNavIV.setImageResource(R.drawable.ic_comment_black_24dp);
                holder.mNavTV.setText("COMMENTS");
                break;
            case 3:
                holder.mNavIV.setImageResource(R.drawable.ic_mail_black_24dp);
                holder.mNavTV.setText("MAILS");
                break;
            case 4:
                holder.mNavIV.setImageResource(R.drawable.ic_settings_black_24dp);
                holder.mNavTV.setText("SETTINGS");
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout mLayout;
        public final ImageView mNavIV;
        public final TextView mNavTV;

        public CustomViewHolder(View itemView) {
            super(itemView);

            mLayout = (LinearLayout) itemView.findViewById(R.id.custom_item_layout);
            mNavIV = (ImageView) itemView.findViewById(R.id.nav_IV);
            mNavTV = (TextView) itemView.findViewById(R.id.nav_TV);
        }
    }
}
