package xiaoliang.lanimation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuj on 2016/9/10.
 * 这是一个绘制基准线的View,
 * 没什么用,仅仅是在屏幕上绘制几个基准线来确定View的动画移动方式
 */
public class BaseLineView extends View {
    public BaseLineView(Context context) {
        super(context);
    }

    public BaseLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        for(int i = 0;i<10;i++){
            canvas.drawLine(i*100,1000,i*100,0,paint);
        }
        canvas.drawText("x=0",0,500,paint);
        canvas.drawText("x=200",200,500,paint);
        canvas.drawText("x=500",500,500,paint);
        canvas.drawText("x=700",700,500,paint);
    }
}
