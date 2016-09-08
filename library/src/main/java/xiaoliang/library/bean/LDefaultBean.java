package xiaoliang.library.bean;

import android.graphics.Color;

/**
 * Created by LiuJ on 2016/8/31.
 * 缺省默认的关键帧
 */
public class LDefaultBean extends LAnimaBean {

    public final AnimaType animaType;//动画类型
    public final boolean beyond;// 是否忽略布局限制
    public final long animaTime;//动画执行时间
    public final int x;// 横向坐标增量
    public final int y;//纵向坐标增量
    public final int width;//宽度增量
    public final int height;// 高度增量
    public final int color;// 目标颜色
    public final float rotate;//旋转角度
    public final String name;// 关键帧名称
    public final int what;//关键帧标签
    public final Object tag;//标签

    public LDefaultBean(Build build) {
        this.animaTime = build.animaTime;
        this.animaType = build.animaType;
        this.beyond = build.beyond;
        this.color = build.color;
        this.height = build.height;
        this.name = build.name;
        this.rotate = build.rotate;
        this.tag = build.tag;
        this.what = build.what;
        this.x = build.x;
        this.y = build.y;
        this.width = build.width;
    }

    public int getAnimaType() {
        return animaType.value;
    }

    public boolean isBeyond() {
        return beyond;
    }

    public long getAnimaTime() {
        return animaTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColor() {
        return color;
    }

    public float getRotate() {
        return rotate;
    }

    public String getName() {
        return name;
    }

    public int getWhat() {
        return what;
    }

    public Object getTag() {
        return tag;
    }

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
        private String name = getClass().toString();// 关键帧名称
        private int what = 0;//关键帧标签
        private Object tag = null;//标签

        public Build setAnimaType(AnimaType animaType) {
            this.animaType = animaType;
            return this;
        }

        public Build setBeyond(boolean beyond) {
            this.beyond = beyond;
            return this;
        }

        public Build setAnimaTime(long animaTime) {
            this.animaTime = animaTime;
            return this;
        }

        public Build setX(int x) {
            this.x = x;
            return this;
        }

        public Build setY(int y) {
            this.y = y;
            return this;
        }

        public Build setWidth(int width) {
            this.width = width;
            return this;
        }

        public Build setHeight(int height) {
            this.height = height;
            return this;
        }

        public Build setColor(int color) {
            this.color = color;
            return this;
        }

        public Build setRotate(float rotate) {
            this.rotate = rotate;
            return this;
        }

        public Build setName(String name) {
            this.name = name;
            return this;
        }

        public Build setWhat(int what) {
            this.what = what;
            return this;
        }

        public Build setTag(Object tag) {
            this.tag = tag;
            return this;
        }
    }
}
