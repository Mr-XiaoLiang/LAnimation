package xiaoliang.library.algorithm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;

/**
 * Created by LiuJ on 2016/9/8.
 * 一个容器工具类
 * 用于简单的创建及管理容器或图层
 */
public class GroupUtil {

    private static HashMap<String,LinearLayout> linearLayoutHashMap = new HashMap<>();
    private static HashMap<String,FrameLayout> frameLayoutHashMap = new HashMap<>();
    private static HashMap<String,RelativeLayout> relativeLayoutHashMap = new HashMap<>();

    /**
     * 添加View到动画层
     * @param vg 容器
     * @param view View本身
     * @param location 坐标
     * @return 被添加的View
     */
    public static View addViewToAnimLayout(LinearLayout vg, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        cleanView(view);
        vg.addView(view);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    /**
     * 添加View到动画层
     * @param vg 容器
     * @param view View本身
     * @param location 坐标
     * @return 被添加的View
     */
    public static View addViewToAnimLayout(FrameLayout vg, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        cleanView(view);
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    /**
     * 添加View到动画层
     * @param vg 容器
     * @param view View本身
     * @param location 坐标
     * @return 被添加的View
     */
    public static View addViewToAnimLayout(RelativeLayout vg, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        cleanView(view);
        vg.addView(view);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    /**
     * 清理干净一个View
     * 从一个从属关系中剔除出来
     * @param view 待清理的View
     */
    public static void cleanView(View view){
        if(view.getParent()!=null){
            //如果View已经有了归宿,则把他从原有归属中剔除,然后加入现有归属
            view.getParent().recomputeViewAttributes(view);
        }
    }

    /**
     * 获取一个独立动画层的LinearLayout
     * 这个方法是同步的,保证了对象的唯一性
     * 并且使动画层可以重复利用
     * 获取对象的key是Activity的句柄
     * @param activity 页面对象
     * @return 容器
     */
    public static synchronized LinearLayout getAnimLinearLayout(Activity activity){
        if(linearLayoutHashMap.get(activity.toString())==null){
            linearLayoutHashMap.put(activity.toString(),createAnimLinearLayout(activity));
        }
        return linearLayoutHashMap.get(activity.toString());
    }

    /**
     * 获取一个独立动画层的RelativeLayout
     * 这个方法是同步的,保证了对象的唯一性
     * 并且使动画层可以重复利用
     * 获取对象的key是Activity的句柄
     * @param activity 页面对象
     * @return 容器
     */
    public static synchronized FrameLayout getAnimFrameLayout(Activity activity){
        if(frameLayoutHashMap.get(activity.toString())==null){
            frameLayoutHashMap.put(activity.toString(),createAnimFrameLayout(activity));
        }
        return frameLayoutHashMap.get(activity.toString());
    }

    /**
     * 获取一个独立动画层的RelativeLayout
     * 这个方法是同步的,保证了对象的唯一性
     * 并且使动画层可以重复利用
     * 获取对象的key是Activity的句柄
     * @param activity 页面对象
     * @return 容器
     */
    public static synchronized RelativeLayout getAnimRelativeLayout(Activity activity){
        if(relativeLayoutHashMap.get(activity.toString())==null){
            relativeLayoutHashMap.put(activity.toString(),createAnimRelativeLayout(activity));
        }
        return relativeLayoutHashMap.get(activity.toString());
    }

    /**
     * 屏幕上创建一个透明的动画层
     * 本方法创建的是一个新的LinearLayout
     * @param activity 页面对象
     * @return 容器
     */
    public static LinearLayout createAnimLinearLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    /**
     * 屏幕上创建一个透明的动画层
     * 本方法创建的是一个新的FrameLayout
     * @param activity 页面对象
     * @return 容器
     */
    public static FrameLayout createAnimFrameLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(activity);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    /**
     * 屏幕上创建一个透明的动画层
     * 本方法创建的是一个新的RelativeLayout
     * @param activity 页面对象
     * @return 容器
     */
    public static RelativeLayout createAnimRelativeLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        RelativeLayout animLayout = new RelativeLayout(activity);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

}
