package com.savosh.exx.exx11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.savosh.exx.R;
import com.savosh.exx.exx11.model.ImageInfo;

import java.util.ArrayList;

public class FirstActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx11_first_activity);

        listView = (ListView) findViewById(R.id.eleven_first_activity_images_list);


        ImageReflectArrayAdapter imageReflectArrayAdapter =
                new ImageReflectArrayAdapter(this, new ArrayList<ImageInfo>(ImageInfo.CACHE));

        listView.setAdapter(imageReflectArrayAdapter);

        listView.smoothScrollToPosition(ImageInfo.currentItemPosition);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(getClass().getName(), "position = " + position);
                ImageInfo.currentItemPosition = position;
                Intent intent = new Intent(FirstActivity.this, ScreenSlidePagerActivity.class);
                FirstActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        listView.smoothScrollToPosition(ImageInfo.currentItemPosition);
    }
}