package xiaoliang.library.listener;

/**
 * Created by liuj on 2016/8/31.
 * 进度监视器
 */
public interface LProgressListener {
    /**
     * 进度监视器
     * @param p 进度
     * @param index 动作序号
     */
    void onProgressChange(float p,int index);
}
