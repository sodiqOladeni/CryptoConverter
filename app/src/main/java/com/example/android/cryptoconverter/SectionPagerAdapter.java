package com.example.android.cryptoconverter;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by ricHVision on 10/14/2017.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }



    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BTCFragment();
        } else{
            return new ETHFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.btc_layout);
        } else{
            return mContext.getString(R.string.eth_layout);
        }
    }
}