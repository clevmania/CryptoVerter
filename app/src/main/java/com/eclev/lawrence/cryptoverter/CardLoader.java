package com.eclev.lawrence.cryptoverter;

import android.util.Log;
import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lawrence on 10/25/2017.
 */

public class CardLoader{
    private BitCurrency mBitCurrency;
    public String btcCurrentRate;
    public String ethCurrentRate;
    private String currency;


    public CardLoader(final String currency) {
        this.currency = currency;
        mBitCurrency = Common.getBitCurrencyEquiv();

        mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
            @Override
            public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {

                if(currency == "USD"){
                    btcCurrentRate = String.valueOf(response.body().getBTC().getUSD());
                    ethCurrentRate = String.valueOf(response.body().getETH().getUSD());
//                    setBtcCurrentRate(String.valueOf(response.body().getBTC().getUSD()));
//                    setEthCurrentRate(String.valueOf(response.body().getETH().getUSD()));
//                    Log.i("CardLoader.class",btcCurrentRate);
//                    Log.i("CardLoader.class",ethCurrentRate);
                    Log.i("CardLoader.class",currency);
                    Log.i("CardLoader.class",getBtcCurrentRate());
                    Log.i("CardLoader.class",getEthCurrentRate());
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
