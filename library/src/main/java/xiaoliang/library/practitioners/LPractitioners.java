package xiaoliang.library.practitioners;

import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by liuj on 2016/9/9.
 * 动作执行者
 */
public interface LPractitioners <T extends LAnimaBean,V> {
    /**
     * 执行动画
     * @param view 被执行者
     * @param bean 动作要求
     */
    void perform(V view, T bean);

}
