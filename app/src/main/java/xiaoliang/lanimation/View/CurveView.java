package xiaoliang.lanimation.View;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import xiaoliang.library.algorithm.Curve;

/**
 * Created by LiuJ on 2016/8/31.
 * 本View并非功能View,而是用于探究贝塞尔曲线的公式算法
 */
public class CurveView extends View {
    public CurveView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //此处为官方Path中的三阶曲线绘制方法.
        Path path = new Path();
        path.moveTo(200,200);
        path.cubicTo(200,400,400,400,400,200);
        //此处调用我们实现的三阶曲线方法.进行对比
        path.moveTo(200,400);
        for(int i = 0;i<=100;i++){
            int x = (int) Curve.thirdOrder(i*0.01f,200,200,400,400);
            int y = (int) Curve.thirdOrder(i*0.01f,400,600,600,400);
            path.lineTo(x,y);
        }
        canvas.drawPath(path,new Paint());
    }
}
