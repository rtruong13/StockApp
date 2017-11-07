package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class CurrencyExchangeDto
{
    @SerializedName("Realtime Currency Exchange Rate")
    private CurrencyExchangeRateDto m_realtimeCurrencyExchangeRate;

    /**
     * Gets the currency exchange rate object
     * @return Exchange rate pojo
     */
    public CurrencyExchangeRateDto getRealtimeCurrencyExchangeRate() {
        return m_realtimeCurrencyExchangeRate;
    }
}
