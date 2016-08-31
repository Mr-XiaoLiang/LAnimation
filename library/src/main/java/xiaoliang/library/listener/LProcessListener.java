package xiaoliang.library.listener;

import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.object.LAnimaObject;

/**
 * Created by liuj on 2016/8/31.
 * 过程监听器
 */
public interface LProcessListener {
    /**
     * 动画开始
     * @param name 开始任务的名字
     * @param what 开始任务的编号
     * @param obj 开始任务对象
     */
    void onStart(String name,int what,LAnimaObject obj);

    /**
     * 执行下一个动作
     * @param name 动作名
     * @param what 动作编号
     * @param bean 动作对象
     */
    void onNext(String name,int what,LAnimaBean bean);

    /**
     * 发生异常
     * @param e 异常对象
     */
    void onError(Exception e);

    /**
     * 任务结束
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
   void  onConclude(String name,int what,LAnimaObject obj);
}
