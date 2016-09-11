package xiaoliang.lanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import xiaoliang.lanimation.View.CurveView;
import xiaoliang.library.bean.AnimaType;
import xiaoliang.library.bean.DefaultBeanBuilder;
import xiaoliang.library.bean.LDefaultBean;
import xiaoliang.library.bean.SpeedType;
import xiaoliang.library.object.LAnima;
import xiaoliang.library.object.LAnimaObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initAnimation(){
        LAnimaObject lAnimaObject = new LAnimaObject();
        lAnimaObject.create(new View(this));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_for_translation:
                startActivity(new Intent(this,TestForTranslation.class));
                break;
            case R.id.test_for_rotate:
                startActivity(new Intent(this,TestForRotationActivity.class));
                break;
        }
    }
}
