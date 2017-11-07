package example.stockdemo.model;

import java.io.Serializable;

/**
 * Serializable in order to pass as an argument between fragments
 */
public class ExchangeItem implements Serializable
{
    private final String m_toCurrencyName, m_fromCurrencyName, m_exchangeRate, m_lastRefreshed, m_timeZone;
    private final CurrencyCodePair m_currencyCodePair;

    ExchangeItem(
        String toCurrency, String fromCurrency, CurrencyCodePair currencyCodePair,
        String exchangeRate, String lastRefreshed, String timeZone)
    {
        this.m_currencyCodePair = currencyCodePair;
        this.m_toCurrencyName = toCurrency;
        this.m_fromCurrencyName = fromCurrency;
        this.m_exchangeRate = exchangeRate;
        this.m_lastRefreshed = lastRefreshed;
        this.m_timeZone = timeZone;
    }

    public String getToCurrencyName()
    {
        return m_toCurrencyName;
    }

    public String getFromCurrencyName()
    {
        return m_fromCurrencyName;
    }

    public CurrencyCodePair getCurrencyCodePair()
    {
        return m_currencyCodePair;
    }

    public String getExchangeRate()
    {
        return m_exchangeRate;
    }

    public String getLastRefreshed()
    {
        return m_lastRefreshed;
    }

    public String getTimeZone()
    {
        return m_timeZone;
    }

    public static class Builder {
        String m_toCurrencyName, m_fromCurrencyName, m_exchangeRate, m_lastRefreshed, m_timeZone;
        CurrencyCodePair m_currencyCodePair;

        public Builder() {

        }

        public ExchangeItem build() {
            return new ExchangeItem(
                m_toCurrencyName,
                m_fromCurrencyName,
                m_currencyCodePair,
                m_exchangeRate,
                m_lastRefreshed,
                m_timeZone);
        }

        public ExchangeItem.Builder setToCurrencyName(String currencyName) {
            this.m_toCurrencyName = currencyName;
            return this;
        }

        public ExchangeItem.Builder setFromCurrencyName(String fromCurrencyName) {
            this.m_fromCurrencyName = fromCurrencyName;
            return this;
        }

        public ExchangeItem.Builder setCurrencyCodePair(String fromCurrencyCode, String toCurrencyCode)
        {
            this.m_currencyCodePair = new CurrencyCodePair(fromCurrencyCode, toCurrencyCode);
            return this;
        }

        public ExchangeItem.Builder setExchangeRate(String exchangeRate) {
            this.m_exchangeRate = exchangeRate;
            return this;
        }

        public ExchangeItem.Builder setLastRefreshed(String lastRefreshed) {
            this.m_lastRefreshed = lastRefreshed;
            return this;
        }

        public ExchangeItem.Builder setTimeZone(String timeZone) {
            this.m_timeZone = timeZone;
            return this;
        }
    }
}
