package example.stockdemo;

import java.io.Serializable;

public class ExchangeItem implements Serializable
{
    private final String toCurrency;
    private final String fromCurrency;

    public ExchangeItem(String toCurrency, String fromCurrency)
    {
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency()
    {
        return toCurrency;
    }

    public String getFromCurrency()
    {
        return fromCurrency;
    }
}
