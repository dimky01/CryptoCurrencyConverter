package com.example.dimky.cryptocurrencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 11/4/2017.
 */


public class ConversionActivity extends AppCompatActivity {

    TextView BTCInputValueView,
            ETHInputValueView,
            BTCconvertedValueView,
            ETHconvertedValueView,
            selectedCurrencyBTCView,
            selectedCurrencyETHView;

    Button convertBTC,
            convertETH;

    double BTCValue = 0.0;
    double ETHValue =0.0;
    double convertedBTCValue = 0.0;
    double convertedETHValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity);

        BTCInputValueView =(TextView) findViewById(R.id.BTC_inputted_value);
        ETHInputValueView =(TextView) findViewById(R.id.ETH_inputted_value);
        BTCconvertedValueView = (TextView) findViewById(R.id.BTC_converted_value);
        ETHconvertedValueView = (TextView) findViewById(R.id.ETH_converted_value);
        selectedCurrencyBTCView = (TextView) findViewById(R.id.selected_currency_BTC);
        selectedCurrencyETHView = (TextView) findViewById(R.id.selected_currency_ETH);
        convertBTC = (Button) findViewById(R.id.convert_btc);
        convertETH = (Button) findViewById(R.id.convert_eth);

        Intent intent = getIntent();
        final String selectedCurrency = intent.getStringExtra(CustomAdapter.KEY_LOCALCURRENCY);
        final double BTCExchangeRate = intent.getDoubleExtra(CustomAdapter.BTC_EXCHANGE, 0);
        final double ETHExchangeRate = intent.getDoubleExtra(CustomAdapter.ETH_EXCHANGE, 0);


        selectedCurrencyBTCView.setText(selectedCurrency);
        selectedCurrencyETHView.setText(selectedCurrency);


        convertBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double btcValue = Double.parseDouble(BTCInputValueView.getText().toString());
                btcValue = btcValue * BTCExchangeRate;
                BTCconvertedValueView.setText(String.format("%1$,.2f", btcValue));

            }
        });


        convertETH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ethValue = Double.parseDouble(ETHInputValueView.getText().toString());
                ethValue = ethValue * ETHExchangeRate;
                ETHconvertedValueView.setText(String.format("%1$,.2f", ethValue));

            }
        });



    }
}
