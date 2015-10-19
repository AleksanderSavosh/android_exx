package com.savosh.exx.exx10;

import android.widget.ArrayAdapter;
import com.savosh.exx.exx9.DownloadGifUrlsTask;


import java.text.MessageFormat;
import java.util.List;

public class DownloadGifUrlsTaskByBitSteps extends DownloadGifUrlsTask{

    private static int offset = 0;
    private static int limit = 1;
    private static String preSearch;

    public DownloadGifUrlsTaskByBitSteps(String search, ArrayAdapter adapter) {
        super(search, adapter);
        if(search.equalsIgnoreCase(preSearch)){
            offset++;
        } else {
            offset = 0;
        }
        preSearch = search;
    }

    @Override
    protected void onPostExecute(List list) {
        if(list != null && !list.isEmpty()){
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected String getUrl() {
        String urlPattern = super.getUrl() + "&offset={0}&limit={1}";
        return MessageFormat.format(urlPattern, offset, limit);
    }
}
