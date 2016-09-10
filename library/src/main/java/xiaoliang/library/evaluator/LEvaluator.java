package xiaoliang.library.evaluator;

import android.animation.TypeEvaluator;

import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.bean.SpeedType;

/**
 * Created by liuj on 2016/9/9.
 * 动作计算器
 */
public interface LEvaluator<T extends LAnimaBean> extends TypeEvaluator<T> {
    @Override
    T evaluate(float fraction, T startValue, T endValue);

    /**
     * 加速方式
     * 对于能够选择加速方式的动画计算器,
     * 建议重写此方法,并在计算方法中调用
     * 计算器结构会更加清晰
     * @param fraction 真实进度
     * @param speedType 加速类型
     * @return
     */
    float getFraction(float fraction, SpeedType speedType);

}
