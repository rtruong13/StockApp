package example.stockdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.ExchangeItem;
import example.stockdemo.adapter.ExchangeRateAdapter.ExchangeRateViewHolder;
import example.stockdemo.R;

public class ExchangeRateAdapter extends RecyclerView.Adapter<ExchangeRateViewHolder>
{
    private final List<ExchangeItem> data = new ArrayList<>();

    @Override
    public ExchangeRateViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchange_item, parent, false);
        ExchangeRateViewHolder vh = new ExchangeRateViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ExchangeRateViewHolder holder, int position)
    {
        ExchangeItem exchangeItem = data.get(position);
        holder.setCurrencies(exchangeItem.getFromCurrencyCode() + "/" + exchangeItem.getToCurrencyCode());
        holder.setExchange_rate(exchangeItem.getExchangeRate());
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public void add(ExchangeItem stockSymbol)
    {
        this.data.add(stockSymbol);
        notifyItemInserted(data.size() - 1);
    }

    class ExchangeRateViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.currencies)
        TextView currencies;

        @BindView(R.id.exchange_rate)
        TextView exchange_rate;

        public ExchangeRateViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setCurrencies(String currencies)
        {
            this.currencies.setText(currencies);
        }

        public void setExchange_rate(String exchange_rate)
        {
            this.exchange_rate.setText(exchange_rate);
        }
    }
}
