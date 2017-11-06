package example.stockdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.stockdemo.model.dto.CurrencyExchangeModel;
import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;
import example.stockdemo.utils.Constants;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ItemDetailsFragment extends Fragment implements OnRefreshListener
{
    @BindView(R.id.item_swipe_refresh)
    SwipeRefreshLayout m_swipeRefreshLayout;
    @BindView(R.id.from_currency_code)
    TextView m_fromCurrencyCode;
    @BindView(R.id.to_currency_code)
    TextView m_toCurrencyCode;
    @BindView(R.id.from_currency_name)
    TextView m_fromCurrencyName;
    @BindView(R.id.to_currency_name)
    TextView m_toCurrencyName;
    @BindView(R.id.exchange_rate)
    TextView m_exchangeRate;
    @BindView(R.id.time_zone)
    TextView m_timeZone;
    @BindView(R.id.last_refresh)
    TextView m_lastRefresh;

    private Unbinder m_unBinder;
    private ExchangeItem m_exchangeItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        m_unBinder = ButterKnife.bind(this, view);

        Bundle b = getArguments();
        m_exchangeItem = (ExchangeItem) b.getSerializable(getString(R.string.item_detail));
        assert m_exchangeItem != null;

        setTextViews(m_exchangeItem);
        m_swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void setTextViews(ExchangeItem exchangeItem)
    {
        m_fromCurrencyCode.setText(exchangeItem.getCurrencyCodePair().getFromCurrencyCode());
        m_fromCurrencyName.setText(exchangeItem.getFromCurrencyName());
        m_toCurrencyCode.setText(exchangeItem.getCurrencyCodePair().getFromCurrencyCode());
        m_toCurrencyName.setText(exchangeItem.getToCurrencyName());
        m_exchangeRate.setText(exchangeItem.getExchangeRate());
        m_lastRefresh.setText(exchangeItem.getLastRefreshed());
        m_timeZone.setText(exchangeItem.getTimeZone());
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        m_unBinder.unbind();
    }

    @Override
    public void onRefresh()
    {
        ((MainActivity) getActivity()).getAlphaVantageService().getExchangeModel(Constants.CURRENCY_EXCHANGE_RATE,
            m_exchangeItem.getCurrencyCodePair().getFromCurrencyCode(), m_exchangeItem.getCurrencyCodePair().getToCurrencyCode(),
            Constants.API_KEY).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<CurrencyExchangeModel>()
            {
                @Override
                public void onError(Throwable e)
                {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete()
                {
                    Log.d("APP", "Successfully added");
                }

                @Override
                public void onSubscribe(Disposable d)
                {

                }

                @Override
                public void onNext(CurrencyExchangeModel exchangeModel)
                {
                    RealTimeCurrencyExchangeRate currencyExchangeRate = exchangeModel.getRealtimeCurrencyExchangeRate();
                    try
                    {
                        setTextViews(new ExchangeItem.Builder()
                            .setExchangeRate(currencyExchangeRate.getExchangeRate())
                            .setCurrencyCodePair(currencyExchangeRate.getFromCurrencyCode(), currencyExchangeRate.getToCurrencyCode())
                            .setFromCurrencyName(currencyExchangeRate.getFromCurrencyName())
                            .setToCurrencyName(currencyExchangeRate.getToCurrencyName())
                            .setLastRefreshed(currencyExchangeRate.getLastRefreshed())
                            .setTimeZone(currencyExchangeRate.getTimeZone())
                            .build());
                    }
                    catch (NullPointerException e)
                    {
                        Log.e("ERROR", e.toString());
                        Toast.makeText(getActivity(), "Unsuccessful API call", Toast.LENGTH_SHORT).show();
                    }
                    finally
                    {
                        if (m_swipeRefreshLayout.isRefreshing())
                        {
                            m_swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }
            });
    }
}
