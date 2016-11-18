package com.zpauly.topnavigation;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 2016/11/16.
 */

public class NavParams implements Parcelable {
    private boolean isClosableOutside;

    private List<String> titles;

    private long animDuration = AnimUtils.ANIM_TIME;

    public void setClosableOutside(boolean closableOutside) {
        this.isClosableOutside = closableOutside;
    }

    public boolean isClosableOutside() {
        return isClosableOutside;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getTitles() {
        return titles;
    }

    public NavParams() {
        titles = new ArrayList<>();
    }

    public void setAnimDuration(long animDuration) {
        this.animDuration = animDuration;
    }

    public long getAnimDuration() {
        return animDuration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isClosableOutside ? (byte) 1 : (byte) 0);
        dest.writeLong(this.animDuration);
        dest.writeStringList(this.titles);
    }

    protected NavParams(Parcel in) {
        this.isClosableOutside = in.readByte() != 0;
        this.animDuration = in.readLong();
        this.titles = in.createStringArrayList();
    }

    public static final Creator<NavParams> CREATOR = new Creator<NavParams>() {
        @Override
        public NavParams createFromParcel(Parcel source) {
            return new NavParams(source);
        }

        @Override
        public NavParams[] newArray(int size) {
            return new NavParams[size];
        }
    };
}
