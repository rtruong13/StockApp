package example.stockdemo;

import android.graphics.Color;

import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;

public class ExchangeItem
{
    private final String toCurrencyName, fromCurrencyName, toCurrencyCode, fromCurrencyCode, exchangeRate, lastRefreshed, timeZone;

    public ExchangeItem(String toCurrency, String fromCurrency, String toCurrencyCode, String fromCurrencyCode,
                        String exchangeRate, String lastRefreshed, String timeZone)
    {
        this.toCurrencyName = toCurrency;
        this.fromCurrencyName = fromCurrency;
        this.toCurrencyCode = toCurrencyCode;
        this.fromCurrencyCode = fromCurrencyCode;
        this.exchangeRate = exchangeRate;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;

    }

    public String getToCurrencyName()
    {
        return toCurrencyName;
    }

    public String getFromCurrencyName()
    {
        return fromCurrencyName;
    }

    public String getToCurrencyCode()
    {
        return toCurrencyCode;
    }

    public String getFromCurrencyCode()
    {
        return fromCurrencyCode;
    }

    public String getExchangeRate()
    {
        return exchangeRate;
    }

    public String getLastRefreshed()
    {
        return lastRefreshed;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public static ExchangeItem convertModelToItem(RealTimeCurrencyExchangeRate exchangeModel) {
        return new ExchangeItem(exchangeModel.getToCurrencyCode(), exchangeModel.getFromCurrencyName(), exchangeModel.getToCurrencyCode(),
            exchangeModel.getFromCurrencyCode(), exchangeModel.getExchangeRate(), exchangeModel.getLastRefreshed(), exchangeModel.getTimeZone());
    }

    public static class Builder {
        String toCurrencyName, fromCurrencyName, toCurrencyCode, fromCurrencyCode, exchangeRate, lastRefreshed, timeZone;

        public Builder() {

        }

        public ExchangeItem build() {
            return new ExchangeItem(
                toCurrencyName,
                fromCurrencyName,
                toCurrencyCode,
                fromCurrencyCode,
                exchangeRate,
                lastRefreshed,
                timeZone);
        }

        public ExchangeItem.Builder setToCurrencyName(String currencyName) {
            this.toCurrencyCode = currencyName;
            return this;
        }

        public ExchangeItem.Builder setFromCurrencyName(String fromCurrencyName) {
            this.fromCurrencyName = fromCurrencyName;
            return this;
        }

        public ExchangeItem.Builder setToCurrencyCode(String toCurrencyCode) {
            this.toCurrencyCode = toCurrencyCode;
            return this;
        }

        public ExchangeItem.Builder setFromCurrencyCode(String fromCurrencyCode) {
            this.fromCurrencyCode = fromCurrencyCode;
            return this;
        }

        public ExchangeItem.Builder setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public ExchangeItem.Builder setLastRefreshed(String lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
            return this;
        }

        public ExchangeItem.Builder setTimeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }
    }
}
