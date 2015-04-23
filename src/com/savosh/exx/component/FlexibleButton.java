package com.savosh.exx.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import com.savosh.exx.R;

public class FlexibleButton extends Button {

    private float flexibleWidthFactor;
    private float flexibleHeightFactor;

    public FlexibleButton(Context context) {
        super(context);
    }

    public FlexibleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        readCustomAttrs(attrs);
    }

    public FlexibleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readCustomAttrs(attrs);
    }

    private void readCustomAttrs(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.flexible);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.flexible_width_relative_parent: {
                    flexibleWidthFactor = a.getFloat(attr, -1);
                    break;
                }
                case R.styleable.flexible_height_relative_parent: {
                    flexibleHeightFactor = a.getFloat(attr, -1);
                    break;
                }
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        if(0 < flexibleWidthFactor && flexibleWidthFactor <= 1) {
            widthMeasureSpec = MeasureSpec
                    .makeMeasureSpec((int) (displaymetrics.widthPixels * flexibleWidthFactor), MeasureSpec.EXACTLY);
        }
        if(0 < flexibleHeightFactor && flexibleHeightFactor <= 1) {
            heightMeasureSpec = MeasureSpec
                    .makeMeasureSpec((int) (displaymetrics.heightPixels * flexibleHeightFactor), MeasureSpec.EXACTLY);
        }

        Log.v(getClass().getName(), "MeasureSpec width: " + MeasureSpec.toString(widthMeasureSpec));
        Log.v(getClass().getName(), "MeasureSpec height: " + MeasureSpec.toString(heightMeasureSpec));

        Log.v(getClass().getName(), "Screen width: " + displaymetrics.widthPixels);
        Log.v(getClass().getName(), "Screen height: " + displaymetrics.heightPixels);


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


}
