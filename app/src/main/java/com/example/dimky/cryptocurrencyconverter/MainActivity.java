package com.example.dimky.cryptocurrencyconverter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<CurrencyData> mCurrencyData;


    TextView cryptoCurrency;
    TextView conversionRate;
    TextView sample;
    RequestQueue requestQueue;
    final static String data_url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=" +
            "AUD,USD,BRL,CAD,CNY,CZK,EGP,EUR,GBP,HKD,HUF,TRY,ILS,INR,JPY,KPW,MXN,MYP,NGN,ZAR";
    double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //used to indicate changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //using a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Create an empty ArrayList that we can start adding parsed data to
        mCurrencyData = new ArrayList<>();

        loadUrlData();


    }

    //Method loads the rates of the 20 currencies in the URL, receives JSON response, parses response and displays rates
    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading........");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, data_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject btc_currency = response.getJSONObject("BTC".trim());
                            JSONObject eth_currency = response.getJSONObject("ETH".trim());

                            Iterator<String> btcKeysIterator = btc_currency.keys();
                            Iterator<String> ethKeysIterator = eth_currency.keys();

                            while (btcKeysIterator.hasNext() && ethKeysIterator.hasNext()) {
                                String keyBTC = (String) btcKeysIterator.next();
                                String keyETH = (String) ethKeysIterator.next();
                                double btcExchangeValue = btc_currency.getDouble(keyBTC);
                                double ethExchangeValue = eth_currency.getDouble(keyETH);

                                CurrencyData currencyData = new CurrencyData(keyBTC,
                                        btcExchangeValue,
                                        ethExchangeValue,
                                        getCurrencyName(keyBTC),
                                        getCurrencyLogo(keyBTC));

                                mCurrencyData.add(currencyData);

                            }

                            mAdapter = new CustomAdapter(mCurrencyData, getApplicationContext());
                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        );


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public String getCurrencyName(String currencyCode) {
        switch (currencyCode) {
            case "EUR":
                return "Euro";
            case "USD":
                return "US Dollar";
            case "GBP":
                return "British Pound";
            case "CAD":
                return "Canadian Dollar";
            case "NGN":
                return "Nigerian Naira";
            case "AUD":
                return "Australian Dollar";
            case "JPY":
                return "Japanese Yen";
            case "CZK":
                return "Czech Koruna";
            case "CNY":
                return "Chinese Yuan";
            case "ZAR":
                return "South African Rand";
            case "INR":
                return "Indian Rupee";
            case "MXN":
                return "Mexican Peso";
            case "HKD":
                return "Hong Kong Dollar";
            case "HUF":
                return "Hungarian Forint";
            case "BRL":
                return "Brazilian Real";
            case "TRY":
                return "Turkish lira";
            case "EGP":
                return "Egypt Pound";
            case "ILS":
                return "Isreali Shekel";
            case "KPW":
                return "South Korean Won";
            case "MYR":
                return "Malaysian Ringgit";
            default:
                return "";

        }
    }

    public int getCurrencyLogo(String currencyCode) {
        switch (currencyCode) {
            case "EUR":
                return R.drawable.eurp_flag;
            case "USD":
                return R.drawable.usa;
            case "GBP":
                return R.drawable.brazil_flag;
            case "CAD":
                return R.drawable.canada_flag;
            case "NGN":
                return R.drawable.nigeria_flag;
            case "AUD":
                return R.drawable.australia_flag;
            case "JPY":
                return R.drawable.japan_flag;
            case "CZK":
                return R.drawable.czech_flag;
            case "CNY":
                return R.drawable.china_flag;
            case "ZAR":
                return R.drawable.southafrica_flag;
            case "INR":
                return R.drawable.india_flag;
            case "MXN":
                return R.drawable.mexico_flag;
            case "HKD":
                return R.drawable.hongkong_flag;
            case "HUF":
                return R.drawable.hungary_flag;
            case "BRL":
                return R.drawable.brazil_flag;
            case "TRY":
                return R.drawable.turkey_flag;
            case "EGP":
                return R.drawable.egypt_flag;
            case "ILS":
                return R.drawable.israel_flag;
            case "KPW":
                return R.drawable.southkorea_flag;
            case "MYR":
                return R.drawable.malaysian_flag;
            default:
                return R.drawable.usa;

        }
    }
}