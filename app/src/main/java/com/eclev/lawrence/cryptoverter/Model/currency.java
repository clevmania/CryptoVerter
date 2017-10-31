package com.eclev.lawrence.cryptoverter.Model;

/**
 * Created by Lawrence on 10/21/2017.
 */

public class currency {
    private String baseCurrency;
    private String btcXchangeRate;
    private String ethXchangeRate;

    public currency(String bcurr){
        baseCurrency = bcurr;
    }

    public currency(String btcXchangeRate, String ethXchangeRate) {
        this.btcXchangeRate = btcXchangeRate;
        this.ethXchangeRate = ethXchangeRate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBtcXchangeRate() {
        return btcXchangeRate;
    }

    public void setBtcXchangeRate(String btcXchangeRate) {
        this.btcXchangeRate = btcXchangeRate;
    }

    public String getEthXchangeRate() {
        return ethXchangeRate;
    }

    public void setEthXchangeRate(String ethXchangeRate) {
        this.ethXchangeRate = ethXchangeRate;
    }
}
