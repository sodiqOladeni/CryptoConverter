package com.example.android.cryptoconverter;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.cryptoconverter.QueryUtils;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class CryptoLoader extends AsyncTaskLoader<Double> {

    /** Tag for log messages */
    private static final String LOG_TAG = CryptoLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public CryptoLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public Double loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        Double earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}