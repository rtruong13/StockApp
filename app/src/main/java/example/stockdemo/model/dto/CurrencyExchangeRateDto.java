package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class CurrencyExchangeRateDto
{
    @SerializedName("1. From_Currency Code")
    private String m_fromCurrencyCode;
    @SerializedName("2. From_Currency Name")
    private String m_fromCurrencyName;
    @SerializedName("3. To_Currency Code")
    private String m_toCurrencyCode;
    @SerializedName("4. To_Currency Name")
    private String m_toCurrencyName;
    @SerializedName("5. Exchange Rate")
    private String m_exchangeRate;
    @SerializedName("6. Last Refreshed")
    private String m_lastRefreshed;
    @SerializedName("7. Time Zone")
    private String m_timeZone;

    /**
     * Get the currency code to exchange from
     * @return Exchanged from currency code
     */
    public String getFromCurrencyCode()
    {
        return m_fromCurrencyCode;
    }

    /**
     * Get the currency name to exchange from
     * @return Exchanged from currency name
     */
    public String getFromCurrencyName()
    {
        return m_fromCurrencyName;
    }

    /**
     * Get the currency code to exchange to
     * @return Exchanged to currency code
     */
    public String getToCurrencyCode()
    {
        return m_toCurrencyCode;
    }

    /**
     * Get the currency name to exchange to
     * @return Exchanged to currency name
     */
    public String getToCurrencyName()
    {
        return m_toCurrencyName;
    }

    /**
     * Get the exchange from of the two currencies
     * @return Exchange rate
     */
    public String getExchangeRate()
    {
        return m_exchangeRate;
    }

    /**
     * Get the last refresh time of request
     * @return Time of request
     */
    public String getLastRefreshed()
    {
        return m_lastRefreshed;
    }

    /**
     * Get the time zone of where data was requested
     * @return Time zone of retrieval
     */
    public String getTimeZone()
    {
        return m_timeZone;
    }
}
