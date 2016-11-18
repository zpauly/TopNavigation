package com.zpauly.topnavigation;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zpauly on 2016/11/12.
 */

public class TopNavigationBuilder {
    private static TopNavigationBuilder instance;

    private AppCompatActivity mActivity;

    private NavParams mParams;

    private TopNavigationBuilder(AppCompatActivity activity) {
        this.mActivity = activity;
        mParams = new NavParams();
    }

    public static TopNavigationBuilder withActivity(AppCompatActivity activity) {
        instance = new TopNavigationBuilder(activity);
        return instance;
    }

    public TopNavigationBuilder closableOutside(boolean closableOutside) {
        mParams.setClosableOutside(closableOutside);
        return this;
    }

    public TopNavigationBuilder titles(List<String> titles) {
        mParams.setTitles(titles);
        return this;
    }

    public TopNavigationBuilder animDuration(long duration) {
        mParams.setAnimDuration(duration);
        return this;
    }

    public TopNavigation build() {
        return TopNavigation.create(mActivity, mParams);
    }
}
