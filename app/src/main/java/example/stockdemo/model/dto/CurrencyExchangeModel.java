package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class CurrencyExchangeModel
{
    @SerializedName("Realtime Currency Exchange Rate")
    private RealTimeCurrencyExchangeRate m_realtimeCurrencyExchangeRate;

    public RealTimeCurrencyExchangeRate getRealtimeCurrencyExchangeRate() {
        return m_realtimeCurrencyExchangeRate;
    }
}
