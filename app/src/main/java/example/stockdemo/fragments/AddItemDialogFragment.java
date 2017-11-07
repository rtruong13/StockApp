package example.stockdemo.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import example.stockdemo.R;

public class AddItemDialogFragment extends DialogFragment
{
    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_currency_pair, null))
            .setPositiveButton(R.string.search, (dialog, id) ->
            {
                EditText fromCurrency = getDialog().findViewById(R.id.from_currency);
                EditText toCurrency = getDialog().findViewById(R.id.to_currency);
                ((RecyclerViewFragment) getTargetFragment()).addExchangeRates(fromCurrency.getText().toString(), toCurrency.getText().toString());
            })
            .setNegativeButton(R.string.cancel, (dialog, id) -> AddItemDialogFragment.this.getDialog().cancel());

        return builder.create();
    }
}
