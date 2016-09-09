package xiaoliang.library.evaluator;

import android.animation.TypeEvaluator;

import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by liuj on 2016/9/9.
 * 动作计算器
 */
public interface LEvaluator<T extends LAnimaBean> extends TypeEvaluator<T> {
    @Override
    T evaluate(float fraction, T startValue, T endValue);
}
