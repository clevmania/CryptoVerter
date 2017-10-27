package com.eclev.lawrence.cryptoverter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lawrence on 10/21/2017.
 */

public class cardsAdapter extends RecyclerView.Adapter<cardsAdapter.cardHolder>{
    private Context mContext;
    private List<currency> mCurrencyList;
//    BitCurrency mBitCurrency;
//    AlertDialog mDialog;
//    String btcrate, ethrate;
    currency baseCurrency;

    public cardsAdapter(Context context, List<currency> currencyList){
        mContext = context;
        mCurrencyList = currencyList;
    }

    @Override
    public cardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        int layoutFromID = R.layout.card_list;
        boolean shouldAttachToParent = false;
        View view = inflater.inflate(layoutFromID,parent,shouldAttachToParent);

        cardHolder holder = new cardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final cardHolder holder, int position) {
        baseCurrency = mCurrencyList.get(position);
        Log.i("BaseCurrency", baseCurrency.getBaseCurrency());
        final String currency  = baseCurrency.getBaseCurrency();
        CardLoader mcardLoader = new CardLoader(currency);
        switch(currency){
            case "USD":
                holder.btcXchangeView.setText(String.valueOf(mcardLoader.getBtcCurrentRate()));
                holder.ethXchangeView.setText(String.valueOf(mcardLoader.getEthCurrentRate()));
                holder.baseCurrency.setText(baseCurrency.getBaseCurrency());
                Log.i("test",""+mcardLoader.getBtcCurrentRate());
//                Log.i("CardsAdapter.class", currency);
            break;
            default:
                holder.btcXchangeView.setText("6677");
                holder.ethXchangeView.setText("303");
                holder.baseCurrency.setText(baseCurrency.getBaseCurrency());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size();
    }

    class cardHolder extends RecyclerView.ViewHolder {
        private View myView;
        TextView btcXchangeView, ethXchangeView, baseCurrency;

        public cardHolder(View itemView) {
            super(itemView);
            myView = itemView;
            btcXchangeView = myView.findViewById(R.id.tv_xchange_amount);
            ethXchangeView = myView.findViewById(R.id.tv_eth_xchange_amount);
            baseCurrency = myView.findViewById(R.id.tv_currency);
        }

    }


}
