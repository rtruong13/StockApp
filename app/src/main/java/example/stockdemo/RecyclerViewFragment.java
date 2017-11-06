package example.stockdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.stockdemo.adapter.ExchangeRateAdapter;
import example.stockdemo.adapter.ExchangeRateAdapter.OnItemClickListener;
import example.stockdemo.model.dto.CurrencyCodePair;
import example.stockdemo.model.dto.CurrencyExchangeModel;
import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;
import example.stockdemo.utils.Constants;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewFragment extends Fragment implements OnRefreshListener, SearchView.OnQueryTextListener
{
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar m_toolbar;
    @BindView(R.id.list_swipe_refresh)
    SwipeRefreshLayout m_swipeRefreshLayout;
    @BindView(R.id.stock_recycler_view)
    RecyclerView m_stockRecyclerView;

    private ExchangeRateAdapter m_exchangeDataAdapter;
    private Unbinder m_unBinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        m_unBinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initRecyclerView();
        m_swipeRefreshLayout.setOnRefreshListener(this);

        addExchangeRates("BTC", "LTC");
        addExchangeRates("USD", "LTC");
        addExchangeRates("ETH", "LTC");
        addExchangeRates("BTC", "ETH");
        displayStoredExchangeCurrencies(MainActivity.getExchangeItems());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_recycler_view, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_add:
                CurrencyPairDialogFragment currencyPairDialog = new CurrencyPairDialogFragment();
                currencyPairDialog.setTargetFragment(this, 0);
                currencyPairDialog.show(getFragmentManager(), "add");
                break;

            default:
                break;
        }

        return true;
    }

    private void initRecyclerView()
    {
        m_stockRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        OnItemClickListener listener = item ->
        {
            Bundle arguments = new Bundle();
            arguments.putSerializable(getString(R.string.item_detail), item);
            ItemDetailsFragment fragment = new ItemDetailsFragment();
            fragment.setArguments(arguments);
            getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.place_holder, fragment)
                .addToBackStack(null)
                .commit();
        };

        m_exchangeDataAdapter = new ExchangeRateAdapter(listener);
        m_stockRecyclerView.setLayoutManager(layoutManager);
        m_stockRecyclerView.setAdapter(m_exchangeDataAdapter);
    }

    void addExchangeRates(String from, String to)
    {
        ((MainActivity) getActivity()).getAlphaVantageService()
            .getExchangeModel(Constants.CURRENCY_EXCHANGE_RATE, from, to, Constants.API_KEY)
            .subscribeOn(Schedulers.newThread())
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
                        ExchangeItem exchangeItem = new ExchangeItem.Builder()
                            .setExchangeRate(currencyExchangeRate.getExchangeRate())
                            .setCurrencyCodePair(currencyExchangeRate.getFromCurrencyCode(), currencyExchangeRate.getToCurrencyCode())
                            .setFromCurrencyName(currencyExchangeRate.getFromCurrencyName())
                            .setToCurrencyName(currencyExchangeRate.getToCurrencyName())
                            .setLastRefreshed(currencyExchangeRate.getLastRefreshed())
                            .setTimeZone(currencyExchangeRate.getTimeZone())
                            .build();
                        m_exchangeDataAdapter.add(exchangeItem);
                        MainActivity.addCurrencyCodePair(exchangeItem);
                    }
                    catch (NullPointerException e)
                    {
                        Log.e("ERROR", e.toString());
                        Toast.makeText(getActivity(), "Unsuccessful API call", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
        m_exchangeDataAdapter.notifyDataSetChanged();
    }

    private void displayStoredExchangeCurrencies(List<ExchangeItem> exchangeItems)
    {
        for(ExchangeItem item : exchangeItems)
        {
            addExchangeRates(item.getCurrencyCodePair().getFromCurrencyCode(), item.getCurrencyCodePair().getToCurrencyCode());
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s)
    {
        s = s.toLowerCase();
        ArrayList<ExchangeItem> newList = new ArrayList<>();
        for(ExchangeItem item : MainActivity.getExchangeItems())
        {
            String fromCurrency = item.getCurrencyCodePair().getFromCurrencyCode().toLowerCase();
            if(fromCurrency.contains(s))
            {
                newList.add(item);
            }
        }

        m_exchangeDataAdapter.setFilter(newList);
        return true;
    }
}
