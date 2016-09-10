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
import xiaoliang.library.bean.SpeedType;
import xiaoliang.library.exception.AnimationException;
import xiaoliang.library.listener.LProcessListenerInterface;
import xiaoliang.library.object.LAnima;
import xiaoliang.library.object.LAnimaObject;
import xiaoliang.library.practitioners.LPractitioners;

public class TestForRotationActivity extends AppCompatActivity implements View.OnClickListener,LProcessListenerInterface<LAnimaObject> {

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
                switch (index%2){
                    case 0:
                        rotateView.setRotation(30);
                        ((Button)v).setText("手动旋转45度");
                        break;
                    case 1:
                        rotateView.setRotation(45);
                        ((Button)v).setText("手动旋转30度");
                        break;
                }
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

    /**
     * 逆时针
     */
    private void counterclockwise(){
        LAnimaObject object = (LAnimaObject) LAnimation.get("counterclockwise");
        if(object==null){
            Log.d("counterclockwise","object==null");
            object = LAnimaObject.create(rotateView);
            object.setProcessListener(this);
            object.setDuration(1000);
            object.name = "逆时针";
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(-360)
                    .setColor(Color.RED);
                    build();
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(0)
                            .setColor(Color.GREEN)
                            .setSpeedType(SpeedType.SPEED_UP);

                    build();
                }
            });
            LAnimation.put("counterclockwise",object);
        }
        object.start();
        Log.d("counterclockwise","object.start();");
    }

    /**
     * 顺时针
     */
    private void clockwise(){
        LAnimaObject object = (LAnimaObject) LAnimation.get("clockwise");
        if(object==null){
            Log.d("clockwise","object==null");
            object = LAnimaObject.create(rotateView);
            object.setDuration(1000);
            object.setProcessListener(this);
            object.name = "顺时针";
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(0)
                            .setColor(Color.GREEN);
                    build();
                }
            });
            object.add(new DefaultBeanBuilder() {
                @Override
                public void make(LDefaultBean.Build build) {
                    build.setRotate(360)
                            .setColor(Color.RED)
                            .setSpeedType(SpeedType.SLOW_DOWN);

                    build();
                }
            });
            LAnimation.put("clockwise",object);
        }
        object.start();
        Log.d("clockwise","object.start();");
    }


    @Override
    public void onPrepare(String name, int what, LAnimaObject obj) {
        msg.append(name+"-->开始准备\n");
        Log.d(name,"-->开始准备");
    }

    @Override
    public void onStart(String name, int what, LAnimaObject obj) {
        msg.append(name+"-->开始执行\n");
        Log.d(name,"-->开始执行");
    }

    @Override
    public void onRepeat(String name, int what, LAnimaObject obj) {
        msg.append(name+"-->再次重复执行\n");
        Log.d(name,"-->再次重复执行");
    }

    @Override
    public void onCancel(String name, int what, LAnimaObject obj) {
        msg.append(name+"-->任务取消\n");
        Log.d(name,"-->任务取消");
    }

    @Override
    public void onError(String name, int what, LAnimaObject obj,AnimationException e) {
        msg.append(name+"-->发生异常:"+e.getMessage()+"\n");
        Log.d(name,"-->发生异常:"+e.getMessage());
    }

    @Override
    public void onConclude(String name, int what, LAnimaObject obj) {
        msg.append(name+"-->任务结束\n");
        Log.d(name,"-->任务结束\n");
    }
}
