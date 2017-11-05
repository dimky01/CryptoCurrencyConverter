package com.example.dimky.cryptocurrencyconverter;

/**
 * Created by Administrator on 10/27/2017.
 */

public class CurrencyData {
    private String mLocalCurrency;
    private double mBTCLocalRate;
    private double mETHLocalRate;
    private String mCurrencyName;
    private int mCountryLogo;


    public CurrencyData(String localCurrency, double BTCLocalRate, double ETHLocalRate, String currencyName, int countryLogo) {
        mLocalCurrency = localCurrency;
        mBTCLocalRate = BTCLocalRate;
        mETHLocalRate = ETHLocalRate;
        mCurrencyName = currencyName;
        mCountryLogo = countryLogo;
    }

    public String getmLocalCurrency() {

        return mLocalCurrency;
    }

    public double getmBTCLocalRate() {

        return mBTCLocalRate;
    }

    public double getmETHLocalRate() {

        return mETHLocalRate;
    }

    public String getmCurrencyName() {

        return mCurrencyName;
    }

    public int getmCountryLogo() {
        return mCountryLogo;
    }

}
