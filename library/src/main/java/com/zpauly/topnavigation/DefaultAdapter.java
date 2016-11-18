package com.zpauly.topnavigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 2016/11/12.
 */

public class DefaultAdapter extends RecyclerView.Adapter<TopNavigation.DefaultVH> {
    private Context mContext;

    private OnNavItemClickedListener onNavItemClickedListener;

    protected List<String> mData;

    private int layout;

    public DefaultAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public DefaultAdapter(Context context, List<String> titles) {
        this(context);
        mData.addAll(titles);
    }

    public boolean addAll(List<String> items) {
        boolean result = mData.addAll(items);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    public boolean add(String d) {
        boolean result = mData.add(d);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return this.mData;
    }

    public void setOnNavItemClickedListener(OnNavItemClickedListener listener) {
        this.onNavItemClickedListener = listener;
    }

    @Override
    public TopNavigation.DefaultVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.default_mav_item_layout,
                parent, false);
        TopNavigation.DefaultVH viewholder = new TopNavigation.DefaultVH(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(TopNavigation.DefaultVH holder, final int position) {
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onNavItemClickedListener != null) {
                    onNavItemClickedListener.onClicked(view, position);
                }
            }
        });
        holder.mTitleTV.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Context getContext() {
        return this.mContext;
    }
}
