package mlearn.sabachina.com.cn.photoselect.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mlearn.sabachina.com.cn.photoselect.R;

/**
 * 用于相册数字指示器标识
 * Created by zhc on 2017/9/13 0013.
 */

public class SectorIndicatorView extends View {
    /**
     * 外圈颜色
     */
    private int strokeColor;
    /**
     * 内部文字颜色
     */
    private int insideTextColor;
    /**
     * 内部填充色
     */
    private int insideColor;
    /**
     * 外圈弧的宽度
     */
    private float circleStrokeWidth;
    /**
     * 内部数字
     */
    private int text;
    /**
     * 全局画笔
     */
    Paint paint = new Paint();

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setInsideTextColor(int insideTextColor) {
        this.insideTextColor = insideTextColor;
    }

    public void setText(int text) {
        this.text = text;
        postInvalidate();
    }

    public void setInsideColor(int insideColor) {
        this.insideColor = insideColor;
    }

    public SectorIndicatorView(Context context) {
        this(context, null);
    }

    public SectorIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SectorIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SectorIndicatorView);
        strokeColor = typedArray.getColor(R.styleable.SectorIndicatorView_strokeColor, context.getResources().getColor(R.color.text_pressed));
        insideColor = typedArray.getColor(R.styleable.SectorIndicatorView_inside_color, context.getResources().getColor(R.color.colorAlbum));
        insideTextColor = typedArray.getColor(R.styleable.SectorIndicatorView_inside_text_color, context.getResources().getColor(R.color.white));
        circleStrokeWidth = typedArray.getFloat(R.styleable.SectorIndicatorView_circle_width, 3f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int result = 100;
        int widthSize;
        int heightSize;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthSpecMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                widthSize = Math.min(result, widthSpecSize);
                break;
            case MeasureSpec.EXACTLY:
                widthSize = widthSpecSize;
                break;
            default:
                widthSize = result;
                break;
        }
        switch (heightSpecMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                heightSize = Math.min(result, heightSpecSize);
                break;
            case MeasureSpec.EXACTLY:
                heightSize = heightSpecSize;
                break;
            default:
                heightSize = result;
                break;
        }
        int size = Math.min(widthSize, heightSize);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(circleStrokeWidth);
        paint.setColor(strokeColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        float width = getWidth();
        float height = getHeight();
        float outSideRadius = Math.min(width, height) / 2 - circleStrokeWidth / 2;
        float insideRadius = Math.min(width, height) / 2 - circleStrokeWidth;
        canvas.drawCircle(width / 2, height / 2, outSideRadius, paint);
        // 只有当数字大于0的时候，画内圆和数字
        if (text > 0) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(insideColor);
            canvas.drawCircle(width / 2, height / 2, insideRadius, paint);
            paint.setColor(insideTextColor);
            float fontScale = getResources().getDisplayMetrics().scaledDensity;
            paint.setTextSize(12 * fontScale + 0.5f);
            String number = this.text + "";
            float widths = paint.measureText(number);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            //计算基线位置让文字相对于圆心垂直居中
            float Y = height / 2 - (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(number, (width - widths) / 2, Y, paint);
        }
        paint.reset();
    }
}
