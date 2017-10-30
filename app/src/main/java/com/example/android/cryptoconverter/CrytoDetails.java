package com.example.android.cryptoconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ricHVision on 10/27/2017.
 */

public class CrytoDetails extends AppCompatActivity{

    TextView selectCurrency, priceValue, resultValue;
    EditText inputValue;
    double finalResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        selectCurrency = (TextView)findViewById(R.id.clicked_currency);
        priceValue = (TextView) findViewById(R.id.price_view);
        resultValue = (TextView) findViewById(R.id.result_view);
        inputValue = (EditText) findViewById(R.id.input_view);
        int finalValue=Integer.parseInt(inputValue.toString());

        Bundle intentReciever = getIntent().getExtras();
        String selecCurrency = intentReciever.getString("theCurrency");
        Double unitPrice =(Double) intentReciever.getDouble("price");


        selectCurrency.setText(selecCurrency);
        priceValue.setText("" + unitPrice);


    }
}

