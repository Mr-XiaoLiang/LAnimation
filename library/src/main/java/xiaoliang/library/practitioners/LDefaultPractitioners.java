package xiaoliang.library.practitioners;

import android.view.View;

import xiaoliang.library.bean.LDefaultBean;

/**
 * Created by liuj on 2016/9/9.
 * 默认的执行者
 * 本执行者要求数据源为LDefaultBean或其子类
 * 要求被执行对象为View及其子类
 * 为其提供基本的动画操作
 */
public class LDefaultPractitioners extends LPractitioners<LDefaultBean,View> {
    @Override
    public void perform(View view, LDefaultBean bean) {
        view.setTranslationX(bean.x);
        view.setTranslationY(bean.y);
        view.setRotation(bean.rotate);
        if(bean.color!=null)
            view.setBackgroundColor(bean.color);
        view.setScaleX(bean.width);
        view.setScaleY(bean.height);
    }

    @Override
    public void setAnimation(LDefaultBean bean) {
        perform(view,bean);
    }
}
