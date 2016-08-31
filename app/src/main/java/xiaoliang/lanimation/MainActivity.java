package xiaoliang.lanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xiaoliang.lanimation.View.CurveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CurveView(this));
    }
}
