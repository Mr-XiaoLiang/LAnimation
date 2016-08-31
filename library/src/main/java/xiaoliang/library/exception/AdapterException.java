package xiaoliang.library.exception;

import xiaoliang.library.adapter.BaseAdapter;

/**
 * Created by liuj on 2016/8/31.
 * 适配器异常
 */
public class AdapterException extends RuntimeException {
    private Class<? extends BaseAdapter> cla;

    public AdapterException(String msg) {
        super(msg);
    }

    public AdapterException(String msg, Class<? extends BaseAdapter> cla) {
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
