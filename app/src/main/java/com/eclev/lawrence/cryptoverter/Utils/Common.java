package com.eclev.lawrence.cryptoverter.Utils;

import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.Remote.RetrofitClient;

/**
 * Created by SYSTEM on 10/8/2017.
 */

public class Common {
    private static final String baseUrl = "https://min-api.cryptocompare.com";

    public static BitCurrency getBitCurrencyEquiv(){
        return RetrofitClient.getClient(baseUrl).create(BitCurrency.class);
    }

}
