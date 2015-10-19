package com.savosh.exx.exx9;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.savosh.exx.util.CommonTools;
import com.savosh.exx.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by savosh on 18.04.15.
 */
public class DownloadGifUrlsTask<T extends ArrayAdapter> extends AsyncTask<Void, Void, List<String>> {
    protected String urlPattern = "http://api.giphy.com/v1/gifs/search?q={0}&api_key=dc6zaTOxFJmzC";
    protected String search;
    protected T adapter;

    public DownloadGifUrlsTask(String search, T adapter) {
        this.search = search;
        this.adapter = adapter;
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        Log.d(getClass().getName(), "Start download");
        if(StringUtils.isEmpty(search)) {
            return null;
        }
        try {
            String url = getUrl();
            if(url == null){
                return null;
            }
            String json = CommonTools.doHttpGet(url);
            return (List<String>) new GsonBuilder()
                    .registerTypeAdapter(List.class, new UrlDeserializer()).create().fromJson(json, List.class);
        } catch (JsonSyntaxException e){
            Log.e(DownloadGifUrlsTask.class.getName(), e.getMessage());
            Log.e(DownloadGifUrlsTask.class.getName(), e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> list) {
        Log.d(getClass().getName(), "End download");
        if(list != null && !list.isEmpty()){
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    protected String getUrl() {
        try {
            return MessageFormat.format(urlPattern, URLEncoder.encode(search, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Log.e(DownloadGifUrlsTask.class.getName(), e.getMessage());
            Log.e(DownloadGifUrlsTask.class.getName(), e.getMessage(), e);
        }
        return null;
    }
}