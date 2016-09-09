package xiaoliang.library.bean;

import xiaoliang.library.listener.LProgressListener;

/**
 * Created by LiuJ on 2016/8/31.
 * 这是动画的关键帧基础接口
 * ps：这里吐槽下，真的不习惯写备注，但是不写的话感觉逼格掉了有好多。
 * 君不见各种牛逼的框架都是好多注释的，一行代码十行注释。
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
