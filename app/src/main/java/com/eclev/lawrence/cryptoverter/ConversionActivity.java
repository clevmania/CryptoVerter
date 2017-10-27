package com.eclev.lawrence.cryptoverter;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversionActivity extends AppCompatActivity {
    BitCurrency mBitCurrency;
    TextView tvRateBtc, tvRateEth;
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

        mBitCurrency = Common.getBitCurrencyEquiv();
        tvRateBtc = (TextView) findViewById(R.id.tv_exchange_rate_btc);
        tvRateEth = (TextView) findViewById(R.id.tv_exchange_rate_eth);
        mDialog = new SpotsDialog(ConversionActivity.this);

        btcConvertBox = (EditText) findViewById(R.id.et_amountToConvert);
        ethConvertBox = (EditText) findViewById(R.id.et_convertedAmount);
        selectCurrency = (Spinner) findViewById(R.id.sp_choose_currency);

        Intent xChangeIntent = getIntent();
        Bundle extras = xChangeIntent.getExtras();
        btcXchangeRate = extras.getDouble("btcXchange");
        ethXchangeRate = extras.getDouble("ethXchange");

        tvRateBtc.setText("BTC: " + btcXchangeRate);
        tvRateEth.setText("ETH: " + ethXchangeRate);

//        mBitCurrency.getBtcEth().enqueue(new Callback<Xchanger>() {
//            @Override
//            public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {
//                mDialog.dismiss();
//                tvRateBtc.setText("BTC: " + String.valueOf(response.body().getBTC()));
//                tvRateEth.setText("ETH: " + String.valueOf(response.body().getETH()));
//                btcXchangeRate = String.valueOf(response.body().getBTC());
//                ethXchangeRate = String.valueOf(response.body().getETH());
//            }
//
//            @Override
//            public void onFailure(Call<Xchanger> call, Throwable t) {
//                Log.e("Error", t.getMessage());
//                mDialog.dismiss();
//            }
//        });

        selectCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
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
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
