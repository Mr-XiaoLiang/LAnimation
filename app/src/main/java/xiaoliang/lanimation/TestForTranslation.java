package xiaoliang.lanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by liuj on 2016/9/10.
 * 本页面是对View的setTranslationX方法的测试

 */
public class TestForTranslation extends AppCompatActivity {

    private View view;
    private TextView button;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_for_translation);
        view = findViewById(R.id.view);
        button = (TextView) findViewById(R.id.btn);
        button.setText("view.setTranslationX(500)");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index%2){
                    case 0:
                        view.setTranslationX(200);
                        button.setText("view.setTranslationX(500)");
                        break;
                    case 1:
                        view.setTranslationX(500);
                        button.setText("view.setTranslationX(200)");
                        break;
                }
                index++;
            }
        });
    }

}