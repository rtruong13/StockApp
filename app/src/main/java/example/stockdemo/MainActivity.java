package example.stockdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.api.AlphaVantageService;
import example.stockdemo.api.RetrofitAlphaVantageServiceFactory;
import example.stockdemo.model.dto.CurrencyExchangeModel;
import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;
import example.stockdemo.utils.Constants;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.hello_world)
    TextView helloWorldTextView;

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

        Observable.just("Please use this app responsibly!")
            .subscribe(s -> helloWorldTextView.setText(s));

        AlphaVantageService alphaVantageService = new RetrofitAlphaVantageServiceFactory().create();
//        alphaVantageService.getExchangeModel("CURRENCY_EXCHANGE_RATE", "BTC", "USD", Constants.API_KEY).subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<CurrencyExchangeModel>()
//            {
//                @Override
//                public void onError(Throwable e)
//                {
//
//                }
//
//                @Override
//                public void onComplete()
//                {
//
//                }
//
//                @Override
//                public void onSubscribe(Disposable d)
//                {
//
//                }
//
//                @Override
//                public void onNext(CurrencyExchangeModel exchangeModel)
//                {
//                    RealTimeCurrencyExchangeRate currencyExchangeRate = exchangeModel.getRealtimeCurrencyExchangeRate();
//                    exchangeDataAdapter.add(new ExchangeItem(currencyExchangeRate.getToCurrencyName(), currencyExchangeRate.getFromCurrencyName()));
//                }
//            });

        alphaVantageService.getExchangeModel("CURRENCY_EXCHANGE_RATE", "BTC", "USD", Constants.API_KEY)
            .subscribeOn(Schedulers.io())
            .map(CurrencyExchangeModel::getRealtimeCurrencyExchangeRate)
            .map(ExchangeItem::convertModelToItem)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(exchangeModel -> {
                exchangeDataAdapter.add(exchangeModel);
            });

        Observable.just(
            new ExchangeItem("BTC", "ETH"),
            new ExchangeItem("BTC", "USD"),
            new ExchangeItem("ETH", "LTC"))
            .subscribe(stockUpdate ->
            {
                Log.d("APP", "New Update " + stockUpdate.getToCurrency());
                exchangeDataAdapter.add(stockUpdate);

            });
    }

    private void initRecyclerView()
    {
        stockRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        exchangeDataAdapter = new ExchangeRateAdapter();
        stockRecyclerView.setLayoutManager(layoutManager);
        stockRecyclerView.setAdapter(exchangeDataAdapter);
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
