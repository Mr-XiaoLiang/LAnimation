package xiaoliang.library.bean;

import android.graphics.Color;

/**
 * Created by LiuJ on 2016/8/31.
 * 缺省默认的关键帧
 */
public class LDefaultBean extends LAnimaBean {

    public final AnimaType animaType;//动画类型
    public final SpeedType speedType;//速度变化类型
    public final boolean beyond;// 是否忽略布局限制
    public final long animaTime;//动画执行时间
    public final int x;// 横向坐标增量
    public final int y;//纵向坐标增量
    public final float width;//宽度比例
    public final float height;// 高度增量
    public final int color;// 目标颜色
    public final float rotate;//旋转角度
    public final String name;// 关键帧名称
    public final int what;//关键帧标签
    public Object tag;//标签
    public final int x1;// 横向坐标辅助增量
    public final int y1;//纵向坐标辅助增量
    public final int x2;// 横向坐标辅助增量
    public final int y2;//纵向坐标辅助增量

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
        this.x1 = build.x1;
        this.y1 = build.y1;
        this.x2 = build.x2;
        this.y2 = build.y2;
        this.width = build.width;
        this.speedType = build.speedType;
    }

    public int getAnimaType() {
        return animaType.value;
    }

    public int getSpeedType() {
        return speedType.value;
    }

    public static class Build {
        private AnimaType animaType = AnimaType.LINE;//动画类型
        private SpeedType speedType = SpeedType.UNIFORM;//速度变化类型
        private boolean beyond = false;// 是否忽略布局限制,本参数暂时不可用
        private long animaTime = 300;//动画执行时间,本方法暂时不可用
        private int x = 0;// 横向坐标增量
        private int y = 0;//纵向坐标增量
        private int x1 = 0;// 横向坐标辅助增量
        private int y1 = 0;//纵向坐标辅助增量
        private int x2 = 0;// 横向坐标辅助增量
        private int y2 = 0;//纵向坐标辅助增量
        private float width = 0;//宽度比例
        private float height = 0;// 高度比例
        private int color = Color.WHITE;// 目标颜色
        private float rotate= 0;//旋转角度
        private String name = getClass().toString();// 关键帧名称
        private int what = 0;//关键帧标签
        private Object tag = null;//标签

        public Build setAnimaType(AnimaType animaType) {
            this.animaType = animaType;
            return this;
        }

        /**
         * 本方法暂时不可用
         * @param beyond
         * @return
         */
        public Build setBeyond(boolean beyond) {
            this.beyond = beyond;
            return this;
        }

        /**
         * 本方法暂时不可用
         * @param animaTime 动画执行时间
         * @return 当前
         */
        public Build setAnimaTime(long animaTime) {
            this.animaTime = animaTime;
            return this;
        }

        public Build moveTo(int x,int y) {
            this.x = x;this.y = y;
            setAnimaType(AnimaType.MOVE);
            return this;
        }

        public Build lineTo(int x,int y){
            this.x = x;this.y = y;
            setAnimaType(AnimaType.LINE);
            return this;
        }

        /**
         * 二次曲线连接
         * @param x 终点坐标X(相对起点坐标)
         * @param y 终点坐标Y(相对起点坐标)
         * @param x1 起点坐标辅助点X(相对起点坐标)
         * @param y1 起点坐标辅助点Y(相对起点坐标)
         * @param x2 终点坐标辅助点X(相对于终点坐标)
         * @param y2 终点坐标辅助点Y(相对于终点坐标)
         * @return
         */
        public Build curveTo(int x,int y,int x1,int y1,int x2,int y2){
            this.x = x;this.y = y;
            this.x1 = x1;this.y1 = y1;
            this.x2 = x2;this.y2 = y2;
            setAnimaType(AnimaType.CURVE);
            return this;
        }

        public Build setWidth(float width) {
            this.width = width;
            return this;
        }

        public Build setHeight(float height) {
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

        public SpeedType getSpeedType() {
            return speedType;
        }

        public Build setSpeedType(SpeedType speedType) {
            this.speedType = speedType;
            return this;
        }
    }

    public LDefaultBean(){
        this(new Build());
    }

}
