package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class RealTimeCurrencyExchangeRate
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

    public String getFromCurrencyCode()
    {
        return m_fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode)
    {
        this.m_fromCurrencyCode = fromCurrencyCode;
    }

    public String getFromCurrencyName()
    {
        return m_fromCurrencyName;
    }

    public void setFromCurrencyName(String fromCurrencyName)
    {
        this.m_fromCurrencyName = fromCurrencyName;
    }

    public String getToCurrencyCode()
    {
        return m_toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode)
    {
        this.m_toCurrencyCode = toCurrencyCode;
    }

    public String getToCurrencyName()
    {
        return m_toCurrencyName;
    }

    public void setToCurrencyName(String toCurrencyName)
    {
        this.m_toCurrencyName = toCurrencyName;
    }

    public String getExchangeRate()
    {
        return m_exchangeRate;
    }

    public void setExchangeRate(String exchangeRate)
    {
        this.m_exchangeRate = exchangeRate;
    }

    public String getLastRefreshed()
    {
        return m_lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed)
    {
        this.m_lastRefreshed = lastRefreshed;
    }

    public String getTimeZone()
    {
        return m_timeZone;
    }

    public void setTimeZone(String timeZone)
    {
        this.m_timeZone = timeZone;
    }
}
