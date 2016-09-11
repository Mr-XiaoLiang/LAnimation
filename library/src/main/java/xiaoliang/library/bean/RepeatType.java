package xiaoliang.library.bean;

/**
 * Created by liuj on 2016/9/11.
 * 动画重复方式，
 */
public enum RepeatType {
    /**
     * 重复执行
     */
    RESTART(0x1),
    /**
     * 倒转顺序执行
     */
    REVERSE(0x2),
    /**
     * 无限此顺序次数
     */
    INFINITE_RESTART(0x4),
    /**
     * 无限此倒序次数
     */
    INFINITE_REVERSE(0x8);
    int value;
    RepeatType(int i){
        this.value = i;
    }
    /*可用16进制值
    1 2 4 8 10 20 40 80 100 200 400 800 1000 2000
    4000 8000 10000 20000 40000 80000 100000 200000
    400000 800000 1000000 2000000 4000000 8000000
    10000000 20000000 40000000 80000000 */
}
