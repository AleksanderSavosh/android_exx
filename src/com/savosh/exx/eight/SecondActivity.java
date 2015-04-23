package com.savosh.exx.eight;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.savosh.exx.R;
import com.savosh.exx.eight.model.DayExchangeRates;
import com.savosh.exx.eight.model.ExchangeRate;
import com.savosh.exx.eight.model.ExchangeRateDiv;

import java.text.DecimalFormat;
import java.util.*;

public class SecondActivity extends Activity {

    private TextView date;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eight_second_activity);

        date = (TextView) findViewById(R.id.eight_second_activity_date);
        listView = (ListView) findViewById(R.id.eight_second_activity_list);

        DayExchangeRates dayExchangeRatesBase = (DayExchangeRates) getIntent().getSerializableExtra("base");
        DayExchangeRates dayExchangeRatesAnother = (DayExchangeRates) getIntent().getSerializableExtra("another");
        date.setText(dayExchangeRatesBase.getDate());


        listView.setAdapter(new CurrencyInfoAdapter(this, getListFromDayExchangeRates(dayExchangeRatesBase, dayExchangeRatesAnother)));
    }

    private List<ExchangeRateDiv> getListFromDayExchangeRates(DayExchangeRates base, DayExchangeRates another){
        ArrayList<ExchangeRateDiv> result = new ArrayList<ExchangeRateDiv>();

        Map<String, ExchangeRate> anotherMap = getMapFromList(another.getExchangeRate());

        for(ExchangeRate baseRate : base.getExchangeRate()){
            ExchangeRate anotherRate = anotherMap.get(baseRate.getCurrency());
            if(anotherRate != null && baseRate != null){
                result.add(new ExchangeRateDiv(baseRate, anotherRate));
            }
        }

        return result;
    }

    private Map<String, ExchangeRate> getMapFromList(List<ExchangeRate> exchangeRate){
        Map<String, ExchangeRate> map = new HashMap<String, ExchangeRate>();
        for(ExchangeRate rate : exchangeRate){
            map.put(rate.getCurrency(), rate);
        }
        return map;
    }


    public class CurrencyInfoAdapter extends ArrayAdapter<ExchangeRateDiv> {
        final int GOOD_COLOR = getResources().getColor(R.color.eight_second_card_view_good);
        final int BAD_COLOR = getResources().getColor(R.color.eight_second_card_view_bad);

        private final Context context;

        private class ViewHolder {
            ExchangeRateDiv dayExchangeRateDiv;
            TextView currency;
            TextView sellPrice;
            TextView sellDiv;
            TextView buyPrice;
            TextView buyDiv;
            TextView nbuPrice;
            TextView nbuDiv;
        }

        public CurrencyInfoAdapter(Context context, List<ExchangeRateDiv> values) {
            super(context, R.layout.eight_second_card_view, values);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View rowView = convertView;
            if (rowView == null) {

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.eight_second_card_view, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.currency = (TextView) rowView.findViewById(R.id.eight_second_card_view_currency);
                viewHolder.sellPrice = (TextView) rowView.findViewById(R.id.eight_second_card_view_sell);
                viewHolder.sellDiv = (TextView) rowView.findViewById(R.id.eight_second_card_view_sell_div);
                viewHolder.buyPrice = (TextView) rowView.findViewById(R.id.eight_second_card_view_buy);
                viewHolder.buyDiv = (TextView) rowView.findViewById(R.id.eight_second_card_view_buy_div);
                viewHolder.nbuPrice = (TextView) rowView.findViewById(R.id.eight_second_card_view_nbu);
                viewHolder.nbuDiv = (TextView) rowView.findViewById(R.id.eight_second_card_view_nbu_div);

                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
            }

            setAllTextViewsNoData(viewHolder);
            viewHolder.dayExchangeRateDiv = getItem(position);

            if(viewHolder.dayExchangeRateDiv != null) {
                ExchangeRate exchangeRate = viewHolder.dayExchangeRateDiv.getBase();

                if (exchangeRate != null) {
                    viewHolder.currency.setText(exchangeRate.getCurrency());

                    setPrice(viewHolder.sellPrice, exchangeRate.getSaleRate());
                    setPrice(viewHolder.buyPrice, exchangeRate.getPurchaseRate());
                    setPrice(viewHolder.nbuPrice, exchangeRate.getSaleRateNB());


                    setDiv(viewHolder.sellDiv, viewHolder.dayExchangeRateDiv.getDivSell());
                    setDiv(viewHolder.buyDiv, viewHolder.dayExchangeRateDiv.getDivBuy());
                    setDiv(viewHolder.nbuDiv, viewHolder.dayExchangeRateDiv.getDivNbu());
                }
            }


            return rowView;
        }

        private void setPrice(TextView view, String value){
            DecimalFormat format = new DecimalFormat("###.####");
            if (value != null) {
                view.setText(format.format(Double.valueOf(value)));
            }
        }

        private void setDiv(TextView view, String value){
            DecimalFormat format = new DecimalFormat("###.####");
            if (value != null) {
                Double nbuDiv = Double.valueOf(value);
                view.setText(format.format(nbuDiv));
                if(nbuDiv > 0){
                    view.setTextColor(GOOD_COLOR);
                } else {
                    view.setTextColor(BAD_COLOR);
                }
            }
        }

        private void setAllTextViewsNoData(ViewHolder holder){
            String noData = getContext().getString(R.string.eight_first_activity_no_data);
            holder.currency.setText(noData);
            holder.sellPrice.setText(noData);
            holder.buyPrice.setText(noData);
            holder.nbuPrice.setText(noData);
            holder.sellDiv.setText(noData);
            holder.buyDiv.setText(noData);
            holder.nbuDiv.setText(noData);
        }
    }


}
