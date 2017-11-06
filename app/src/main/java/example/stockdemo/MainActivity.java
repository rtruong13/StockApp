package example.stockdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.adapter.ExchangeRateAdapter;
import example.stockdemo.api.AlphaVantageService;
import example.stockdemo.api.RetrofitAlphaVantageServiceFactory;
import example.stockdemo.model.dto.CurrencyCodePair;
import example.stockdemo.model.dto.CurrencyExchangeModel;
import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;
import example.stockdemo.utils.Constants;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    private AlphaVantageService m_alphaVantageService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_alphaVantageService = new RetrofitAlphaVantageServiceFactory().create();

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
}
