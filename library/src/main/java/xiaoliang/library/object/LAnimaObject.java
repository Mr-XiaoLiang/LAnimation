package xiaoliang.library.object;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.evaluator.LDefaultEvaluator;
import xiaoliang.library.evaluator.LEvaluator;
import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.listener.LProcessListener;
import xiaoliang.library.listener.LProcessListenerInterface;
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
    private LProcessListenerInterface processListener;
    private ObjectAnimator objectAnimator;
    private boolean isRunning = false;

    @Override
    public void start(){
        if(isRunning){
            isRunning = true;
            return;
        }
//        try{
            if(objectAnimator==null){
                if (processListener!=null){
                    processListener.onPrepare(name,what,this);
                }
                if(animaBens.size()<2)//如果小于2个坐标,则添加一个当前坐标的点
                    animaBens.add(0,new LAnimaBean());
                if(practitioners==null){
                    if (processListener!=null)
                        processListener.onError(name,what,LAnimaObject.this,new AnimationException("LPractitioners is null.(执行者不存在,请为动画任务分配执行者)",LAnimaObject.class));
                    else
                        throw new AnimationException("LPractitioners is null.(执行者不存在,请为动画任务分配执行者)",LAnimaObject.class);
                }
                Log.d("LAnimaObject","准备创建ObjectAnimator");
                objectAnimator = ObjectAnimator.ofObject(LAnimaObject.this,"animation",evaluator,animaBens.toArray());
                Log.d("LAnimaObject","创建ObjectAnimator完毕");
                objectAnimator.setDuration(duration);
                Log.d("LAnimaObject","设置ObjectAnimator时间");
                objectAnimator.addListener(new LAnimaListener());
                Log.d("LAnimaObject","设置ObjectAnimator监听器");
            }
            objectAnimator.start();
            Log.d("LAnimaObject","ObjectAnimator开始执行");
//        }catch (Exception e){
//            if (processListener!=null){
//                processListener.onError(name,what,LAnimaObject.this,new AnimationException("LAnimaObject--->"+e.getMessage()));
//            }else{
//                throw new AnimationException("LAnimaObject--->"+e.getMessage());
//            }
//        }
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
        add(builder.getBean());
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
            isRunning = false;
            if(processListener!=null)
                processListener.onConclude(name,what,LAnimaObject.this);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            isRunning = false;
            if(processListener!=null)
                processListener.onCancel(name,what,LAnimaObject.this);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            if(processListener!=null)
                processListener.onRepeat(name,what,LAnimaObject.this);
        }
    }

    public LProcessListenerInterface getProcessListener() {
        return processListener;
    }

    public void setProcessListener(LProcessListenerInterface processListener) {
        this.processListener = processListener;
    }
}
