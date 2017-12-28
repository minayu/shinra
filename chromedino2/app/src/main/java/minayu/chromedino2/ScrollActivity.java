package minayu.chromedino2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScrollActivity extends AppCompatActivity {
    public int backgroundWidth;
    public int backgroundHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        LinearLayout l = new LinearLayout(this);
        l.addView(new ScrollView(this));
        setContentView(l);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        l.addView(new ScrollView(this));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Here you can get the size!
        Resources res = this.getResources();
        Bitmap backGround = BitmapFactory.decodeResource(res, R.drawable.background);
        backgroundWidth = backGround.getWidth();
        backgroundHeight = backGround.getHeight();
    }
}
