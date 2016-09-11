package xiaoliang.library.object;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.ArrayList;

import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.DefaultBeanBuilder;
import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.bean.RepeatType;
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
public class LAnimaObject<T extends LAnimaBean,V> extends LAnima<T> {

    private ArrayList<T> animaBens;
    private V view;
    private LEvaluator evaluator;
    private LPractitioners<T,V> practitioners;
    private long duration = 300;
    private LProcessListenerInterface processListener;
    private ObjectAnimator objectAnimator;
    private boolean isRunning = false;
    private RepeatType repeatType = RepeatType.RESTART;
    private int repeatCount = 0;

    @Override
    public void start(){
        if(isRunning){
            Log.d("LAnimaObject","isRunning");
            return;
        }
        Log.d("LAnimaObject","onStart");
        isRunning = true;
        try{
            if(objectAnimator==null){
                if (processListener!=null){
                    processListener.onPrepare(name,what,this);
                }
                if(animaBens.size()<2){//如果小于2个坐标,则添加一个当前坐标的点
                    if (processListener!=null)
                        processListener.onError(name,what,LAnimaObject.this,new AnimationException("AnimaBeans 的数量必须>1 .",LAnimaObject.class));
                    else
                        throw new AnimationException("AnimaBeans 的数量必须>1 .",LAnimaObject.class);
                }
                if(practitioners==null){
                    if (processListener!=null)
                        processListener.onError(name,what,LAnimaObject.this,new AnimationException("LPractitioners is null.(执行者不存在,请为动画任务分配执行者)",LAnimaObject.class));
                    else
                        throw new AnimationException("LPractitioners is null.(执行者不存在,请为动画任务分配执行者)",LAnimaObject.class);
                }
                objectAnimator = ObjectAnimator.ofObject(practitioners,practitioners.getPropertyName(),evaluator,animaBens.toArray());
                objectAnimator.setDuration(duration);
                objectAnimator.addListener(new LAnimaListener());
                setRepeatType();
            }
            objectAnimator.start();
            Log.d("LAnimaObject","开始动画");
        }catch (Exception e){
            isRunning = false;
            if (processListener!=null){
                processListener.onError(name,what,LAnimaObject.this,new AnimationException("LAnimaObject--->"+e.getMessage()));
            }else{
                throw new AnimationException("LAnimaObject--->"+e.getMessage());
            }
        }
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public RepeatType getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(RepeatType repeatType) {
        this.repeatType = repeatType;
        setRepeatType();
    }

    private void setRepeatType(){
        if(objectAnimator!=null){
            switch (repeatType){
                case RESTART://顺序重复
                    objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                    objectAnimator.setRepeatCount(repeatCount);
                    break;
                case REVERSE://倒序重复
                    objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    objectAnimator.setRepeatCount(repeatCount);
                    break;
                case INFINITE_RESTART:
                    objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    repeatCount = -1;
                    break;
                case INFINITE_REVERSE:
                    objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    repeatCount = -1;
                    break;
            }
        }
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public LAnimaObject() {
        animaBens = new ArrayList<>();
    }

    public Object getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
        if(practitioners!=null)
            practitioners.setView(view);
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

    public void create(V view){
        setEvaluator(new LDefaultEvaluator());
        setPractitioners(new LDefaultPractitioners());
        setView(view);
    }

    /**
     * 添加动作,直接添加动作对象
     * 如果只有一个动作,那么View的当前状态为起始状态,添加动作为结束动作
     * 如果只有两个动作,那么第一个为开始动作,第二个为结束动作
     * 如果有2个以上,那么第一个为开始动作,经过中间的动作,以最后一个动作为结束
     * @param bean
     */
    @Override
    public void add(T bean){
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
    public void add(BeanBuilder<T> builder){
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

    /**
     * 停止动画，仅仅停止已运行的状态
     */
    @Override
    public void cancel() {
        if(objectAnimator!=null){
            objectAnimator.cancel();
            objectAnimator.end();
            Log.d("LAnimaObject","停止动画");
        }
        isRunning = false;
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
