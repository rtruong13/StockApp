package example.stockdemo.api;

import example.stockdemo.model.dto.CurrencyExchangeDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Java interface where you define the structure and parameters of Http requests by putting meta data
 */
public interface AlphaVantageService
{
    @GET("/query")
    Observable<CurrencyExchangeDto> getExchangeModel(
        @Query("function") String function, @Query("from_currency") String fromCurrency,
        @Query("to_currency") String toCurrency, @Query("apikey") String apiKey);
}
