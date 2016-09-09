package xiaoliang.library.object;

import android.animation.Animator;
import android.animation.ObjectAnimator;

import java.util.ArrayList;

import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.evaluator.LDefaultEvaluator;
import xiaoliang.library.evaluator.LEvaluator;
import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.listener.LProcessListener;
import xiaoliang.library.practitioners.LDefaultPractitioners;
import xiaoliang.library.practitioners.LPractitioners;

/**
 * Created by LiuJ on 2016/8/31.
 * 动画任务对象
 * 这里负责管理,调度动画的组件
 * 将动作,计算器,被执行者,执行者,监听器,动画处理器进行调度
 */
public class LAnimaObject extends LAnima {

    private ArrayList<LAnimaBean> animaBens;
    private Object view;
    private LEvaluator evaluator;
    private LPractitioners practitioners;
    private long duration = 300;
    private LProcessListener processListener;
    private ObjectAnimator objectAnimator;

    @Override
    public void start(){
        try{
            if(objectAnimator==null){
                if (processListener!=null){
                    processListener.onPrepare(name,what,this);
                }
                objectAnimator = ObjectAnimator.ofObject(this,"animation",evaluator,animaBens);
                objectAnimator.setDuration(duration);
                objectAnimator.addListener(new LAnimaListener());
            }
            objectAnimator.start();
        }catch (Exception e){
            if (processListener!=null){
                processListener.onError(new AnimationException(getClass().getName()+"--->"+e.getMessage()));
            }
        }
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setAnimation(LAnimaBean bean){
        practitioners.perform(view,bean);
    }

    public LAnimaObject() {
        animaBens = new ArrayList<>();
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public LEvaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(LEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public LPractitioners getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(LPractitioners practitioners) {
        this.practitioners = practitioners;
    }

    public static LAnimaObject create(Object view){
        LAnimaObject lAnimaObject = new LAnimaObject();
        lAnimaObject.setEvaluator(new LDefaultEvaluator());
        lAnimaObject.setView(view);
        lAnimaObject.setPractitioners(new LDefaultPractitioners());
        return lAnimaObject;
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
        animaBens.add(bean);
    }
    /**
     * 添加动作,使用动作构造器
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param builder
     */
    @Override
    public void add(BeanBuilder builder){
        animaBens.add(builder.getBean());
    }

    /**
     * 添加一个进度监听器
     * @param processListener
     */
    @Override
    public void addListener(LProcessListener processListener) {
        this.processListener = processListener;
    }

    private class LAnimaListener implements Animator.AnimatorListener{

        @Override
        public void onAnimationStart(Animator animation) {
            if(processListener!=null)
                processListener.onStart(name,what,LAnimaObject.this);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(processListener!=null)
                processListener.onConclude(name,what,LAnimaObject.this);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            if(processListener!=null)
                processListener.onCancel(name,what,LAnimaObject.this);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            if(processListener!=null)
                processListener.onRepeat(name,what,LAnimaObject.this);
        }
    }

}
