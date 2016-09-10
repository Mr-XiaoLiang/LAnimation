package xiaoliang.library.evaluator;

import android.graphics.Color;

import xiaoliang.library.algorithm.Curve;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.bean.SpeedType;

/**
 * Created by liuj on 2016/9/9.
 * 默认的算法计算器
 * 注意:本类仅仅兼容LDefaultBean,
 * 对于LDefaultBean的子类,仅仅兼容LDefaultBean部分.
 */
public class LDefaultEvaluator implements LEvaluator<LDefaultBean> {

    private float minSpeed = 0.5f;
    private float maxSpeed = 1.5f;
    private int a = 0,r = 0,g = 0,b = 0;
    private int x = 0,y = 0;
    private float width = 0,height = 0;
    private float rotate= 0;

    @Override
    public LDefaultBean evaluate(float fraction, LDefaultBean startValue, LDefaultBean endValue) {
        LDefaultBean.Build build = new LDefaultBean.Build();
        if(endValue.getProgressListener()!=null)
            endValue.getProgressListener().onProgressChange(fraction,endValue.what);
        fraction = getFraction(fraction,endValue.speedType);
        //位置变化
        switch (endValue.animaType){
            case CURVE:
                x = (int) Curve.thirdOrder(fraction,startValue.x,endValue.x1,endValue.x,endValue.x2);
                y = (int) Curve.thirdOrder(fraction,startValue.y,endValue.y1,endValue.y,endValue.y2);
                break;
            case MOVE:
                x = endValue.x;
                y = endValue.y;
                break;
            case LINE:
                x = (int) ((endValue.x-startValue.x)*fraction);
                y = (int) ((endValue.y-startValue.y)*fraction);
                break;
        }
        build.moveTo(x,y);
        //颜色变化
        a = (int) ((Color.alpha(startValue.color)-Color.alpha(endValue.color))*fraction+Color.alpha(startValue.color));
        r = (int) ((Color.red(startValue.color)-Color.red(endValue.color))*fraction+Color.red(startValue.color));
        g = (int) ((Color.green(startValue.color)-Color.green(endValue.color))*fraction+Color.green(startValue.color));
        b = (int) ((Color.blue(startValue.color)-Color.blue(endValue.color))*fraction+Color.blue(startValue.color));
        build.setColor(Color.argb(a,r,g,b));
        //尺寸变化
        width = (startValue.width-endValue.width)*fraction+startValue.width;
        height = (startValue.height-endValue.height)*fraction+startValue.height;
        build.setWidth(width);
        build.setHeight(height);
        //旋转角度
        rotate = (startValue.rotate-endValue.rotate)*fraction+startValue.rotate;
        build.setRotate(rotate);
        return new LDefaultBean(build);
    }

    /**
     * 模拟加速减速动画效果
     * 仅仅模拟匀加速和匀减速运动,
     * 对于曲线加速,请重写此方法
     * 速度变化区间为minSpeed - maxSpeed
     * @param fraction 当前真实进度
     * @param speedType 加速模式
     * @return 加速后的进度
     */
    @Override
    public float getFraction(float fraction, SpeedType speedType){
        switch (speedType){
            case SLOW_DOWN://减速
                return maxSpeed*fraction+0.5f*(minSpeed-maxSpeed)*fraction*fraction;
            case SPEED_UP://加速
                return minSpeed*fraction+0.5f*(maxSpeed-minSpeed)*fraction*fraction;
            case UNIFORM://匀速
                return fraction;
            case DOWN_TO_UP://先减再加
                if(fraction<0.5){//减速
                    return maxSpeed*fraction+(minSpeed-maxSpeed)*fraction*fraction;
                }else{//加速
                    return minSpeed*(fraction-0.5f)+(maxSpeed-minSpeed)*(fraction-0.5f)*(fraction-0.5f)+0.5f;
                }
            case UP_TO_DOWN://先加再减
                if(fraction>0.5){//减速
                    return maxSpeed*(fraction-0.5f)+(minSpeed-maxSpeed)*(fraction-0.5f)*(fraction-0.5f)+0.5f;
                }else{//加速
                    return minSpeed*fraction+(maxSpeed-minSpeed)*fraction*fraction;
                }
        }
        return fraction;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        setSpeed(maxSpeed,minSpeed);
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        setSpeed(maxSpeed,minSpeed);
    }

    /**
     * 此处要求,最大速度和最小速度之和为2
     * @param maxSpeed
     * @param minSpeed
     */
    public void setSpeed(float maxSpeed,float minSpeed) {
        float difference = 0;
        if(maxSpeed+minSpeed!=2){
            difference = (maxSpeed+minSpeed-2)*0.5f;
        }
        minSpeed -= difference;
        maxSpeed -= difference;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
    }
}
