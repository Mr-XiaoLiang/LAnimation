package xiaoliang.library.bean;

import android.graphics.Color;

/**
 * Created by LiuJ on 2016/8/31.
 * 缺省默认的关键帧
 */
public class LDefaultBean implements LAnimaBean {


    public static class Build {
        private AnimaType animaType = AnimaType.LINE;//动画类型
        private boolean beyond = false;// 是否忽略布局限制
        private long animaTime = 300;//动画执行时间
        private int x = 0;// 横向坐标增量
        private int y = 0;//纵向坐标增量
        private int width = 0;//宽度增量
        private int height = 0;// 高度增量
        private int color = Color.WHITE;// 目标颜色
        private float rotate= 0;//旋转角度
        private String name = "";// 关键帧名称
        private int what = 0;//关键帧标签
        private Object tag = null;//标签
    }
}
