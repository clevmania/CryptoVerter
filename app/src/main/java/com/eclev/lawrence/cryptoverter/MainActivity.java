package com.eclev.lawrence.cryptoverter;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.eclev.lawrence.cryptoverter.Model.Xchanger;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import java.util.ArrayList;
import java.util.List;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cardsView;
    private cardsAdapter mCardsAdapter;
    private List<currency> mCurrencies;
    private Spinner chooseCurrency;

    BitCurrency mBitCurrency;
    TextView tvUSD, tvRateBtc, tvRateEth, tvNGN, tvGBP;
    AlertDialog mDialog;
    EditText btcConvertBox, ethConvertBox;
    String btcXchangeRate = null;
    String ethXchangeRate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardsView = (RecyclerView) findViewById(R.id.rv_cards_view);
        chooseCurrency = (Spinner) findViewById(R.id.sp_choose_currency);
        mCurrencies = new ArrayList<>();

        mBitCurrency = Common.getBitCurrencyEquiv();
        tvUSD = (TextView) findViewById(R.id.tv_usd);
        tvNGN = (TextView) findViewById(R.id.tv_ngn);
        tvGBP = (TextView) findViewById(R.id.tv_gbp);
        tvRateBtc = (TextView) findViewById(R.id.tv_exchange_rate_btc);
        tvRateEth = (TextView) findViewById(R.id.tv_exchange_rate_eth);
        mDialog = new SpotsDialog(MainActivity.this);

        btcConvertBox = (EditText) findViewById(R.id.et_amountToConvert);
        ethConvertBox = (EditText) findViewById(R.id.et_convertedAmount);

        tvUSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                convertToBtc("USD");
            }
        });

        tvNGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                convertToBtc("NGN");
            }
        });

        tvGBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                convertToBtc("GBP");
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        cardsView.setLayoutManager(layoutManager);
        cardsView.setHasFixedSize(true);
        cardsView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        cardsView.setItemAnimator(new DefaultItemAnimator());
        mCardsAdapter = new cardsAdapter(this,mCurrencies);
        cardsView.setAdapter(mCardsAdapter);


        chooseCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:

                        break;
                    case 1:
                        mCurrencies.add(new currency("JPY"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        mCurrencies.add(new currency("CNY"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        mCurrencies.add(new currency("INR"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        mCurrencies.add(new currency("SGD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 5:
                        mCurrencies.add(new currency("KRW"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 6:
                        mCurrencies.add(new currency("USD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void convertToBtc(final String currency){
        switch(currency){
            case "NGN":
                mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
                    @Override
                    public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {
                        mDialog.dismiss();

//                        Log.i("Output",String.valueOf(response.body().getBTC().getUSD()));
//                        Log.i("Output",String.valueOf(response.body().getETH().getUSD()));
                        tvRateBtc.setText(String.valueOf(response.body().getBTC().getNGN()));
                        tvRateEth.setText(String.valueOf(response.body().getETH().getNGN()));

                        Intent convertIntent = new Intent(MainActivity.this, ConversionActivity.class);
                        Bundle extras = new Bundle();
                        extras.putDouble("btcXchange",response.body().getBTC().getNGN());
                        extras.putDouble("ethXchange", response.body().getETH().getNGN());
                        convertIntent.putExtras(extras);
                        startActivity(convertIntent);
                    }

                    @Override
                    public void onFailure(Call<Xchanger> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        mDialog.dismiss();
                    }
                });
                break;
            case "USD":
                mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
                    @Override
                    public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {
                        mDialog.dismiss();

                        Log.i("Output",String.valueOf(response.body().getBTC().getUSD()));
                        Log.i("Output",String.valueOf(response.body().getETH().getUSD()));
                        tvRateBtc.setText(String.valueOf(response.body().getBTC().getUSD()));
                        tvRateEth.setText(String.valueOf(response.body().getETH().getUSD()));

                        Intent convertIntent = new Intent(MainActivity.this, ConversionActivity.class);
                        Bundle extras = new Bundle();
                        extras.putDouble("btcXchange",response.body().getBTC().getUSD());
                        extras.putDouble("ethXchange", response.body().getETH().getUSD());
                        convertIntent.putExtras(extras);
                        startActivity(convertIntent);

                    }

                    @Override
                    public void onFailure(Call<Xchanger> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        mDialog.dismiss();
                    }
                });
                break;
            case "GBP":
                mBitCurrency.getXchangeRate(currency).enqueue(new Callback<Xchanger>() {
                    @Override
                    public void onResponse(Call<Xchanger> call, Response<Xchanger> response) {
                        mDialog.dismiss();

                        tvRateBtc.setText(String.valueOf(response.body().getBTC().getUSD()));
                        tvRateEth.setText(String.valueOf(response.body().getETH().getUSD()));

                        Intent convertIntent = new Intent(MainActivity.this, ConversionActivity.class);
                        Bundle extras = new Bundle();
                        extras.putDouble("btcXchange",response.body().getBTC().getGBP());
                        extras.putDouble("ethXchange", response.body().getETH().getGBP());
                        convertIntent.putExtras(extras);
                        startActivity(convertIntent);

                    }

                    @Override
                    public void onFailure(Call<Xchanger> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        mDialog.dismiss();
                    }
                });
                break;
        }

    }

//    https://www.cryptocompare.com/media/19633/btc.png
//    /media/20275/etc2.png
//    https://www.cryptocompare.com/media/20646/eth.png

    public void currencyLoader(String currency){

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeEdge){
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if(position < spanCount) outRect.top = spacing;
                outRect.bottom = spacing;
            }else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if(position >= spacing) outRect.top = spacing;
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();

        DisplayMetrics metrics = r.getDisplayMetrics();
        int unit = TypedValue.COMPLEX_UNIT_DIP;
        float show = TypedValue.applyDimension(unit, dp, metrics);
        int dvalue = Math.round(show);
        return dvalue;
    }




}
