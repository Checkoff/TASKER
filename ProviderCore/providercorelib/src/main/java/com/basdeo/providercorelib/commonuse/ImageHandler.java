package com.basdeo.providercorelib.commonuse;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * Created by Eugene on 5/24/16
 */
public class ImageHandler {


    public ImageHandler() {}

    public Bitmap toGrayscale(Bitmap original)
    {
        float[] mat = new float[]{
                0.3f, 0.59f, 0.11f, 0, 0,
                0.3f, 0.59f, 0.11f, 0, 0,
                0.3f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0,};

        int width, height;
        height = original.getHeight();
        width = original.getWidth();

        Paint paint = new Paint();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(mat);
        paint.setColorFilter(filter);
        c.drawBitmap(original, 0, 0, paint);

        return bmpGrayscale;
    }

}
