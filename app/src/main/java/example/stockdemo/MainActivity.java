package example.stockdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.adapter.ExchangeRateAdapter;
import example.stockdemo.api.AlphaVantageService;
import example.stockdemo.api.RetrofitAlphaVantageServiceFactory;
import example.stockdemo.model.dto.CurrencyExchangeModel;
import example.stockdemo.utils.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.stock_recycler_view)
    RecyclerView stockRecyclerView;

    private ExchangeRateAdapter exchangeDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();

        AlphaVantageService alphaVantageService = new RetrofitAlphaVantageServiceFactory().create();

        alphaVantageService.getExchangeModel("CURRENCY_EXCHANGE_RATE", "BTC", "USD", Constants.API_KEY)
            .subscribeOn(Schedulers.io())
            .map(CurrencyExchangeModel::getRealtimeCurrencyExchangeRate)
            .map(ExchangeItem::convertModelToItem)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(exchangeModel -> exchangeDataAdapter.add(exchangeModel));

//        Observable.just(
//            new ExchangeItem("BTC", "ETH"),
//            new ExchangeItem("BTC", "USD"),
//            new ExchangeItem("ETH", "LTC"))
//            .subscribe(stockUpdate ->
//            {
//                Log.d("APP", "New Update " + stockUpdate.getToCurrencyName());
//                exchangeDataAdapter.add(stockUpdate);
//            });
    }

    private void initRecyclerView()
    {
        stockRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        exchangeDataAdapter = new ExchangeRateAdapter();
        stockRecyclerView.setLayoutManager(layoutManager);
        stockRecyclerView.setAdapter(exchangeDataAdapter);
    }
}
