package xiaoliang.library.object;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.ArrayList;

import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.LAnimaBean;

/**
 * Created by LiuJ on 2016/8/31.
 * 动画任务对象
 */
public class LAnimaObject implements LAnima {

    private ArrayList<LAnimaBean> animaBeens;
    private View view;
    private TypeEvaluator<LAnimaBean> evaluator;
    @Override
    public void start(){
//        ObjectAnimator objectAnimator = ObjectAnimator.ofObject("",);
//        view.setAnimation();
    }

    public LAnimaObject() {
        animaBeens = new ArrayList<>();
    }

    /**
     * 添加动作,直接添加动作对象
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param bean
     */
    @Override
    public void add(LAnimaBean bean){
        animaBeens.add(bean);
    }
    /**
     * 添加动作,使用动作构造器
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param builder
     */
    public void add(BeanBuilder builder){
        animaBeens.add(builder.getBean());
    }

}
