package com.example.dimky.cryptocurrencyconverter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/2/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

   public static final String KEY_LOCALCURRENCY = "local_currency";
   public static final String KEY_BTC = "btc";
   public static final String KEY_ETH = "eth";
   public static final String BTC_EXCHANGE = "btc_rate";
   public static final String ETH_EXCHANGE = "eth_rate";


    //declaration of an arraylist from the DataSet Java Class
    private ArrayList<CurrencyData> mDataSet;
    private Context context;


    //Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Instance of the view elements
        TextView mLocalCurrency, mCurrencyName, mBTCExchangeRate, mETHExchangeRate;
        ImageView mCountryLogo;
        LinearLayout mLinearLayout;

        public ViewHolder(View dataView) {
            super(dataView);

            //define view objects
            this.mLocalCurrency = (TextView) dataView.findViewById(R.id.local_currency);
            this.mBTCExchangeRate = (TextView) dataView.findViewById(R.id.BTCExchange_rate);
            this.mETHExchangeRate = (TextView) dataView.findViewById(R.id.ETHExchange_rate);
            this.mCurrencyName = (TextView) dataView.findViewById(R.id.currency_name);
            this.mCountryLogo = (ImageView) dataView.findViewById(R.id.country_logo);
            this.mLinearLayout = (LinearLayout) dataView.findViewById(R.id.linear_layout);
        }
    }


    //Provide a suitable constructor
    public CustomAdapter (ArrayList<CurrencyData> dataSet, Context context) {
        this.mDataSet = dataSet;
        this.context = context;
    }


    //Inflating new ViewGroup on ViewHolder (invoked by the Layout manager)
    //Method to be called whenever ViewHolder is created
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exchange_rate, parent, false);

        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }


    //to bind data to the ViewHolder from where it will be shown to other views
        public void onBindViewHolder (ViewHolder holder, final int position){
        final CurrencyData DataSets = mDataSet.get(position);
        holder.mLocalCurrency.setText(DataSets.getmLocalCurrency());
        holder.mBTCExchangeRate.setText(String.format("%1$,.2f", DataSets.getmBTCLocalRate()));
        holder.mETHExchangeRate.setText(String.format("%1$,.2f", DataSets.getmETHLocalRate()));
        holder.mCurrencyName.setText(DataSets.getmCurrencyName());
        Picasso.with(context)
                .load(DataSets.getmCountryLogo())
                .into(holder.mCountryLogo);

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyData DataSet = mDataSet.get(position);
                Intent skipIntent = new Intent(v.getContext(), ConversionActivity.class);
                skipIntent.putExtra(BTC_EXCHANGE, DataSet.getmBTCLocalRate());
                skipIntent.putExtra(ETH_EXCHANGE, DataSet.getmETHLocalRate());
                skipIntent.putExtra(KEY_LOCALCURRENCY, DataSet.getmLocalCurrency());
                v.getContext().startActivity(skipIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
            return mDataSet.size();
    }


}
