package example.stockdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.stockdemo.ExchangeRateAdapter.ExchangeRateViewHolder;

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
        ExchangeItem stockUpdate = data.get(position);
        holder.setFromCurrency(stockUpdate.getToCurrency());
        holder.setToCurrency(stockUpdate.getFromCurrency());
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
        @BindView(R.id.from_currency)
        TextView fromCurrency;

        @BindView(R.id.to_currency)
        TextView toCurrency;

        public ExchangeRateViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setFromCurrency(String fromCurrency)
        {
            this.fromCurrency.setText(fromCurrency);
        }

        public void setToCurrency(String toCurrency)
        {
            this.toCurrency.setText(toCurrency);
        }
    }
}
