package com.savosh.exx.exx11;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.savosh.exx.R;
import com.savosh.exx.exx11.model.ImageInfo;
import com.savosh.exx.util.CommonTools;

public class SplashScreenActivity extends Activity {

    private ProgressBar progressBar;

    public class DownloadImagesTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            int progress = 0;
            publishProgress(progress);
            for(ImageInfo imageInfo : ImageInfo.CACHE){
                Bitmap bitmap = CommonTools.downloadImage(imageInfo.getUrl());
                imageInfo.setImage(bitmap);
                publishProgress(++progress);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Intent intent = new Intent(SplashScreenActivity.this, FirstActivity.class);
            SplashScreenActivity.this.startActivity(intent);
            SplashScreenActivity.this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx8_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.eight_splash_screen_prog_bar);
        progressBar.setMax(ImageInfo.CACHE.size());

        new DownloadImagesTask().execute();
    }


}
