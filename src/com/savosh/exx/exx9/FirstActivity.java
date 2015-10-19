package com.savosh.exx.exx9;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.savosh.exx.R;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends Activity {

    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    private Class task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx9_first_activity);

        editText = (EditText) findViewById(R.id.nine_first_activity_search_string);
        listView = (ListView) findViewById(R.id.nine_first_activity_result);

        Serializable outerAdapter = getIntent().getSerializableExtra("outerAdapter");
        if(outerAdapter instanceof Class){
            Class outerAdapterClass = (Class) outerAdapter;
            try {
                Log.d(FirstActivity.class.getName(), "Init outer adapter");
                Constructor constructor = outerAdapterClass.getConstructor(Context.class);
                adapter = (ArrayAdapter<String>) constructor.newInstance(this);
            } catch (Exception e) {
                Log.e(FirstActivity.class.getName(), e.getMessage());
                Log.e(FirstActivity.class.getName(), e.getMessage(), e);
            }
        }

        if(adapter == null) {
            Log.d(FirstActivity.class.getName(), "Init default adapter");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        }

        listView.setAdapter(adapter);


        Serializable outerTask = getIntent().getSerializableExtra("outerTask");
        if(outerTask instanceof Class){
            task = (Class) outerTask;
        } else {
            task = DownloadGifUrlsTask.class;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = adapter.getItem(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

    public void click(View v){
        try {
            Log.d(FirstActivity.class.getName(), "Init outer adapter");
            Constructor constructor = task.getConstructor(String.class, ArrayAdapter.class);
            AsyncTask<Void, Void, List<String>> asyncTask =
                    (AsyncTask) constructor.newInstance(editText.getText().toString(), adapter);
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(FirstActivity.class.getName(), e.getMessage());
            Log.e(FirstActivity.class.getName(), e.getMessage(), e);
        }
    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String urlPattern = "http://api.giphy.com/v1/gifs/search?q={0}&api_key=dc6zaTOxFJmzC";
//        String json = CommonTools.doHttpGet(MessageFormat.format(urlPattern, URLEncoder.encode("funny cat", "UTF-8")));
//        List<String> list = new GsonBuilder().registerTypeAdapter(List.class, new UrlDeserializer()).create().fromJson(json, List.class);
//        System.out.println(list);
//    }
}
