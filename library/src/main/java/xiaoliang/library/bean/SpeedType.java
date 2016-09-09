package xiaoliang.library.bean;

/**
 * Created by liuj on 2016/9/9.
 * 动作的速度变化类型
 */
public enum SpeedType {
    /**
     * 匀速
     */
    UNIFORM(0x1),
    /**
     * 减速
     */
    SLOW_DOWN(0x2),
    /**
     * 加速
     */
    SPEED_UP(0x4),
    /**
     * 先加速再减速
     */
    UP_TO_DOWN(0x8),
    /**
     * 先减速再加速
     */
    DOWN_TO_UP(0x10);

    int value;
    SpeedType(int i){
        this.value = i;
    }
    /*可用16进制值
    1 2 4 8 10 20 40 80 100 200 400 800 1000 2000
    4000 8000 10000 20000 40000 80000 100000 200000
    400000 800000 1000000 2000000 4000000 8000000
    10000000 20000000 40000000 80000000 */
}
