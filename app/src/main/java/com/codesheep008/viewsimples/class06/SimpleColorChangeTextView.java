package com.codesheep008.viewsimples.class06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.codesheep008.viewsimples.utils.UIUtils;

/**
 * @desc 文字跟随进度条变色的textview
 * extends AppCompatTextView的好处是不用管测量
 */
public class SimpleColorChangeTextView extends AppCompatTextView {
    private static final String mText = "享学课堂";
    private int textSize = 18;

    public SimpleColorChangeTextView(Context context) {
        super(context);
    }

    public SimpleColorChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleColorChangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCenterLine(canvas);
        drawText(canvas);
    }

    private void drawCenterLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStrokeWidth();
        paint.setStrokeWidth(UIUtils.dip2px(getContext(), 2));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //画一条横着的中心线2
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
    }

    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(UIUtils.dip2px(getContext(), 15));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //默认是left
        paint.setTextAlign(Paint.Align.LEFT);
        //获取文字的长度
        float mtextWidth = paint.measureText(mText);
//        int startX =
        canvas.drawText(mText, 0, getHeight() / 2, paint);
    }
}
