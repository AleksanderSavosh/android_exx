package com.savosh.exx.exx8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.savosh.exx.R;
import com.savosh.exx.exx8.model.DayExchangeRates;
import com.savosh.exx.exx8.model.ExchangeRate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FirstActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx8_first_activity);

        listView = (ListView) findViewById(R.id.eight_first_activity_list);

        ArrayList<DayExchangeRates> list = (ArrayList<DayExchangeRates>) getIntent().getSerializableExtra("data");
        Log.v(getClass().getName(), list.toString());

        CurrencyInfoAdapter adapter = new CurrencyInfoAdapter(this, list);
        //some error
        adapter.sort(new Comparator<DayExchangeRates>() {
            @Override
            public int compare(DayExchangeRates lhs, DayExchangeRates rhs) {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                if(lhs == null || rhs == null || lhs.getDate() == null || rhs.getDate() == null){
                    return 0;
                }

                long left = 0;
                long right = 0;
                try {
                    left = format.parse(lhs.getDate()).getTime();
                    right = format.parse(rhs.getDate()).getTime();
                } catch (Exception unused) {
                    Log.w(getClass().getName(), unused.getMessage(), unused);
                    return 0;
                }
                return left > right ? -1 : (left < right ? 1 : 0);
            }
        });
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DayExchangeRates dayExchangeRatesBase = (DayExchangeRates) parent.getItemAtPosition(position);
                DayExchangeRates dayExchangeRatesAnother = (DayExchangeRates) parent.getItemAtPosition(position + 1);

                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("base", dayExchangeRatesBase);
                intent.putExtra("another", dayExchangeRatesAnother);
                startActivity(intent);
            }
        });


    }

    public class CurrencyInfoAdapter extends ArrayAdapter<DayExchangeRates> {

        private final Context context;

        private class ViewHolder {
            DayExchangeRates dayExchangeRates;
            ExchangeRate exchangeRate;
            TextView date;
            TextView currency;
            TextView sellPrice;
            TextView buyPrice;
            TextView nbuPrice;
        }

        public CurrencyInfoAdapter(Context context, ArrayList<DayExchangeRates> values) {
            super(context, R.layout.exx8_card_view, values);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View rowView = convertView;
            if (rowView == null) {

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.exx8_card_view, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.date = (TextView) rowView.findViewById(R.id.eight_card_view_date);
                viewHolder.currency = (TextView) rowView.findViewById(R.id.eight_card_view_currency);
                viewHolder.sellPrice = (TextView) rowView.findViewById(R.id.eight_card_view_sell);
                viewHolder.buyPrice = (TextView) rowView.findViewById(R.id.eight_card_view_buy);
                viewHolder.nbuPrice = (TextView) rowView.findViewById(R.id.eight_card_view_nbu);

                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
            }

            DayExchangeRates dayExchangeRates = getItem(position);
            ExchangeRate exchangeRate = null;
            for (ExchangeRate item : dayExchangeRates.getExchangeRate()) {
                if (item.getBaseCurrency().equalsIgnoreCase("uah") && item.getCurrency().equalsIgnoreCase("usd")) {
                    exchangeRate = item;
                    break;
                }
            }
            viewHolder.exchangeRate = exchangeRate;
            viewHolder.dayExchangeRates = dayExchangeRates;

            setAllTextViewsNoData(viewHolder);

            if(viewHolder.dayExchangeRates != null) {
                viewHolder.date.setText(viewHolder.dayExchangeRates.getDate());
                if (viewHolder.exchangeRate != null) {
                    viewHolder.currency.setText(viewHolder.exchangeRate.getCurrency());

                    DecimalFormat format = new DecimalFormat("###.####");
                    if (viewHolder.exchangeRate.getSaleRate() != null) {
                        viewHolder.sellPrice.setText(format.format(Double.valueOf(viewHolder.exchangeRate.getSaleRate())));
                    }
                    if (viewHolder.exchangeRate.getPurchaseRate() != null) {
                        viewHolder.buyPrice.setText(format.format(Double.valueOf(viewHolder.exchangeRate.getPurchaseRate())));
                    }
                    if (viewHolder.exchangeRate.getSaleRateNB() != null) {
                        viewHolder.nbuPrice.setText(format.format(Double.valueOf(viewHolder.exchangeRate.getSaleRateNB())));
                    }
                }
            }

            return rowView;
        }

        private void setAllTextViewsNoData(ViewHolder holder){
            String noData = getContext().getString(R.string.eight_first_activity_no_data);
            holder.date.setText(noData);
            holder.currency.setText(noData);
            holder.sellPrice.setText(noData);
            holder.buyPrice.setText(noData);
            holder.nbuPrice.setText(noData);
        }
    }



}
