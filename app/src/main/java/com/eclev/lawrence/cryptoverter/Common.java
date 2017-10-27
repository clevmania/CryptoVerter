package com.eclev.lawrence.cryptoverter;

import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.Remote.RetrofitClient;

/**
 * Created by SYSTEM on 10/8/2017.
 */

public class Common {
//    https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD
    private static final String baseUrl = "https://min-api.cryptocompare.com";

    public static BitCurrency getBitCurrencyEquiv(){
        return RetrofitClient.getClient(baseUrl).create(BitCurrency.class);
    }
}
