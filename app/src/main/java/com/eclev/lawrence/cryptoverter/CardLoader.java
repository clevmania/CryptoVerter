package com.eclev.lawrence.cryptoverter;

import android.util.Log;
import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lawrence on 10/25/2017.
 */

public class CardLoader{
    private BitCurrency mBitCurrency;
    private String btcCurrentRate;
    private String ethCurrentRate;


    public CardLoader(final String currency) {
        mBitCurrency = Common.getBitCurrencyEquiv();

        mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
            @Override
            public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {

                switch (currency){
                    case "USD":
                        btcCurrentRate = String.valueOf(response.body().getbTC().getUSD());
                        ethCurrentRate = String.valueOf(response.body().geteTH().getUSD());
                        Log.i("CardLoader.class",currency);
                        Log.i("CardLoader.class",getBtcCurrentRate());
                        Log.i("CardLoader.class",getEthCurrentRate());
                        break;
                    default:
                        btcCurrentRate = "1234";
                        ethCurrentRate = "5678";
                        Log.i("CardLoader.class",getBtcCurrentRate());
                        Log.i("CardLoader.class",getEthCurrentRate());
                        break;
                }
            }

            @Override
            public void onFailure(Call<Xchanger> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public String getBtcCurrentRate() {
        return btcCurrentRate;
    }

    public void setBtcCurrentRate(String btcCurrentRate) {
        this.btcCurrentRate = btcCurrentRate;
    }

    public String getEthCurrentRate() {
        return ethCurrentRate;
    }

    public void setEthCurrentRate(String ethCurrentRate) {
        this.ethCurrentRate = ethCurrentRate;
    }


}
