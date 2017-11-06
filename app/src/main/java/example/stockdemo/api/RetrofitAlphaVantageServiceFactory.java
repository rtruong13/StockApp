package example.stockdemo.api;

import example.stockdemo.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit object built here to convertModelToItem services
 */
public class RetrofitAlphaVantageServiceFactory
{
    private HttpLoggingInterceptor m_interceptor = new HttpLoggingInterceptor() //Turn off in production but in testing, it's good to see what we're doing
        .setLevel(Level.BODY);

    private OkHttpClient m_okHttpClient = new OkHttpClient.Builder().addInterceptor(m_interceptor).build();
    private Retrofit m_retrofit = new Retrofit.Builder()
        .client(m_okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build();

    /**
     * Create service interface objects
     * @return Service interface objects to make queries
     */
    public AlphaVantageService create()
    {
        return m_retrofit.create(AlphaVantageService.class);
    }
}
