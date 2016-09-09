package xiaoliang.library.adapter;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import java.util.ArrayList;

import xiaoliang.library.bean.LDefaultBean;

/**
 * Created by liuj on 2016/8/31.
 * 一个预设的简单的动画适配器
 * Update by liuj on 2016/9/8
 * 基于某些情况,暂时放弃adapter动画管理模式
 */
public class SimpleAdapter implements BaseAdapter<LDefaultBean> {

    private ArrayList<LDefaultBean> beans;
    private TypeEvaluator evaluator;

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public int getType(int index) {
        return beans.get(index).getAnimaType();
    }

    @Override
    public LDefaultBean getBean(int index) {
        return beans.get(index);
    }

    @Override
    public void runAnimation(int index, View view, LDefaultBean bean) {
        ObjectAnimator objectAnimator;

    }

    @Override
    public long getBeanWhat(int index) {
        return beans.get(index).what;
    }

    public void setAnimation(){

    }

}
