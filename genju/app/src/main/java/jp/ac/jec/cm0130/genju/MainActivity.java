package jp.ac.jec.cm0130.genju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/*
    Home画面（メインメニュー
    幻獣、ステータス、行動ログなどの表示
*/

public class MainActivity extends AppCompatActivity {

    // ステータス表示
    //　画面遷移などした際も正しく表示されるように
    @Override
    protected void onResume() {
        super.onResume();
        StatusSQLiteOpenHelper sHelper = new StatusSQLiteOpenHelper(this);
        int exp = sHelper.getNowExp();
        TextView txtExp = (TextView)findViewById(R.id.txtExp);
        txtExp.setText(String.valueOf(exp));

        int level = sHelper.getNowLevel();
        TextView txtLevel = (TextView)findViewById(R.id.txtLevel);
        txtLevel.setText(String.valueOf(level));

        int stamina = sHelper.getNowStamina();
        TextView txtStamina = (TextView)findViewById(R.id.txtStamina);
        txtStamina.setText(String.valueOf(stamina));

        int attack = sHelper.getNowAttack();
        TextView txtAttack = (TextView)findViewById(R.id.txtAttack);
        txtAttack.setText(String.valueOf(attack));

        int defence = sHelper.getNowDefence();
        TextView txtDefence = (TextView)findViewById(R.id.txtDefence);
        txtDefence.setText(String.valueOf(defence));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 上部メニューボタンの設定
        
        // ダンジョン
        ImageButton ibtnDungeon = (ImageButton)findViewById(R.id.iButtonDungeon);
        ibtnDungeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DungeonActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        
        // アイテム
        ImageButton ibtnItem = (ImageButton)findViewById(R.id.iButtonItem);
        ibtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChattelActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // ショップ
        ImageButton ibtnShop = (ImageButton)findViewById(R.id.iButtonShop);
        ibtnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 討伐
        ImageButton ibtnEnemy = (ImageButton)findViewById(R.id.iButtonEnemy);
        ibtnEnemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnemyActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

}
