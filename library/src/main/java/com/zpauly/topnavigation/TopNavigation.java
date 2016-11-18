package com.zpauly.topnavigation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zpauly on 2016/11/12.
 */

public class TopNavigation extends DialogFragment {
    private static final String TAG = TopNavigation.class.getName();

    public static final String NAV_PARAMS = "NAV_PARAMS";

    private static FragmentManager fragmentManager;

    private View mView;

    private TopNavigationLayout mNavLayout;
    private RecyclerView mNavRV;

    private NavParams mNavParams;
    private boolean canClickCancel;
    private List<String> titles;
    private boolean resetAdapter;
    private long animDuration;

    private RecyclerView.Adapter mAdapter;
    private static DefaultAdapter mNavAdapter;

    private OnNavStateChangingListener onNavStateChangingListener;

    public static TopNavigation create(AppCompatActivity activity,
                                       NavParams navParams) {
        fragmentManager = activity.getSupportFragmentManager();
        TopNavigation view = new TopNavigation();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NAV_PARAMS, navParams);
        view.setArguments(bundle);
        mNavAdapter = new DefaultAdapter(activity);
        return view;
    }

    public TopNavigation() {
        super();
    }

    public void open() {
        show(fragmentManager, TAG);
        if (onNavStateChangingListener != null) {
            onNavStateChangingListener.onOpening();
        }
    }

    public void close() {
        mNavLayout.close();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, animDuration);
        if (onNavStateChangingListener != null) {
            onNavStateChangingListener.onClosing();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MenuFragmentStyle);
        getParams();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.top_nav_layout, container, false);
        mNavLayout = (TopNavigationLayout) mView.findViewById(R.id.nav_layout);
        mNavRV = (RecyclerView) mView.findViewById(R.id.nav_RV);
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        initViews();
        return mView;
    }

    public void setOnNavStateChangingListener(OnNavStateChangingListener listener) {
        this.onNavStateChangingListener = listener;
    }

    private void getParams() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mNavParams = bundle.getParcelable(NAV_PARAMS);
            if (mNavParams != null) {
                canClickCancel = mNavParams.isClosableOutside();
                titles = mNavParams.getTitles();
                animDuration = mNavParams.getAnimDuration();
            }
        }
    }

    private void initViews() {
        setupRecyclerView();
        setupLayout();
    }

    private void setupLayout() {
        if (canClickCancel) {
            mNavLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    close();
                }
            });
        }
        mNavLayout.resetAnimDuration(animDuration);
    }

    private void setupRecyclerView() {
        mNavRV.setHasFixedSize(true);
        mNavRV.setLayoutManager(new LinearLayoutManager(getContext()));
        if (resetAdapter) {
            mNavRV.setAdapter(mAdapter);
        } else {
            mNavRV.setAdapter(mNavAdapter);
            mNavAdapter.clear();
            mNavAdapter.addAll(titles);
        }
    }

    public void resetAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        resetAdapter = true;
    }

    public DefaultAdapter getDefaultAdapter() {
        return mNavAdapter;
    }

    public static class DefaultVH extends RecyclerView.ViewHolder {
        public final TextView mTitleTV;

        public DefaultVH(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.default_nav_item_title_TV);
        }
    }
}
