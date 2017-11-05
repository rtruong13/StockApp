package example.stockdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.api.AlphaVantageService;
import example.stockdemo.api.RetrofitAlphaVantageServiceFactory;
import example.stockdemo.utils.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.hello_world)
    TextView helloWorldTextView;

    @BindView(R.id.stock_recycler_view)
    RecyclerView stockRecyclerView;

    private LinearLayoutManager layoutManager;
    private ExchangeRateAdapter stockDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        stockRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        stockDataAdapter = new ExchangeRateAdapter();
        stockRecyclerView.setLayoutManager(layoutManager);
        stockRecyclerView.setAdapter(stockDataAdapter);

        Observable.just("Please use this app responsibly!")
            .subscribe(s -> helloWorldTextView.setText(s));

        AlphaVantageService alphaVantageService = new RetrofitAlphaVantageServiceFactory().create();

//        Observable.interval(0, 5, TimeUnit.SECONDS)
//            .flatMap(
//                i -> alphaVantageService.getExchangeModel("CURRENCY_EXCHANGE_RATE", "BTC", "USD", Constants.API_KEY)
//            ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(ExchangeModel::getExchangeRate)
//            .subscribeOn(exchangeModel -> log(
//
//                dat
//
//                );
        Observable.interval(0, 5, TimeUnit.SECONDS)
            .flatMap(i -> alphaVantageService.getExchangeModel("CURRENCY_EXCHANGE_RATE", "BTC", "USD", Constants.API_KEY)
                .toObservable())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(data -> log(data.getExchangeRate()));

        Observable.just(
            new ExchangeItem("BTC", "ETH"),
            new ExchangeItem("BTC", "USD"),
            new ExchangeItem("ETH", "LTC"))
            .subscribe(stockUpdate ->
            {
                Log.d("APP", "New Update " + stockUpdate.getToCurrency());
                stockDataAdapter.add(stockUpdate);

            });
    }

    private void log(Throwable throwable)
    {
        Log.e("APP", "Error", throwable);
    }

    private void log(String stage, String item)
    {
        Log.d("APP", stage + ":" + Thread.currentThread().getName() + ":" + item);
    }

    private void log(String item)
    {
        Log.d("APP", item);
    }
}
