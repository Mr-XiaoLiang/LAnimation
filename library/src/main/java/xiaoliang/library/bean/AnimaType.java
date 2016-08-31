package xiaoliang.library.bean;

/**
 * Created by LiuJ on 2016/8/31.
 * 动画的类型
 * 支持按位或,使一个Bean支持多种类型
 */
public enum AnimaType {
    /**
     * 线性位移
     */
    LINE(0x1),
    /**
     * 曲线位移
     */
    CURVE(0x2),
    /**
     * 直接位移
     */
    MOVE(0x4);
    int value;
    AnimaType(int i){
        this.value = i;
    }
    /*可用16进制值
    1 2 4 8 10 20 40 80 100 200 400 800 1000 2000
    4000 8000 10000 20000 40000 80000 100000 200000
    400000 800000 1000000 2000000 4000000 8000000
    10000000 20000000 40000000 80000000 */
}
