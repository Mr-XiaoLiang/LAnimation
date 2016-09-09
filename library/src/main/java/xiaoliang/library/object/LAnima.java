package xiaoliang.library.object;

import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by Liuj on 2016/9/8.
 * 动画任务
 */
public interface LAnima {
    /**
     * 开始执行动画
     */
    public void start();
    /**
     * 添加动作
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param bean
     */
    public void add(LAnimaBean bean);

}
