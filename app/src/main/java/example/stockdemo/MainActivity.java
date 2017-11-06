package example.stockdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import example.stockdemo.api.AlphaVantageService;
import example.stockdemo.api.RetrofitAlphaVantageServiceFactory;

public class MainActivity extends AppCompatActivity
{
    public static ArrayList<ExchangeItem> m_exchangeItems;
    private AlphaVantageService m_alphaVantageService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_alphaVantageService = new RetrofitAlphaVantageServiceFactory().create();
        m_exchangeItems = new ArrayList<>();

        RecyclerViewFragment fragment = new RecyclerViewFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.place_holder, fragment);
        fragmentTransaction.commit();
    }

    public AlphaVantageService getAlphaVantageService()
    {
        return m_alphaVantageService;
    }

    public static ArrayList<ExchangeItem> getExchangeItems()
    {
        return m_exchangeItems;
    }

    public static void addCurrencyCodePair(ExchangeItem exchangeItem)
    {
        boolean duplicate = false;
        for (ExchangeItem item : m_exchangeItems)
        {
            if (item.getCurrencyCodePair().getToCurrencyCode().equals(exchangeItem.getCurrencyCodePair().getToCurrencyCode())
                && item.getCurrencyCodePair().getFromCurrencyCode().equals(exchangeItem.getCurrencyCodePair().getFromCurrencyCode()))
            { duplicate = true; }
        }

        if (!duplicate)
        { m_exchangeItems.add(exchangeItem); }
    }
}
