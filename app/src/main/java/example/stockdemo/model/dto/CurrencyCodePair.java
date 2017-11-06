package example.stockdemo.model.dto;

public class CurrencyCodePair
{
    private String m_fromCurrencyCode, m_toCurrencyCode;

    public CurrencyCodePair(String fromCurrencyCode, String toCurrencyCode)
    {
        m_fromCurrencyCode = fromCurrencyCode;
        m_toCurrencyCode = toCurrencyCode;
    }

    public String getFromCurrencyCode()
    {
        return m_fromCurrencyCode;
    }

    public String getToCurrencyCode()
    {
        return m_toCurrencyCode;
    }
}
