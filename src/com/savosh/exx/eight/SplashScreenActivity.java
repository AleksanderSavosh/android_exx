package com.savosh.exx.eight;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.savosh.exx.R;
import com.savosh.exx.eight.model.DayExchangeRates;
import com.savosh.exx.util.CommonTools;

import java.text.SimpleDateFormat;
import java.util.*;

public class SplashScreenActivity extends Activity {

    private ProgressBar progressBar;

    private class LoadDataTask extends AsyncTask<Void, Integer, ArrayList<DayExchangeRates>> {

        private String datePattern = "dd.MM.yyyy";
        private String dateRequestParamPattern = "{date}";
        private String urlPattern =
                "https://api.privatbank.ua/p24api/exchange_rates?json&date=" + dateRequestParamPattern;

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected ArrayList<DayExchangeRates> doInBackground(Void... params) {
            ArrayList<DayExchangeRates> exchangeRateses = new ArrayList<DayExchangeRates>();

            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = (Calendar) calStart.clone();
            calEnd.add(Calendar.DAY_OF_MONTH, -30);

            int prog = 0;
            Gson gson = new Gson();
            while(!calEnd.equals(calStart)){
                String date = new SimpleDateFormat(datePattern).format(calEnd.getTime());
                String json = CommonTools.doHttpGet(urlPattern.replace(dateRequestParamPattern, date));
                DayExchangeRates rates = gson.fromJson(json, DayExchangeRates.class);
                exchangeRateses.add(rates);
                calEnd.add(Calendar.DAY_OF_MONTH, 1);
                onProgressUpdate(++prog);
            }

            return exchangeRateses;
        }

        @Override
        protected void onPostExecute(ArrayList<DayExchangeRates> list) {
            Log.v(getClass().getName(), list.toString());
            Intent intent = new Intent(SplashScreenActivity.this, FirstActivity.class);
            intent.putExtra("data", list);
            SplashScreenActivity.this.startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eight_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.eight_splash_screen_prog_bar);


        new LoadDataTask().execute();
    }
}
//https://api.privatbank.ua/p24api/exchange_rates?json&date=01.12.2014
//{"date":"01.12.2014","bank":"PB","baseCurrency":980,"baseCurrencyLit":"UAH","exchangeRate":
//        [{"baseCurrency":"UAH","currency":"CAD","saleRateNB":13.2107400,"purchaseRateNB":13.2107400,"saleRate":15.0000000,"purchaseRate":13.0000000},
//        {"baseCurrency":"UAH","currency":"CHF","saleRateNB":15.6389750,"purchaseRateNB":15.6389750,"saleRate":17.0000000,"purchaseRate":15.5000000},
//        {"baseCurrency":"UAH","currency":"EUR","saleRateNB":18.7949200,"purchaseRateNB":18.7949200,"saleRate":20.0000000,"purchaseRate":19.2000000},
//        {"baseCurrency":"UAH","currency":"GBP","saleRateNB":23.6324910,"purchaseRateNB":23.6324910,"saleRate":25.8000000,"purchaseRate":24.0000000},
//        {"baseCurrency":"UAH","currency":"PLZ","saleRateNB":4.4922010,"purchaseRateNB":4.4922010,"saleRate":5.0000000,"purchaseRate":4.2000000},
//        {"baseCurrency":"UAH","currency":"RUB","saleRateNB":0.3052700,"purchaseRateNB":0.3052700,"saleRate":0.3200000,"purchaseRate":0.2800000},
//        {"baseCurrency":"UAH","currency":"SEK","saleRateNB":2.0283750,"purchaseRateNB":2.0283750},
//        {"baseCurrency":"UAH","currency":"UAH","saleRateNB":1.0000000,"purchaseRateNB":1.0000000},
//        {"baseCurrency":"UAH","currency":"USD","saleRateNB":15.0564130,"purchaseRateNB":15.0564130,"saleRate":15.7000000,"purchaseRate":15.3500000},
//        {"baseCurrency":"UAH","currency":"XAU","saleRateNB":17747.7470000,"purchaseRateNB":17747.7470000}]
//}