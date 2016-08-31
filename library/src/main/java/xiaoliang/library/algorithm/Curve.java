package xiaoliang.library.algorithm;

/**
 * Created by LiuJ on 2016/8/31.
 * 曲线算法公式类
 */
public class Curve {
    /**
     * 三阶曲线算法
     * X轴与Y轴算法通用分别调用即可
     * @param t 进度区间(0-1)
     * @param firstEndpoint 起始端点
     * @param firstControl 起始端点的控制点
     * @param endControl 结束端点的控制点
     * @param endEndpoint 结束端点
     * @return
     */
    public static float thirdOrder(float t,int firstEndpoint,int firstControl,int endControl,int endEndpoint){
        float uT = (1-t);
        return firstEndpoint*uT*uT*uT
                +3*firstControl*t*uT*uT
                +3*endControl*t*t*uT
                +endEndpoint*t*t*t;
    }

    /**
     * 二阶曲线函数
     * @param t 进度区间(0-1)
     * @param firstEndpoint 起始端点
     * @param endControl 结束端点的控制点
     * @param endEndpoint 结束端点
     * @return
     */
    public static float secondOrder(float t,int firstEndpoint,int endControl,int endEndpoint){
        float uT = (1-t);
        return firstEndpoint*uT*uT
                +2*endControl*t*uT
                +endEndpoint*t*t;
    }

    /**
     * 线性函数
     * @param t 进度区间(0-1)
     * @param firstEndpoint 起点
     * @param endEndpoint 终点
     * @return
     */
    private static float line(float t,int firstEndpoint,int endEndpoint){
        return firstEndpoint+(endEndpoint-firstEndpoint)*t;
    }

}
