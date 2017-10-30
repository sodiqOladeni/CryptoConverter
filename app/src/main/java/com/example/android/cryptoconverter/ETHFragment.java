package com.example.android.cryptoconverter;

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
import static com.example.android.cryptoconverter.R.id.main_spinner;
import static com.example.android.cryptoconverter.R.id.price_view;

public class ETHFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Double>  {

    TextView priceTextView;
    private ArrayAdapter<String> mAdapter;
    private static final String USGS_REQUEST_URL =
            "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spinner_list, container, false);

        String [] currencyArray = new String[20];
        currencyArray[0] = "NGN";
        currencyArray[1] = "USD";
        currencyArray[2] = "EUR";
        currencyArray[3] = "JPY";
        currencyArray[4] = "GBP";
        currencyArray[5] = "AUD";
        currencyArray[6] = "CAD";
        currencyArray[7] =  "CHF";
        currencyArray[8] =  "CNY";
        currencyArray[9] =  "SEK";
        currencyArray[10] =  "MXN";
        currencyArray[11] =  "NZD";
        currencyArray[12] =  "SGD";
        currencyArray[13] =  "HKD";
        currencyArray[14] =  "NOK";
        currencyArray[15] =  "KRW";
        currencyArray[16] =  "TRY";
        currencyArray[17] =  "INR";
        currencyArray[18] =  "RUB";
        currencyArray[19] =  "BRL";

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        Spinner cryptoListView = rootView.findViewById(main_spinner);
        priceTextView = rootView.findViewById(price_view);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, currencyArray);
        cryptoListView.setAdapter(mAdapter);

        cryptoListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        // Loader reset, so we can clear out our existing data.

    }

}

