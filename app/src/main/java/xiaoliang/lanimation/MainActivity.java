package xiaoliang.lanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.SimpleAdapter;

import xiaoliang.lanimation.View.CurveView;
import xiaoliang.library.bean.AnimaType;
import xiaoliang.library.bean.DefaultBeanBuilder;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.bean.SpeedType;
import xiaoliang.library.object.LAnima;
import xiaoliang.library.object.LAnimaObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new CurveView(this));
    }

    private void initAnimation(){
        LAnimaObject lAnimaObject = LAnimaObject.create(new View(this));
        lAnimaObject.add(new DefaultBeanBuilder() {
            @Override
            public void make(LDefaultBean.Build build) {
                build.moveTo(0,0);
            }
        });
        lAnimaObject.add(new DefaultBeanBuilder() {
            @Override
            public void make(LDefaultBean.Build build) {
                build.lineTo(10,100);
                build.setSpeedType(SpeedType.UNIFORM);
            }
        });
        lAnimaObject.add(new DefaultBeanBuilder() {
            @Override
            public void make(LDefaultBean.Build build) {
                build.curveTo(-100,100,10,35,12,54);
                build.setSpeedType(SpeedType.SPEED_UP);
            }
        });
        lAnimaObject.start();
    }

}
