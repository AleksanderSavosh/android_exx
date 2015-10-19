package com.savosh.exx.exx10;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.savosh.exx.R;

import java.util.ArrayList;

public class GifReflectArrayAdapter extends ArrayAdapter<String> {

    private Context context;

    public GifReflectArrayAdapter(Context context) {
        super(context, R.layout.exx10_list_view_item, new ArrayList<String>());
        this.context = context;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.exx10_list_view_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.ten_item_view_img);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        String url = getItem(position);
        Log.d(getClass().getName(), "gif url: " + url);

        Glide.with(context).load(url).asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).fitCenter().into(viewHolder.imageView);

        return rowView;
    }
}