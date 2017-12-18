package minayu.chromedino2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        l.addView(new ScrollView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        l.addView(new ScrollView(this));
    }
}
