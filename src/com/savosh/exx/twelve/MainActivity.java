package com.savosh.exx.twelve;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.savosh.exx.R;
import com.savosh.exx.util.CommonTools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public List<Notice> noticeList = new ArrayList<Notice>(){{
        add(new Notice());
        add(new Notice());
        add(new Notice());
        add(new Notice());
        add(new Notice());
        add(new Notice());
    }};

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twelve_main_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.twelve_main_activity_recyclerview);


        Drawable drawable;
        if(Build.VERSION.SDK_INT >= 22){
            drawable = MainActivity.this.getResources().getDrawable(R.drawable.ift, null);
        } else {
            drawable = MainActivity.this.getResources().getDrawable(R.drawable.ift);
        }
        byte[] img = CommonTools.getBitmapAsByteArrayJpeg(((BitmapDrawable) drawable).getBitmap());
        noticeList.get(0).img = img;
        noticeList.get(3).img = img;
        noticeList.get(5).img = img;


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new Adapter(noticeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView titleTextView;
            TextView textTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.twelve_card_view_image_16x9);
                titleTextView = (TextView) itemView.findViewById(R.id.twelve_card_view_title);
                textTextView = (TextView) itemView.findViewById(R.id.twelve_card_view_text);
            }
        }

        private List<Notice> noticeList;
        public Adapter(List<Notice> noticeList){
            this.noticeList = noticeList;
        }

        @Override
        public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.twelve_card_view, parent, false);
            // set the view's size, margins, paddings and layout parameters
            // ...
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
            // - get element from your dataset at this position
            Notice notice = noticeList.get(position);

            // - replace the contents of the view with that element
            if(notice.img != null) {
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageBitmap(CommonTools.getByteArrayAsBitmap(notice.img));
            } else {
                holder.imageView.setVisibility(View.GONE);
            }
            if(notice.title != null) {
                holder.titleTextView.setVisibility(View.VISIBLE);
                holder.titleTextView.setText(notice.title);
            } else {
                holder.titleTextView.setVisibility(View.GONE);
            }
            if(notice.text != null) {
                holder.textTextView.setVisibility(View.VISIBLE);
                holder.textTextView.setText(notice.text);
            } else {
                holder.textTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return noticeList.size();
        }
    }
}
