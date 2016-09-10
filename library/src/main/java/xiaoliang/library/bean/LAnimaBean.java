package xiaoliang.library.bean;

import xiaoliang.library.listener.LProgressListener;

/**
 * Created by LiuJ on 2016/8/31.
 * 这是动画的关键帧基础接口
 * 注意:继承时请保留无参构造器,无参构造器将于添加动作时调用
 */
public class LAnimaBean {
    /**
     * 动作类型
     */
    private int beanType;
    /**
     * 动作进度监听器
     */
    private LProgressListener progressListener;

    public int getBeanType() {
        return beanType;
    }

    public void setBeanType(int beanType) {
        this.beanType = beanType;
    }

    public LProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(LProgressListener progressListener) {
        this.progressListener = progressListener;
    }

}
