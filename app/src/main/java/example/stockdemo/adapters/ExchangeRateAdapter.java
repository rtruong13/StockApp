package example.stockdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.model.ExchangeItem;
import example.stockdemo.adapters.ExchangeRateAdapter.ExchangeRateViewHolder;
import example.stockdemo.R;

public class ExchangeRateAdapter extends RecyclerView.Adapter<ExchangeRateViewHolder>
{
    private List<ExchangeItem> m_data = new ArrayList<>();
    private OnItemClickListener m_listener;

    public ExchangeRateAdapter(OnItemClickListener listener)
    {
        m_listener = listener;
    }

    @Override
    public ExchangeRateViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchange_item, parent, false);
        return new ExchangeRateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExchangeRateViewHolder holder, int position)
    {
        ExchangeItem exchangeItem = m_data.get(position);
        holder.setCurrencies(exchangeItem.getCurrencyCodePair().getFromCurrencyCode() + "/" + exchangeItem.getCurrencyCodePair().getToCurrencyCode());
        holder.setExchangeRate(exchangeItem.getExchangeRate());
        holder.click(m_data.get(position), m_listener);
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }

    /**
     * Add an item to the array list of exchange items
     * @param exchangeItem Object containing relevant data
     */
    public void add(ExchangeItem exchangeItem)
    {
        this.m_data.add(exchangeItem);
        notifyItemInserted(m_data.size() - 1);
    }

    /**
     * Modifies the data array with filtered data
     * @param newList
     */
    public void setFilter(ArrayList<ExchangeItem> newList)
    {
        m_data = new ArrayList<>();
        m_data.addAll(newList);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(ExchangeItem item);
    }

    class ExchangeRateViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.currencies)
        TextView currencies;

        @BindView(R.id.exchange_rate)
        TextView exchange_rate;

        ExchangeRateViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void click(final ExchangeItem exchangeItem, final OnItemClickListener listener)
        {
            itemView.setOnClickListener(view -> listener.onClick(exchangeItem));
        }

        void setCurrencies(String currencies)
        {
            this.currencies.setText(currencies);
        }

        void setExchangeRate(String exchange_rate)
        {
            this.exchange_rate.setText(exchange_rate);
        }
    }
}
