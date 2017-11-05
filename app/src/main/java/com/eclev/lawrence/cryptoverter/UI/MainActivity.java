package com.eclev.lawrence.cryptoverter.UI;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.eclev.lawrence.cryptoverter.Model.currency;
import com.eclev.lawrence.cryptoverter.R;
import com.eclev.lawrence.cryptoverter.Remote.BitCurrency;
import com.eclev.lawrence.cryptoverter.Utils.Common;
import com.eclev.lawrence.cryptoverter.Utils.cardsAdapter;

import java.util.ArrayList;
import java.util.List;
import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cardsView;
    private cardsAdapter mCardsAdapter;
    private List<currency> mCurrencies;
    private Spinner chooseCurrency;

    BitCurrency mBitCurrency;
    TextView tvCardStatus;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardsView = (RecyclerView) findViewById(R.id.rv_cards_view);
        chooseCurrency = (Spinner) findViewById(R.id.sp_choose_currency);
        tvCardStatus = (TextView) findViewById(R.id.tv_status);
        mCurrencies = new ArrayList<>();

        mBitCurrency = Common.getBitCurrencyEquiv();
        mDialog = new SpotsDialog(MainActivity.this);

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
                    case 7:
                        mCurrencies.add(new currency("AUD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 8:
                        mCurrencies.add(new currency("EUR"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 9:
                        mCurrencies.add(new currency("GBP"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 10:
                        mCurrencies.add(new currency("RUB"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 11:
                        mCurrencies.add(new currency("ZAR"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 12:
                        mCurrencies.add(new currency("MXN"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 13:
                        mCurrencies.add(new currency("HKD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 14:
                        mCurrencies.add(new currency("CAD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 15:
                        mCurrencies.add(new currency("NZD"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 16:
                        mCurrencies.add(new currency("SEK"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 17:
                        mCurrencies.add(new currency("CHF"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 18:
                        mCurrencies.add(new currency("NOK"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 19:
                        mCurrencies.add(new currency("BRL"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    case 20:
                        mCurrencies.add(new currency("NGN"));
                        mCardsAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
                createCardHandler();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void createCardHandler() {
        if(mCardsAdapter.getItemCount() == 0){
            tvCardStatus.setVisibility(View.VISIBLE);
            cardsView.setVisibility(View.GONE);
        }else{
            cardsView.setVisibility(View.VISIBLE);
            tvCardStatus.setVisibility(View.GONE);
        }
    }

//    public void setImage(Context context, String img){
//        ImageView blogImage = (ImageView) myView.findViewById(R.id.iv_blog_posted_image);
//        Glide.with(context).load(img).into(blogImage);
//        https://www.cryptocompare.com/media/19633/btc.png
//        https://www.cryptocompare.com/media/20646/eth.png
//    }



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
