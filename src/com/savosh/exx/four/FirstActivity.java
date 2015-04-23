package com.savosh.exx.four;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import com.savosh.exx.R;
import com.savosh.exx.component.MyImageView;
import com.savosh.exx.util.CommonTools;

public class FirstActivity extends Activity {

    private MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_first_activity);


        myImageView = (MyImageView) findViewById(R.id.four_first_activity_img);

        new DownloadImageTask().execute();
    }

    private class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {

        private String[] urls = {
                "http://i.ytimg.com/vi/mSFTRoBY99s/hqdefault.jpg"
        };

        protected Bitmap doInBackground(Void... voids) {
            Log.v(getClass().getName(), "download image url: " + urls[0]);
            return CommonTools.downloadImage(urls[0]);
        }


        protected void onPostExecute(Bitmap result) {
            myImageView.setImageBitmap(result);
        }
    }


}
