package com.example.android.cryptoconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.android.cryptoconverter.R.id.main_spinner;
import static com.example.android.cryptoconverter.R.id.price_view;

public class ETHFragment extends Fragment implements
        LoaderCallbacks<Double> {

    private static final int EARTHQUAKE_LOADER_ID = 1;
    public String selected;
    public Double earthquakes;
    NumberFormat formattedValue = NumberFormat.getInstance(Locale.US);
    TextView priceTextView;
    String USGS_REQUEST_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=NGN";
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spinner_list_eth, container, false);

        ArrayList<String> currencyArray = new ArrayList<String>();
        currencyArray.add("NGN");
        currencyArray.add("USD");
        currencyArray.add("EUR");
        currencyArray.add("JPY");
        currencyArray.add("GBP");
        currencyArray.add("AUD");
        currencyArray.add("CAD");
        currencyArray.add("CHF");
        currencyArray.add("CNY");
        currencyArray.add("SEK");
        currencyArray.add("MXN");
        currencyArray.add("NZD");
        currencyArray.add("SGD");
        currencyArray.add("HKD");
        currencyArray.add("NOK");
        currencyArray.add("KRW");
        currencyArray.add("TRY");
        currencyArray.add("INR");
        currencyArray.add("RUB");
        currencyArray.add("BRL");

        final Spinner cryptoListView = rootView.findViewById(main_spinner);
        priceTextView = rootView.findViewById(price_view);
        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, R.id.simple_spinner_item, currencyArray);
        cryptoListView.setAdapter(mAdapter);

        cryptoListView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = cryptoListView.getSelectedItem().toString();
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.restartLoader(EARTHQUAKE_LOADER_ID, null, ETHFragment.this);
                USGS_REQUEST_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=" + selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        TextView open = rootView.findViewById(R.id.open_view);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), CrytoDetails.class);
                Bundle testing = new Bundle();
                testing.putString("theCurrency", selected);
                testing.putDouble("thePrice", earthquakes);
                newIntent.putExtras(testing);
                startActivity(newIntent);
            }
        });
        return rootView;
    }

    @Override
    public Loader<Double> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new CryptoLoader(getActivity(), USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<Double> loader, Double earthquakes) {

        priceTextView.setText(formattedValue.format(earthquakes));
        this.earthquakes = earthquakes;
    }

    @Override
    public void onLoaderReset(Loader<Double> loader) {

    }
}

