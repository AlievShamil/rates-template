package ru.sberbank.learning.rates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by DevCom on 29.04.2017.
 */

public class ValuteAdapter extends BaseAdapter {

    private List<Currency> mCurrencies;

    public ValuteAdapter(CurrenciesStorage currenciesStorage) {
        mCurrencies = currenciesStorage.getLoadedList().getCurrencies();
    }

    @Override
    public int getCount() {
        return mCurrencies.size();
    }

    @Override
    public Currency getItem(int position) {
        return mCurrencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getNumCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.valute_item, parent, false);

            ValuteViewHolder holder = new ValuteViewHolder();

            holder.mValuteName = (TextView) view.findViewById(R.id.valute_name);
            holder.mValuteCharCode = (TextView) view.findViewById(R.id.charCode);
            holder.mValuteNominal = (TextView) view.findViewById(R.id.nominal);
            holder.mValuteNumCode = (TextView) view.findViewById(R.id.numCode);
            holder.mValuteValue = (TextView) view.findViewById(R.id.value);

            view.setTag(holder);
        }

        ValuteViewHolder holder = (ValuteViewHolder) view.getTag();


        Currency currency = mCurrencies.get(position);

        holder.mValuteName.setText(currency.getName());
        holder.mValuteValue.setText(String.format("%.2f",currency.getValue()));
        holder.mValuteNumCode.setText("(" + currency.getNumCode() + ")");
        holder.mValuteCharCode.setText(currency.getCharCode());
        holder.mValuteNominal.setText(String.valueOf(Math.round(currency.getNominal())));


        return view;

    }

    private static class ValuteViewHolder {
        private TextView mValuteName;
        private TextView mValuteCharCode;
        private TextView mValuteNumCode;
        private TextView mValuteNominal;
        private TextView mValuteValue;
    }


}
