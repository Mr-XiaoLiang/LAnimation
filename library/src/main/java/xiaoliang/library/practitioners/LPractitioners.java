package xiaoliang.library.practitioners;

import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by liuj on 2016/9/9.
 * 动作执行者
 */
public abstract class LPractitioners <T extends LAnimaBean,V> {

    protected V view;
    protected String propertyName = "animation";

    /**
     * 执行动画
     * @param view 被执行者
     * @param bean 动作要求
     */
    public abstract void perform(V view, T bean);

    /**
     * 反射回调方法，用于被动画定时器调用
     * @param bean
     */
    public void setAnimation(T bean){
        perform(view,bean);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
