package com.example.android.cryptoconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by ricHVision on 10/27/2017.
 */

public class CrytoDetails extends AppCompatActivity{

    NumberFormat formattedValue = NumberFormat.getInstance(Locale.US);
    TextView selectCurrency, priceValueTextView, resultValue;
    EditText inputValue;
    Button calculate;
    String selecCurrency;
    Bundle intentReciever;
    String stringdouble;
    double inputedValue = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        selectCurrency = (TextView)findViewById(R.id.clicked_currency);
        priceValueTextView = (TextView) findViewById(R.id.price_value);
        resultValue = (TextView) findViewById(R.id.result_view);
        inputValue = (EditText) findViewById(R.id.input_view);
        calculate = (Button) findViewById(R.id.calculate_button);


        intentReciever = getIntent().getExtras();
        selecCurrency = intentReciever.getString("theCurrency");
        final double priceValue = intentReciever.getDouble("thePrice");



        selectCurrency.setText(selecCurrency);
        priceValueTextView.setText(formattedValue.format(priceValue));
        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (inputValue != null) {
                    inputedValue = Double.parseDouble(inputValue.getText().toString());
                    double inputAndPriceValue = (inputedValue * priceValue);
                    resultValue.setText(formattedValue.format(inputAndPriceValue));
                    Toast.makeText(getBaseContext(), "value is valid " + inputAndPriceValue, Toast.LENGTH_LONG).show();
                } else if (inputValue.getText().toString().trim().length() < 0) {
                    double inputAndPriceValue = (0.0 * priceValue);
                    String stringDoubleInputAndValue = Double.toString(inputAndPriceValue);
                    resultValue.setText(stringDoubleInputAndValue);
                    Toast.makeText(getBaseContext(), "value is valid " + inputAndPriceValue, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Not a Valid Input ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

