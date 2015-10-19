package com.savosh.exx.exx11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.savosh.exx.R;
import com.savosh.exx.exx11.model.ImageInfo;

import java.util.List;


public class ImageReflectArrayAdapter extends ArrayAdapter<ImageInfo> {

    public ImageReflectArrayAdapter(Context context, List<ImageInfo> objects) {
        super(context, R.layout.exx11_list_view_item, objects);
    }

    private class ViewHolder {
        TextView header;
        ImageView imageView;
        TextView footer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.exx11_list_view_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) row.findViewById(R.id.eleven_list_view_item_img);
            viewHolder.header = (TextView) row.findViewById(R.id.eleven_list_view_item_header);
            viewHolder.footer = (TextView) row.findViewById(R.id.eleven_list_view_item_footer);

            row.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) row.getTag();
        ImageInfo imageInfo = getItem(position);
        viewHolder.imageView.setImageBitmap(imageInfo.getImage());
        viewHolder.header.setText(imageInfo.getHeader());
        viewHolder.footer.setText(imageInfo.getFrom());

        return row;
    }

}
