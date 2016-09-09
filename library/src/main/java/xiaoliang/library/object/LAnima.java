package xiaoliang.library.object;

import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.listener.LProcessListener;

/**
 * Created by Liuj on 2016/9/8.
 * 动画任务
 */
public abstract class LAnima<T extends LAnimaBean> {

    public int what = 0;

    public String name = "";

    /**
     * 开始执行动画
     */
    public abstract void start();
    /**
     * 添加动作,直接添加动作
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param bean
     */
    public abstract void add(T bean);

    /**
     * 添加动作,使用动作构造器
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param bean
     */
    public abstract void add(BeanBuilder<T> bean);

    /**
     * 添加一个进度监听器
     * @param processListener
     */
    public abstract void addListener(LProcessListener processListener);

}
