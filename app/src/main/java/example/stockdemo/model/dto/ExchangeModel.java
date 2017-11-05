package example.stockdemo.model.dto;

import com.google.gson.annotations.SerializedName;

public class ExchangeModel
{

    @SerializedName("1. From_Currency Code")
    private String fromCurrencyCode;
    @SerializedName("2. From_Currency Name")
    private String fromCurrencyName;
    @SerializedName("3. To_Currency Code")
    private String toCurrencyCode;
    @SerializedName("4. To_Currency Name")
    private String toCurrencyName;
    @SerializedName("5. Exchange Rate")
    private String exchangeRate;
    @SerializedName("6. Last Refreshed")
    private String lastRefreshed;
    @SerializedName("7. Time Zone")
    private String timeZone;

    public String getFromCurrencyCode()
    {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode)
    {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getFromCurrencyName()
    {
        return fromCurrencyName;
    }

    public void setFromCurrencyName(String fromCurrencyName)
    {
        this.fromCurrencyName = fromCurrencyName;
    }

    public String getToCurrencyCode()
    {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode)
    {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getToCurrencyName()
    {
        return toCurrencyName;
    }

    public void setToCurrencyName(String toCurrencyName)
    {
        this.toCurrencyName = toCurrencyName;
    }

    public String getExchangeRate()
    {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public String getLastRefreshed()
    {
        return lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed)
    {
        this.lastRefreshed = lastRefreshed;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public void setTimeZone(String timeZone)
    {
        this.timeZone = timeZone;
    }

}
