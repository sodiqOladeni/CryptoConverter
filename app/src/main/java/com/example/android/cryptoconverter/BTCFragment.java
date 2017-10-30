package com.example.android.cryptoconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;
import static com.example.android.cryptoconverter.R.id.main_spinner;
import static com.example.android.cryptoconverter.R.id.price_view;

public class BTCFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Double>  {

    TextView priceTextView;
    public String selected;
    private ArrayAdapter<String> mAdapter;
     String USGS_REQUEST_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=NGN";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spinner_list, container, false);

        ArrayList<String>  currencyArray = new ArrayList<String>();
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

        Spinner cryptoListView = rootView.findViewById(main_spinner);
       priceTextView = rootView.findViewById(price_view);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, currencyArray);
        cryptoListView.setAdapter(mAdapter);

        cryptoListView.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 selected = adapterView.getItemAtPosition(i).toString();
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
                testing.putString("price", String.valueOf(priceTextView));
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

             priceTextView.setText(""+earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<Double> loader) {

    }

}

