package com.eclev.lawrence.cryptoverter.Remote;

//import com.eclev.lawrence.cryptoverter.Model.Btc;
import com.eclev.lawrence.cryptoverter.Model.Xchanger;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SYSTEM on 10/8/2017.
 */

public interface BitCurrency {
    @GET("/data/pricemulti?fsyms=BTC,ETH")
    Call<Xchanger> getXchangeRate(@Query("tsyms") String currencyCode);

}
