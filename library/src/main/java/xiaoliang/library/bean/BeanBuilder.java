package xiaoliang.library.bean;

/**
 * Created by liuj on 2016/9/9.
 * 动作构造器
 */
public interface BeanBuilder<T extends LAnimaBean> {
    /**
     * 获取动作
     * @return
     */
    public T getBean();

}
