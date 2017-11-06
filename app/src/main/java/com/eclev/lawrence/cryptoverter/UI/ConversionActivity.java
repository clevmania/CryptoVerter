package com.eclev.lawrence.cryptoverter.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eclev.lawrence.cryptoverter.R;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.Utils.Common;

import dmax.dialog.SpotsDialog;

public class ConversionActivity extends AppCompatActivity {
    BitCurrency mBitCurrency;
    TextView tvRateBtc, tvRateEth, tvBtc, tvEth;
    AlertDialog mDialog;
    EditText btcConvertBox, ethConvertBox;
    Double btcXchangeRate = null;
    Double ethXchangeRate = null;
    String selection = null;
    Spinner selectCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        tvRateBtc = (TextView) findViewById(R.id.tv_exchange_rate_btc);
        tvRateEth = (TextView) findViewById(R.id.tv_exchange_rate_eth);
        mDialog = new SpotsDialog(ConversionActivity.this);
        tvBtc = (TextView) findViewById(R.id.tv_btcToUSD);
        tvEth = (TextView) findViewById(R.id.tv_ethToUSD);

        btcConvertBox = (EditText) findViewById(R.id.et_amountToConvert);
        ethConvertBox = (EditText) findViewById(R.id.et_convertedAmount);
        selectCurrency = (Spinner) findViewById(R.id.sp_choose_currency);

        Intent xChangeIntent = getIntent();
        Bundle extras = xChangeIntent.getExtras();
        btcXchangeRate = extras.getDouble("btcXchange");
        ethXchangeRate = extras.getDouble("ethXchange");
        tvBtc.setText("BTC - " + extras.getString("currency"));
        tvEth.setText("ETH - " + extras.getString("currency"));


        if(TextUtils.isEmpty(btcXchangeRate.toString())&& TextUtils.isEmpty(ethXchangeRate.toString())){
            Toast.makeText(this, "Please go back and create more cards", Toast.LENGTH_LONG).show();
        }else {
            String btc = getCurrencySymbol(extras.getString("currency"))+String.valueOf(btcXchangeRate);
            String eth = getCurrencySymbol(extras.getString("currency"))+String.valueOf(ethXchangeRate);
            tvRateBtc.setText(btc);
            tvRateEth.setText(eth);
        }


        selectCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        selection = "none";
                        Toast.makeText(ConversionActivity.this, "Select Currency", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        selection = "btc";
                        break;
                    case 2:
                        selection = "eth";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btcConvertBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double con;
                if(TextUtils.isEmpty(btcConvertBox.getText().toString())){
                    // Do nothing
                }
                if(charSequence.length() > 0 && charSequence != null){
                    switch (selection){
                        case "btc":
                            con = Double.valueOf(charSequence.toString());
                            ethConvertBox.setText(String.valueOf(con / btcXchangeRate));
                            break;
                        case "eth":
                            con = Double.valueOf(charSequence.toString());
                            ethConvertBox.setText(String.valueOf(con / ethXchangeRate));
                            break;
                        case "none":
                            Toast.makeText(ConversionActivity.this,
                                    "You have not chosen any cryptocurrency", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public String getCurrencySymbol(String currencyCode){
        String symbol = null;
        switch (currencyCode){
            case "NGN":
                symbol = getString(R.string.NGN);
                break;
            case "JPY":
                symbol = getString(R.string.JPY);
                break;
            case "CNY":
                symbol = getString(R.string.CNY);
                break;
            case "INR":
                symbol = getString(R.string.INR);
                break;
            case "USD":
                symbol = getString(R.string.USD);
                break;
            case "SGD":
                symbol = getString(R.string.SGD);
                break;
            case "KRW":
                symbol = getString(R.string.KRW);
                break;
            case "AUD":
                symbol = getString(R.string.AUD);
                break;
            case "EUR":
                symbol = getString(R.string.KRW);
                break;
            case "GBP":
                symbol = getString(R.string.AUD);
                break;
            case "RUB":
                symbol = getString(R.string.RUB);
                break;
            case "ZAR":
                symbol = getString(R.string.ZAR);
                break;
            case "MXN":
                symbol = getString(R.string.MXN);
                break;
            case "HKD":
                symbol = getString(R.string.HKD);
                break;
            case "CAD":
                symbol = getString(R.string.CAD);
                break;
            case "NZD":
                symbol = getString(R.string.NZD);
                break;
            case "SEK":
                symbol = getString(R.string.SEK);
                break;
            case "CHF":
                symbol = getString(R.string.CHF);
                break;
            case "NOK":
                symbol = getString(R.string.NOK);
                break;
            case "BRL":
                symbol = getString(R.string.BRL);
                break;
        }
        return symbol;
    }

}
