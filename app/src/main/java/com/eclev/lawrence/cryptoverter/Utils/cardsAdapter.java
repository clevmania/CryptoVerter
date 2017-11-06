package com.eclev.lawrence.cryptoverter.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Model.currency;
import com.eclev.lawrence.cryptoverter.R;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.UI.ConversionActivity;

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
        final String currency  = baseCurrency.getBaseCurrency();
        holder.loadCurrentXchangeRate(currency);
        holder.btcXchangeView.setText(holder.getBtcx());
        holder.ethXchangeView.setText(holder.getEthx());
        holder.baseCurrency.setText(baseCurrency.getBaseCurrency());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent convertIntent = new Intent(mContext.getApplicationContext(), ConversionActivity.class);
                Bundle extras = new Bundle();
                if(holder.getBtcx()!= null && holder.getEthx()!= null){
                    extras.putDouble("btcXchange",Double.valueOf(holder.getBtcx()));
                    extras.putDouble("ethXchange", Double.valueOf(holder.getEthx()));
                    extras.putString("currency", currency);
                    convertIntent.putExtras(extras);
                    mContext.startActivity(convertIntent);
                }else{
                    Toast.makeText(mContext, "Network Error, card not loaded \n" +
                            "Check Your Network or Create A Card", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size();
    }

    static class cardHolder extends RecyclerView.ViewHolder {
        private View myView;
        TextView btcXchangeView, ethXchangeView, baseCurrency;
        private String btcx;
        private String ethx;
        private BitCurrency mBitCurrency;

        public cardHolder(View itemView) {
            super(itemView);
            myView = itemView;
            btcXchangeView = myView.findViewById(R.id.tv_xchange_amount);
            ethXchangeView = myView.findViewById(R.id.tv_eth_xchange_amount);
            baseCurrency = myView.findViewById(R.id.tv_currency);
            mBitCurrency  = Common.getBitCurrencyEquiv();
        }

        public String getBtcx() {
            return btcx;
        }

        public String getEthx() {
            return ethx;
        }

        public void loadCurrentXchangeRate(final String currency){
            mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
                @Override
                public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {
                    switch (currency){
                        case "USD":
                            btcx = String.valueOf(response.body().getbTC().getUSD());
                            ethx = String.valueOf(response.body().geteTH().getUSD());
                            break;
                        case "GBP":
                            btcx = String.valueOf(response.body().getbTC().getGBP());
                            ethx = String.valueOf(response.body().geteTH().getGBP());
                            break;
                        case "NGN":
                            btcx = String.valueOf(response.body().getbTC().getNGN());
                            ethx = String.valueOf(response.body().geteTH().getNGN());
                            break;
                        case "JPY":
                            btcx = String.valueOf(response.body().getbTC().getJPY());
                            ethx = String.valueOf(response.body().geteTH().getJPY());
                            break;
                        case "CNY":
                            btcx = String.valueOf(response.body().getbTC().getCNY());
                            ethx = String.valueOf(response.body().geteTH().getCNY());
                            break;
                        case "INR":
                            btcx = String.valueOf(response.body().getbTC().getINR());
                            ethx = String.valueOf(response.body().geteTH().getINR());
                            break;
                        case "SGD":
                            btcx = String.valueOf(response.body().getbTC().getSGD());
                            ethx = String.valueOf(response.body().geteTH().getSGD());
                            break;
                        case "KRW":
                            btcx = String.valueOf(response.body().getbTC().getKRW());
                            ethx = String.valueOf(response.body().geteTH().getKRW());
                            break;
                        case "AUD":
                            btcx = String.valueOf(response.body().getbTC().getAUD());
                            ethx = String.valueOf(response.body().geteTH().getAUD());
                            break;
                        case "EUR":
                            btcx = String.valueOf(response.body().getbTC().getEUR());
                            ethx = String.valueOf(response.body().geteTH().getEUR());
                            break;
                        case "RUB":
                            btcx = String.valueOf(response.body().getbTC().getRUB());
                            ethx = String.valueOf(response.body().geteTH().getRUB());
                            break;
                        case "ZAR":
                            btcx = String.valueOf(response.body().getbTC().getZAR());
                            ethx = String.valueOf(response.body().geteTH().getZAR());
                            break;
                        case "MXN":
                            btcx = String.valueOf(response.body().getbTC().getMXN());
                            ethx = String.valueOf(response.body().geteTH().getMXN());
                            break;
                        case "HKD":
                            btcx = String.valueOf(response.body().getbTC().getHKD());
                            ethx = String.valueOf(response.body().geteTH().getHKD());
                            break;
                        case "CAD":
                            btcx = String.valueOf(response.body().getbTC().getCAD());
                            ethx = String.valueOf(response.body().geteTH().getCAD());
                            break;
                        case "NZD":
                            btcx = String.valueOf(response.body().getbTC().getNZD());
                            ethx = String.valueOf(response.body().geteTH().getNZD());
                            break;
                        case "SEK":
                            btcx = String.valueOf(response.body().getbTC().getSEK());
                            ethx = String.valueOf(response.body().geteTH().getSEK());
                            break;
                        case "CHF":
                            btcx = String.valueOf(response.body().getbTC().getCHF());
                            ethx = String.valueOf(response.body().geteTH().getCHF());
                            break;
                        case "NOK":
                            btcx = String.valueOf(response.body().getbTC().getNOK());
                            ethx = String.valueOf(response.body().geteTH().getNOK());
                            break;
                        case "BRL":
                            btcx = String.valueOf(response.body().getbTC().getBRL());
                            ethx = String.valueOf(response.body().geteTH().getBRL());
                            break;
//                        default:
//                            break;
                    }
                }

                @Override
                public void onFailure(Call<Xchanger> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });
        }

    }

}
