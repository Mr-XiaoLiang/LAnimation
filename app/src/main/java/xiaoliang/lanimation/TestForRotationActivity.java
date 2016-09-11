package xiaoliang.lanimation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xiaoliang.library.LAnimation;
import xiaoliang.library.bean.BeanBuilder;
import xiaoliang.library.bean.DefaultBeanBuilder;
import xiaoliang.library.bean.LAnimaBean;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.bean.RepeatType;
import xiaoliang.library.bean.SpeedType;
import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.listener.LProcessListenerInterface;
import xiaoliang.library.listener.LProgressListener;
import xiaoliang.library.object.LAnima;
import xiaoliang.library.object.LAnimaObject;
import xiaoliang.library.practitioners.LPractitioners;

/**
 * Created by LiuJ on 2016/9/11.
 * 用来做旋转测试的的小demo
 */
public class TestForRotationActivity extends AppCompatActivity implements View.OnClickListener,LProcessListenerInterface<LAnimaObject>,LProgressListener {

    private int index = 0;

    private View rotateView;

    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_rotation);
        rotateView = findViewById(R.id.rotate_view);
        msg = (TextView) findViewById(R.id.rotate_msg);
        msg.setMovementMethod(ScrollingMovementMethod.getInstance());
        rotateView.setRotationY(rotateView.getHeight()/2);
        rotateView.setRotationX(rotateView.getWidth()/2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotate_btn:
                flip();
                break;
            case R.id.rotate_btn2://顺时针,减速
                clockwise();
                break;
            case R.id.rotate_btn3://逆时针加速
                counterclockwise();
                break;
        }
        index++;
    }

    private void flip(){
        LAnimaObject<LDefaultBean,View> object = (LAnimaObject<LDefaultBean,View>) LAnimation.get("flip");
        if(object==null){
            object = new LAnimaObject<>();
            object.create(rotateView);
            object.setProcessListener(this);
            object.setDuration(1000);
            object.name = "翻面";
            object.setRepeatType(RepeatType.INFINITE_REVERSE);
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setWhat(11);
                    build.setProgressListener(TestForRotationActivity.this);
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setWhat(12);
                    build.setWidth(0);
                    build.setProgressListener(TestForRotationActivity.this);
                    build.setSpeedType(SpeedType.SPEED_UP);
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setWhat(11);
                    build.setProgressListener(TestForRotationActivity.this);
                    build.setSpeedType(SpeedType.SLOW_DOWN);
                }
            });
            LAnimation.put("flip",object);
        }
        if(object.isRunning()){
            object.cancel();
            Log.d("test","按下停止按钮");
        }else{
            object.start();
            Log.d("test","按下启动按钮");
        }
    }

    /**
     * 逆时针
     */
    private void counterclockwise(){
        LAnimaObject<LDefaultBean,View> object = (LAnimaObject<LDefaultBean,View>) LAnimation.get("counterclockwise");
        if(object==null){
            object = new LAnimaObject<>();
            object.create(rotateView);
            object.setProcessListener(this);
            object.setDuration(1000);
            object.name = "逆时针";
//            object.setRepeatType(RepeatType.INFINITE_REVERSE);
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(0);
                    build.setWhat(1);
                    build.setProgressListener(TestForRotationActivity.this);
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(-360)
                            .setSpeedType(SpeedType.SPEED_UP);
                    build.setWhat(2);
                    build.setProgressListener(TestForRotationActivity.this);
                }
            });
            LAnimation.put("counterclockwise",object);
        }
        if(object.isRunning()){
            object.cancel();
        }else{
            object.start();
        }
    }

    /**
     * 顺时针
     */
    private void clockwise(){
        LAnimaObject<LDefaultBean,View> object = (LAnimaObject<LDefaultBean,View>) LAnimation.get("clockwise");
        if(object==null){
            object = new LAnimaObject<>();
            object.create(rotateView);
            object.setDuration(1000);
            object.setProcessListener(this);
            object.name = "顺时针";
//            object.setRepeatType(RepeatType.INFINITE_RESTART);
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(0);
                    build.setWhat(3);
                    build.setProgressListener(TestForRotationActivity.this);
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(360)
                            .setSpeedType(SpeedType.SLOW_DOWN);
                    build.setWhat(4);
                    build.setProgressListener(TestForRotationActivity.this);
                }
            });
            LAnimation.put("clockwise",object);
        }
        if(object.isRunning()){
            object.cancel();
        }else{
            object.start();
        }
    }


    @Override
    public void onPrepare(String name, int what, LAnimaObject obj) {
        refreshLogView(name+"-->开始准备\n");
        Log.d(name,"-->开始准备");
    }

    @Override
    public void onStart(String name, int what, LAnimaObject obj) {
        refreshLogView(name+"-->开始执行\n");
        Log.d(name,"-->开始执行");
    }

    @Override
    public void onRepeat(String name, int what, LAnimaObject obj) {
        refreshLogView(name+"-->再次重复执行\n");
        Log.d(name,"-->再次重复执行");
    }

    @Override
    public void onCancel(String name, int what, LAnimaObject obj) {
        refreshLogView(name+"-->任务取消\n");
        Log.d(name,"-->任务取消");
    }

    @Override
    public void onError(String name, int what, LAnimaObject obj,AnimationException e) {
        refreshLogView(name+"-->发生异常:"+e.getMessage()+"\n");
        Log.d(name,"-->发生异常:"+e.getMessage());
    }

    @Override
    public void onConclude(String name, int what, LAnimaObject obj) {
        refreshLogView(name+"-->任务结束\n");
        Log.d(name,"-->任务结束\n");
    }
    int fIndex = 0;
    @Override
    public void onProgressChange(float p, int index) {
        if(fIndex!=index&&index>10){
            fIndex = index;
            switch (fIndex%2){
                case 0:
                    rotateView.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    rotateView.setBackgroundColor(Color.GREEN);
                    break;
            }
        }
//        refreshLogView(index+"-->"+p+"\n");
//        Log.d(index+"","-->"+p);
    }

    void refreshLogView(String msgStr){
        msg.append(msgStr);
        int offset=msg.getLineCount()*msg.getLineHeight();
        if(offset>msg.getHeight()){
            msg.scrollTo(0,offset-msg.getHeight());
        }
     }

}
