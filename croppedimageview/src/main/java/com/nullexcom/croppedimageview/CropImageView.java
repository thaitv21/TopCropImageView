package com.nullexcom.croppedimageview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

public class CropImageView extends AppCompatImageView {
    private static final String TAG = "CropImageView";

    public CropImageView(Context context) {
        super(context);
//        resizeBitmap();
    }

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        resizeBitmap();
    }

    public CropImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        resizeBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        resizeBitmap();
    }

    private void resizeBitmap() {
        Drawable drawable = getDrawable();
        Log.d(TAG, "resizeBitmap: ");
        Bitmap bitmap;
        if (drawable != null) {
            bitmap = drawableToBitmap(drawable);
        } else {
            bitmap = getDrawingCache();
        }
        if (bitmap == null) {
            return;
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, getMeasuredWidth(), getMeasuredWidth() * bitmap.getHeight() / bitmap.getWidth(), false);
        Log.d(TAG, "resizeBitmap: " + bitmap.getWidth());
        Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), getMeasuredHeight());
        setImageBitmap(croppedBitmap);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
//        Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), getMeasuredHeight());
//        super.setImageBitmap(croppedBitmap);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
