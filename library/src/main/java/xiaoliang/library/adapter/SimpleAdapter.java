package xiaoliang.library.adapter;

import android.view.View;

import java.util.ArrayList;

import xiaoliang.library.bean.AnimaType;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.exception.AdapterException;

/**
 * Created by liuj on 2016/8/31.
 * 一个预设的简单的动画适配器
 */
public class SimpleAdapter implements BaseAdapter<LDefaultBean> {

    private ArrayList<LDefaultBean> beans;

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public int getType(int index) {
        return beans.get(index).animaType;
    }

    @Override
    public LDefaultBean getBean(int index) {
        return beans.get(index);
    }

    @Override
    public void runAnimation(int index, View view, LDefaultBean bean) {

    }

    @Override
    public long getBeanWhat(int index) {
        return beans.get(index).what;
    }

    public void setAnimation(){

    }

}
