package com.savosh.exx.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.savosh.exx.R;

public class CustomImageView extends ImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAttrs(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttrs(context, attrs);
    }

    int pWidth = -1;
    int pHeight = -1;
    float radiusTopLeft = 0;
    float radiusTopRight = 0;
    float radiusBottomRight = 0;
    float radiusBottomLeft = 0;

    private void handleAttrs(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomImageView,
                0, 0);
        try {
            pWidth = a.getInt(R.styleable.CustomImageView_pWidth, -1);
            pHeight = a.getInt(R.styleable.CustomImageView_pHeight, -1);
            radiusTopLeft = radiusTopRight = radiusBottomRight = radiusBottomLeft = a.getDimension(R.styleable.CustomImageView_radius, -1f);
            radiusTopLeft = a.getDimension(R.styleable.CustomImageView_radiusTopLeft, radiusTopLeft);
            radiusTopRight = a.getDimension(R.styleable.CustomImageView_radiusTopRight, radiusTopRight);
            radiusBottomRight = a.getDimension(R.styleable.CustomImageView_radiusBottomRight, radiusBottomRight);
            radiusBottomLeft = a.getDimension(R.styleable.CustomImageView_radiusBottomLeft, radiusBottomLeft);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radiusTL = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radiusTopLeft, getResources().getDisplayMetrics());
        float radiusTR = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radiusTopRight, getResources().getDisplayMetrics());
        float radiusBR = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radiusBottomRight, getResources().getDisplayMetrics());
        float radiusBL = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radiusBottomLeft, getResources().getDisplayMetrics());
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, new float[]{
                radiusTL, radiusTL,
                radiusTR, radiusTR,
                radiusBR, radiusBR,
                radiusBL, radiusBL
        }, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable d = getDrawable();

        // ceil not round - avoid thin vertical gaps along the left/right edges
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        if(d != null && pWidth > 0 && pHeight > 0){

            width = width == 0 ? height / pHeight * pWidth : width;
            height = height == 0 ? width / pWidth * pHeight : height;

            if(width/pWidth < height/pHeight){
                height = (width/pWidth) * pHeight;
            } else {
                width = (height/pHeight) * pWidth;
            }

            setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


}
