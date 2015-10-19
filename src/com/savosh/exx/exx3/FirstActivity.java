package com.savosh.exx.exx3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.savosh.exx.R;
import com.savosh.exx.util.CommonTools;

public class FirstActivity extends Activity{

    private LinearLayout contentLayout;


    private static int downloadCount = 0;
    public static class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {

        private Context context;
        private LinearLayout content;

        public DownloadImageTask(Context context, LinearLayout content) {
            this.context = context;
            this.content = content;
        }

        private String[] urls = {
                "https://s-media-cache-ak0.pinimg.com/originals/3d/19/e2/3d19e22f8fc92cdbd53337558220e262.jpg",
                "http://www.businessinsider.com/image/4f3433986bb3f7b67a00003c/cute-cat.jpg",
                "http://www.slopemedia.org/wp-content/uploads/2015/02/cats.jpg",
                "http://i.ytimg.com/vi/mSFTRoBY99s/hqdefault.jpg"
        };

        protected Bitmap doInBackground(Void... voids) {
            String url = urls[ downloadCount++ % urls.length ];
            Log.v(getClass().getName(),"download image url: " + url);

            return CommonTools.downloadImage(url);
        }


        protected void onPostExecute(Bitmap result) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.exx3_item, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.three_items_img);
            TextView textView = (TextView) view.findViewById(R.id.three_items_txt);

            imageView.setImageBitmap(result);
            textView.setText("Fine cat");//some text from internet maybe

            content.addView(view);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx3_first_activity);

        contentLayout = (LinearLayout) findViewById(R.id.three_first_activity_linear_layout);
    }

    public void click(View v){
        if(v.getId() == R.id.three_first_activity_btn_add){
            new DownloadImageTask(this, contentLayout).execute();
        }
    }

}
