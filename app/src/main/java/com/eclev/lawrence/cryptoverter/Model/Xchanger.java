package com.eclev.lawrence.cryptoverter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SYSTEM on 10/10/2017.
 */

public class Xchanger {

    private BTC BTC;

    public BTC getBTC() { return this.BTC; }

    public void setBTC(BTC BTC) { this.BTC = BTC; }

    private ETH ETH;

    public ETH getETH() { return this.ETH; }

    public void setETH(ETH ETH) { this.ETH = ETH; }
//    @SerializedName("BTC")
//    @Expose
//    private BTC bTC;
//    @SerializedName("ETH")
//    @Expose
//    private ETH eTH;
//
//    public BTC getbTC() {
//        return this.bTC;
//    }
//
//    public void setbTC(BTC bTC) {
//        this.bTC = bTC;
//    }
//
//    public ETH geteTH() {
//        return this.eTH;
//    }
//
//    public void seteTH(ETH eTH) {
//        this.eTH = eTH;
//    }

}



