package xiaoliang.library.adapter;

import android.view.View;

import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by liuj on 2016/8/31.
 * 动画的管理适配器
 */
public interface BaseAdapter<T extends LAnimaBean> {
    /**
     * 获取任务总数
     * @return
     */
    int getCount();

    /**
     * 获取任务类型
     * @param index 任务序号
     * @return
     */
    int getType(int index);

    /**
     * 获取任务对象
     * @param index 任务序号
     * @return
     */
    T getBean(int index);

    /**
     * 执行动画
     * @param index 动作序号
     * @param view 操作View
     * @param bean 动作bean
     */
    void runAnimation(int index,View view,T bean);

    /**
     * 获取动作id
     * @param index 序号
     * @return
     */
    long getBeanWhat(int index);

}
