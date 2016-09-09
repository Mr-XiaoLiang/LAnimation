package xiaoliang.library.listener;

import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.object.LAnima;

/**
 * Created by liuj on 2016/8/31.
 * 过程监听器
 * 我决定不定义为接口
 * 定义为类的话,可以根据自己的需要来进行重写
 */
public class LProcessListener<T extends LAnima> {
    /**
     * 动画准备
     * @param name 准备任务的名字
     * @param what 准备任务的编号
     * @param obj 准备任务对象
     */
    public void onPrepare(String name,int what,T obj){}

    /**
     * 动画开始
     * @param name 开始任务的名字
     * @param what 开始任务的编号
     * @param obj 开始任务对象
     */
    public void onStart(String name,int what,T obj){}

    /**
     * 单任务重复执行
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    public void onRepeat(String name,int what,T obj){}

    /**
     * 当任务被取消
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    public void onCancel(String name,int what,T obj){}

    /**
     * 发生异常
     * @param e 异常对象
     */
    public void onError(AnimationException e) {
    }

    /**
     * 任务结束
     * @param name 任务名
     * @param what 任务编号
     * @param obj 任务对象
     */
    public void  onConclude(String name,int what,T obj){}
}
