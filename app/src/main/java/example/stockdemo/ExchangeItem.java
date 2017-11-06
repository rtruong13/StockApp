package example.stockdemo;

import example.stockdemo.model.dto.RealTimeCurrencyExchangeRate;

public class ExchangeItem
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

    public static ExchangeItem convertModelToItem(RealTimeCurrencyExchangeRate exchangeModel) {
        return new ExchangeItem(exchangeModel.getToCurrencyCode(), exchangeModel.getFromCurrencyName());
    }
}
