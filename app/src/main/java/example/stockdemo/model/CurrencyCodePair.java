package example.stockdemo.model;

public class CurrencyCodePair
{
    private String m_fromCurrencyCode, m_toCurrencyCode;

    CurrencyCodePair(String fromCurrencyCode, String toCurrencyCode)
    {
        m_fromCurrencyCode = fromCurrencyCode;
        m_toCurrencyCode = toCurrencyCode;
    }

     /**
     * Get the currency code to exchange from
     * @return Exchanged from currency code
     */
    public String getFromCurrencyCode()
    {
        return m_fromCurrencyCode;
    }

    /**
     * Get the currency code to exchange to
     * @return Exchanged to currency code
     */
    public String getToCurrencyCode()
    {
        return m_toCurrencyCode;
    }
}
