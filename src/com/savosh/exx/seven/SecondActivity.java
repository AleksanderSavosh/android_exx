package com.savosh.exx.seven;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.*;
import com.savosh.exx.R;
import com.savosh.exx.util.CommonTools;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecondActivity extends Activity {

    private TextView textView;

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            String json = null;
            try {
                URL url = new URL("http://json-time.appspot.com/time.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                json = CommonTools.inputStreamToString(in);

            } catch (Exception e) {
                Log.e(getClass().getName(), e.getMessage());
                Log.e(getClass().getName(), e.getMessage(), e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TimeDeserialiser()).create();
            return gson.fromJson(json, String.class);
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        private class TimeDeserialiser implements JsonDeserializer<String> {
            @Override
            public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                String hour = json.getAsJsonObject().get("hour").getAsString();
                String minute = json.getAsJsonObject().get("minute").getAsString();

                if (hour.length() == 1) {
                    hour = "0" + hour;
                }
                if (minute.length() == 1) {
                    minute = "0" + minute;
                }

                return hour + ":" + minute;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_second_layout);
        textView = (TextView) findViewById(R.id.seven_second_txt_time);
    }

    public void click(View v){
        new GetTimeTask().execute();
    }
}
