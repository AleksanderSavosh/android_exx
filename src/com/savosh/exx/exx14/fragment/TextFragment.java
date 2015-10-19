package com.savosh.exx.exx14.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.savosh.exx.R;

public class TextFragment extends Fragment {

    public static String TEXT_EXTRA = "com.savosh.exx.exx14.fragment.TextFragment.text_extra";
    public static String TAG = "TEXT_FRAGMENT";

    public static TextFragment create(String text){
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_EXTRA, text);
        TextFragment textFragment = new TextFragment();
        textFragment.setArguments(bundle);
        return textFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.exx14_text_fragment, container, false);

        String text = getArguments().getString(TEXT_EXTRA);
        ((TextView) v.findViewById(R.id.exx14_text_fragment_text)).setText(text);
//        LogUtil.toLog(TAG, "Extra text: " + text);

        return v;
    }


}
