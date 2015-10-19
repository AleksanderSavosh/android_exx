package com.savosh.exx.exx11;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.savosh.exx.R;
import com.savosh.exx.exx11.model.ImageInfo;

public class ScreenSlidePageFragment extends Fragment {

    private int position = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.exx11_screen_fragment, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.eleven_screen_fragment_image);
        imageView.setImageBitmap(ImageInfo.CACHE.get(position).getImage());


        return rootView;
    }
}
