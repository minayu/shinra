package jp.ac.jec.cm0130.genju;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DungeonActivity extends AppCompatActivity {
    private ArrayList<Status> ary = new ArrayList<>();

    private int dNumber = 1;
//    private int nowLevel;
//    private int expGauge = nowLevel * 10;

    class CountDown extends CountDownTimer {
        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
             TextView txt = (TextView) findViewById(R.id.txtTimer);

             txt.setText("残り" + mm + ":" + ss);
        }

        @Override
        public void onFinish() {
            TextView txt = (TextView) findViewById(R.id.txtTimer);

            txt.setText("帰還しました！");

            ChattelSQLiteOpenHelper helper = new ChattelSQLiteOpenHelper(DungeonActivity.this);
            StatusSQLiteOpenHelper sHelper = new StatusSQLiteOpenHelper(DungeonActivity.this);

            int nowLevel = sHelper.getNowLevel();
            int expGauge = nowLevel * 10;


            if(dNumber == 1){
                Chattel getChattel = new Chattel(4, "ぼろい布", 2,1);
                helper.insertChattel(getChattel);

                Status getExp = new Status(0,0,0,0,0,10);

                sHelper.insertExp(getExp);
                int nowExp = sHelper.getNowExp();
                sHelper.updateNowExp(nowExp);   // 値を更新する


//                Log.d("exp", String.valueOf(nowExp));
            } else if (dNumber == 2) {
                Chattel getChattel = new Chattel(5, "金のかけら", 1,1);
                helper.insertChattel(getChattel);

                Status getExp = new Status(0,0,0,0,0,20);

                sHelper.insertExp(getExp);
                int nowExp = sHelper.getNowExp();
                sHelper.updateNowExp(nowExp);   // 値を更新する

            } else if (dNumber == 3) {
                Chattel getChattel = new Chattel(-1, "エメラルド", 1,1);
                helper.insertChattel(getChattel);

                Status getExp = new Status(0,0,0,0,0,30);

                sHelper.insertExp(getExp);
                int nowExp = sHelper.getNowExp();
                sHelper.updateNowExp(nowExp);   // 値を更新する

            }

            if(sHelper.getNowExp() >= expGauge){
                Log.d("exp", String.valueOf(sHelper.getNowExp()));

                int i = sHelper.getNowExp() - expGauge;
                Log.d("nowgauge", String.valueOf(expGauge));

                Status getExp = new Status(0,0,0,0,0,-sHelper.getNowExp()+i);
                // Expの加算
                sHelper.insertExp(getExp);
                int nowExp = sHelper.getNowExp();
                sHelper.updateNowExp(nowExp);   // 値を更新する
                Status updateStatus = new Status(0,1,10,5,2,0);
                sHelper.levelUp(updateStatus);
                Toast.makeText(DungeonActivity.this, "レベルアップしました！", Toast.LENGTH_SHORT).show();


            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        TextView txt = (TextView)findViewById(R.id.txtTimer);
        txt.setText("待機中");

        StatusSQLiteOpenHelper sHelper = new StatusSQLiteOpenHelper(DungeonActivity.this);
//        nowLevel = sHelper.getNowLevel();



        findViewById(R.id.ibtnDun01).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                new AlertDialog.Builder(DungeonActivity.this)
                        .setTitle("ダンジョン探索")
                        .setMessage("初心者の森に行きますか?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                CountDown countDown = new CountDown(3 * 60 * 1000, 100);
                                // OK button pressed
                                CountDown countDown = new CountDown(1 * 1000, 100);
                                countDown.start();

                                dNumber = 1;

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }

        });

        findViewById(R.id.ibtnDun02).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                new AlertDialog.Builder(DungeonActivity.this)
                        .setTitle("ダンジョン探索")
                        .setMessage("荒れ砂漠に行きますか?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CountDown countDown = new CountDown(5 * 1000, 100);
                                countDown.start();

                                dNumber = 2;

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }

        });

        findViewById(R.id.ibtnDun03).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                new AlertDialog.Builder(DungeonActivity.this)
                        .setTitle("ダンジョン探索")
                        .setMessage("南国の島に行きますか?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CountDown countDown = new CountDown(1 * 1000, 100);
                                countDown.start();

                                dNumber = 3;

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }

        });



        findViewById(R.id.iButtonHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          FLAG_ACTIVITY_CLEAR_TOPは、
//          遷移先のアクティビティが既に動いていればそのアクティビティより上にある
//          （この場合はB, C, D）アクティビティを消す、という挙動を設定する。
//          これによって、A→B→C→D→Aと遷移した後にbackボタンを押してもDに戻ることはなくなる。
                Intent intent = new Intent(DungeonActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DungeonActivity.this, ChattelActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonShop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DungeonActivity.this, ShopActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonEnemy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DungeonActivity.this, EnemyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

//    protected void scheduleService(){
//        Log.d(TAG, "scheduleService()"); Context context = getBaseContext();
//        Intent intent = new Intent(context, MyService3.class);
//        PendingIntent pendingIntent = PendingIntent.getService(
//                context, -1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        alarmManager.setInexactRepeating( AlarmManager.RTC, System.currentTimeMillis(), 5000, pendingIntent);
//    }
//
//    protected void cancelService() {
//        Context context = getBaseContext();
//        Intent intent = new Intent(context, MyService3.class);
//        PendingIntent pendingIntent = PendingIntent.getService(
//                context, -1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        alarmManager.cancel(pendingIntent);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusSQLiteOpenHelper sHelper = new StatusSQLiteOpenHelper(DungeonActivity.this);
//        nowLevel = sHelper.getNowLevel();
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }
}