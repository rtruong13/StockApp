package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class CurrencyExchangeModel
{
    @SerializedName("Realtime Currency Exchange Rate")
    private RealTimeCurrencyExchangeRate realtimeCurrencyExchangeRate;

    public RealTimeCurrencyExchangeRate getRealtimeCurrencyExchangeRate() {
        return realtimeCurrencyExchangeRate;
    }

    public void setRealtimeCurrencyExchangeRate(RealTimeCurrencyExchangeRate realtimeCurrencyExchangeRate) {
        this.realtimeCurrencyExchangeRate = realtimeCurrencyExchangeRate;
    }
}
