package xiaoliang.library.bean;

/**
 * Created by liuj on 2016/9/9.
 * 默认的动作构造器
 * 本动作构造器是对动作的封装,达到动作步骤化的目的,
 * 简化代码的复杂程度
 * 某种程度上借鉴了其他成名框架.如RxJava
 */
public abstract class DefaultBeanBuilder implements BeanBuilder<LDefaultBean>{

    private LDefaultBean defaultBean;
    private LDefaultBean.Build build;

    public DefaultBeanBuilder() {
        build = new LDefaultBean.Build();
    }

    /**
     * 制造方法,由使用时来实现
     * 对传入的Build进行参数设置
     * 然后调用build()方法
     * 通知框架动作完成
     * @param build
     */
    public abstract void make(LDefaultBean.Build build);
    @Override
    public void build(){
        defaultBean = new LDefaultBean(build);
    }
    @Override
    public LDefaultBean getBean(){
        return defaultBean;
    }

}
