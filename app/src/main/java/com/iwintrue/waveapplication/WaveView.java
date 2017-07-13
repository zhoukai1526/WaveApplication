package com.iwintrue.waveapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by zhoukai on 2017/7/9.
 * 利用贝塞尔曲线与动画实现水波纹效果
 */

public class WaveView extends View {

    private Paint paint;
    private Path path;
    private float waveLength ;
    private float waveHeight ;
    private float waveSpeed ;
    private Bitmap bitmap;
    private int waveColor ;
    private int strokeWidth = 3;
    private Region region;
    private int width,height;
    public int translateX ;
    private float distanceY;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.waveStyleable);
        waveLength = array.getFloat(R.styleable.waveStyleable_waveLength,300);
        waveColor = array.getColor(R.styleable.waveStyleable_waveColor,0x00ff00);
        waveHeight = array.getFloat(R.styleable.waveStyleable_waveHeight,100);
        waveSpeed = array.getFloat(R.styleable.waveStyleable_waveSpeed,5);
        distanceY = array.getFloat(R.styleable.waveStyleable_distanceY,100);

        Drawable waveTopICon = array.getDrawable(R.styleable.waveStyleable_waveTopIcon);
        array.recycle();
        bitmap = drawableToBitmap(waveTopICon);
        initPaint();
        startAnimal();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(waveColor);
        paint.setStrokeWidth(strokeWidth);
        //绘制贝塞尔曲线的path
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制贝塞尔曲线
        drawPath(canvas,path);
        //绘制wave上部的头像
        drawIcon(canvas);
    }

    private void drawIcon(Canvas canvas) {
        float baseLine = height-distanceY;
        if(region.getBounds().top==baseLine){
            canvas.drawBitmap(bitmap,width/2-bitmap.getWidth()/2,region.getBounds().bottom-bitmap.getHeight(),paint);
        }else {
            if(region.getBounds().top==0){
                canvas.drawBitmap(bitmap,width/2-bitmap.getWidth()/2,height-bitmap.getHeight()-distanceY,paint);
            }
            canvas.drawBitmap(bitmap,width/2-bitmap.getWidth()/2,region.getBounds().top-bitmap.getHeight(),paint);
        }
    }

    private void drawPath(Canvas canvas, Path path) {

        path.reset();
        //path的起始点，向手机外多绘制一段
        path.moveTo(-2* waveLength +translateX,getHeight()-distanceY);
        for(int i = 0; i<getWidth()+ waveLength; i+= waveLength){
            path.rQuadTo(waveLength /2,-waveHeight, waveLength,0);
            path.rQuadTo(waveLength /2,waveHeight, waveLength,0);
        }
        region = new Region();
        Region clip = new Region();
        clip.set((int) (getWidth()/2-0.1),0,getWidth()/2,getHeight()*2);
        region.setPath(path,clip);

        path.lineTo(getWidth(),getHeight());
        path.lineTo(-waveLength,getHeight());
        path.close();

        canvas.drawPath(path,paint);
    }


    public  void  startAnimal(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                translateX += waveSpeed;
                if(-2* waveLength +translateX >= 0){
                    translateX = 0;
                }
                postInvalidate();
            }
        });
        animator.start();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽高模式
        int widthMode  = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        width  = MeasureSpec.getSize(widthMeasureSpec);
        height  = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST){
            width = (int) waveLength;
        }
        if(heightMode == MeasureSpec.AT_MOST){
            height = (int) (waveHeight+ distanceY+bitmap.getHeight());
        }
        setMeasuredDimension(width,height);

    }

    /**
     * dp转化为px
     * @param dpValue
     * @param context
     * @return
     */

    public  float  dp2px(float dpValue,Context context){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }

    /**
     * 如果图片底部有很多空白会导致图片不能贴到波纹底部
     * @param bitmap
     * @return
     */

    public  Bitmap makeRoundCorner(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int left = 0, top = 0, right = width, bottom = height;
        float roundPx = height/2;
        if (width > height) {
            left = (width - height)/2;
            top = 0;
            right = left + height;
            bottom = height;
        } else if (height > width) {
            left = 0;
            top = (height - width)/2;
            right = width;
            bottom = top + width;
            roundPx = width/2;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public  Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return makeRoundCorner(bitmap);

    }

}
