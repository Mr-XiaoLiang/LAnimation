package xiaoliang.library.exception;

import xiaoliang.library.adapter.BaseAdapter;

/**
 * Created by liuj on 2016/8/31.
 * 动画异常异常
 */
public class AnimationException extends RuntimeException {
    private Class<? extends BaseAdapter> cla;

    public AnimationException(String msg) {
        super(msg);
    }

    public AnimationException(String msg, Class<? extends BaseAdapter> cla) {
        this(msg);
        this.cla = cla;
    }

    public Class getExceptionClass(){
        return cla;
    }

    public String getExceptionClassPath(){
        if(cla!=null){
            return cla.getName();
        }
        return "";
    }

    @Override
    public String toString() {
        return getClass().getName()+":"+getExceptionClassPath()+"-->"+getMessage();
    }
}
