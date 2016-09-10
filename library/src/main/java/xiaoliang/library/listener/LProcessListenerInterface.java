package xiaoliang.library.listener;

import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.object.LAnima;

/**
 * Created by liuj on 2016/9/10.
 * 进度监听器的接口
 */
public interface LProcessListenerInterface<T extends LAnima> {
    /**
     * 动画准备
     * @param name 准备任务的名字
     * @param what 准备任务的编号
     * @param obj 准备任务对象
     */
    void onPrepare(String name,int what,T obj);

    /**
     * 动画开始
     * @param name 开始任务的名字
     * @param what 开始任务的编号
     * @param obj 开始任务对象
     */
    void onStart(String name,int what,T obj);

    /**
     * 单任务重复执行
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    void onRepeat(String name,int what,T obj);

    /**
     * 当任务被取消
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    void onCancel(String name,int what,T obj);

    /**
     * 发生异常
     * @param e 异常对象
     */
    void onError(String name,int what,T obj,AnimationException e);

    /**
     * 任务结束
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    void  onConclude(String name,int what,T obj);
}
