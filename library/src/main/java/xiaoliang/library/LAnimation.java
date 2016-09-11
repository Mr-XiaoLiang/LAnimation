package xiaoliang.library;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.object.LAnimaObject;

/**
 * Created by LiuJ on 2016/8/31.
 * 动画框架的调用入口
 */
public class LAnimation {
    /**
     * 全局对象缓存站
     * 建议缓存动画相关的对象
     * 当然,可以缓存其他的东西,只要你高兴就好了
     */
    private static HashMap<String,Object> map = new HashMap<>();

    /**
     * 存入动画相关的对象
     * 当然,你也可以存入其他的东西
     * @param key 名字(注意,名字重复时,会覆盖掉原先的对象)
     * @param value 值
     */
    public static Object put(String key,Object value){
        return map.put(key,value);
    }

    /**
     * 取出缓存的数据
     * @param key 名字
     * @return 数据值
     */
    public static Object get(String key){
        return map.get(key);
    }

    /**
     * 获取一个新的属性动画任务对象
     * @param v 操作对象
     * @return 任务
     */
    public static LAnimaObject createLAnimaObject(View v){
        LAnimaObject<LDefaultBean,View> lAnimaObject = new LAnimaObject<>();
        lAnimaObject.create(v);
        return lAnimaObject;
    }

}
