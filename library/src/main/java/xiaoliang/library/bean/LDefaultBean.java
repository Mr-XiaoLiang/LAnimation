package xiaoliang.library.bean;

import android.graphics.Color;

/**
 * Created by LiuJ on 2016/8/31.
 * 缺省默认的关键帧实现类
 */
public class LDefaultBean implements LAnimaBean {

    public final int animaType;//动画类型
    public final boolean beyond;// 是否忽略布局限制
    public final long animaTime;//动画执行时间
    public final int x0;// 横向坐标增量
    public final int y0;//纵向坐标增量
    public final int x1;// 横向坐标曲线辅助点
    public final int y1;//纵向坐标曲线辅助点
    public final int x2;// 横向坐标曲线辅助点
    public final int y2;//纵向坐标曲线辅助点
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
        this.width = build.width;
        this.x0 = build.x0;
        this.x1 = build.x1;
        this.x2 = build.x2;
        this.y0 = build.y0;
        this.y1 = build.y1;
        this.y2 = build.y2;
    }

    public static class Build {
        private int animaType = AnimaType.LINE.value;//动画类型
        private boolean beyond = false;// 是否忽略布局限制
        private long animaTime = 300;//动画执行时间
        private int x0 = 0;// 横向坐标增量
        private int y0 = 0;//纵向坐标增量
        private int x1 = 0;// 横向坐标曲线辅助点
        private int y1 = 0;//纵向坐标曲线辅助点
        private int x2 = 0;// 横向坐标曲线辅助点
        private int y2 = 0;//纵向坐标曲线辅助点
        private int width = 0;//宽度增量
        private int height = 0;// 高度增量
        private int color = Color.WHITE;// 目标颜色
        private float rotate= 0;//旋转角度
        private String name = "";// 关键帧名称
        private int what = 0;//关键帧标签
        private Object tag = null;//标签

        public Build addLine(int endEndpointX,int endEndpointY){
            this.x0 = endEndpointX;
            this.y0 = endEndpointY;
            setAnimaType(AnimaType.LINE);
            return this;
        }

        public Build addMove(int endEndpointX,int endEndpointY){
            this.x0 = endEndpointX;
            this.y0 = endEndpointY;
            setAnimaType(AnimaType.MOVE);
            return this;
        }

        public Build addCurve(int firstControlX,int firstControlY,int endControlX,int endControlY,int endEndpointX,int endEndpointY){
            this.x1 = firstControlX;
            this.x2 = endControlX;
            this.x0 = endEndpointX;
            this.y1 = firstControlY;
            this.y2 = endControlY;
            this.y0 = endEndpointY;
            setAnimaType(AnimaType.CURVE);
            return this;
        };

        public int getAnimaType() {
            return animaType;
        }

        public Build setAnimaType(AnimaType... animaType) {
            this.animaType = 0;
            for(AnimaType type : animaType){
                this.animaType |= type.value;
            }
            return this;
        }

        public boolean isBeyond() {
            return beyond;
        }

        public Build setBeyond(boolean beyond) {
            this.beyond = beyond;
            return this;
        }

        public long getAnimaTime() {
            return animaTime;
        }

        public Build setAnimaTime(long animaTime) {
            this.animaTime = animaTime;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public Build setWidth(int width) {
            this.width = width;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public Build setHeight(int height) {
            this.height = height;
            return this;
        }

        public float getRotate() {
            return rotate;
        }

        public Build setRotate(float rotate) {
            this.rotate = rotate;
            return this;
        }

        public String getName() {
            return name;
        }

        public Build setName(String name) {
            this.name = name;
            return this;
        }

        public int getWhat() {
            return what;
        }

        public Build setWhat(int what) {
            this.what = what;
            return this;
        }

        public Object getTag() {
            return tag;
        }

        public Build setTag(Object tag) {
            this.tag = tag;
            return this;
        }

        public int getColor() {
            return color;
        }

        public Build setColor(int color) {
            this.color = color;
            return this;
        }
    }
}
