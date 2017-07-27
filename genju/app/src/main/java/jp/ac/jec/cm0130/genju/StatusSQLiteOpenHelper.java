package jp.ac.jec.cm0130.genju;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by minayu on 17/03/06.
 */
public class StatusSQLiteOpenHelper extends SQLiteOpenHelper {
    public StatusSQLiteOpenHelper(Context context) {
        super(context, "STATUS_DB9", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STATUS(id INTEGER, level INTEGER, stamina INTEGER, attack INTEGER, defence INTEGER, exp INTEGER)");
        db.execSQL("INSERT INTO STATUS VALUES(1, 1, 28, 9, 10, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Expの獲得
    public boolean insertExp(Status src) {
        long ret = -1;
        Status tmp = getItemFromId(src.getExp());
            SQLiteDatabase db = getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                values.put("exp", tmp.getExp() + src.getExp());
                ret = db.update("STATUS", values, "id=1", null);
            } finally {
                db.close();
            }
        return ret != -1;
    }

//    DBからStatusを読み込む
    public Status getItemFromId(int id) {
        Status tmp = null;
        SQLiteDatabase db = getReadableDatabase();
        if( db == null ) return tmp;
        try {
//            Cursor cur = db.rawQuery("select _id, name, count, use from CHATTEL where name = ?", new String[]{name});
            Cursor cur = db.rawQuery("select id, level, stamina, attack, defence, exp " +
                    "from STATUS where id = 1", null);
            if( cur.moveToNext() ) {
                tmp = new Status(cur.getInt(0), cur.getInt(1), cur.getInt(2) ,cur.getInt(3), cur.getInt(4), cur.getInt(5));
            }
            cur.close();
        } finally {
            db.close();
        }
        return tmp;
    }

    // 現在のExpを表示
    public int getNowExp() {
        int nowExp = 0;
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query("STATUS", new String[]{"exp"}, null, null, null, null, null);
            cur.moveToNext();
            nowExp = cur.getInt(0);
            cur.close();
        } finally {
            db.close();
        }
        return nowExp;
    }

    // DBのExpを更新
    public void updateNowExp(int exp) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("exp", exp);
            db.update("STATUS", values, "id=1", null);
        } finally {
            db.close();
        }
    }

    // Level,Statusの上昇
    public boolean levelUp(Status src) {
        long ret = -1;
        Status tmp = getItemFromId(src.getExp());
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("level", tmp.getLevel() + src.getLevel());
            values.put("stamina", tmp.getStamina() + src.getStamina());
            values.put("attack", tmp.getAttack() + src.getAttack());
            values.put("defence", tmp.getDefence() + src.getDefence());
//            values.put("", tmp.getLevel() + src.getLevel());

            ret = db.update("STATUS", values, "id=1", null);
        } finally {
            db.close();
        }
        return ret != -1;
    }

    // 現在のLevelを表示
    public int getNowLevel() {
        int nowExp = 0;
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query("STATUS", new String[]{"level"}, null, null, null, null, null);
            cur.moveToNext();
            nowExp = cur.getInt(0);
            cur.close();
        } finally {
            db.close();
        }
        return nowExp;
    }

    // 現在のStaminaを表示
    public int getNowStamina() {
        int nowStamina = 0;
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query("STATUS", new String[]{"stamina"}, null, null, null, null, null);
            cur.moveToNext();
            nowStamina = cur.getInt(0);
            cur.close();
        } finally {
            db.close();
        }
        return nowStamina;
    }

    // 現在のAttackを表示
    public int getNowAttack() {
        int nowAttack = 0;
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query("STATUS", new String[]{"attack"}, null, null, null, null, null);
            cur.moveToNext();
            nowAttack = cur.getInt(0);
            cur.close();
        } finally {
            db.close();
        }
        return nowAttack;
    }

    // 現在のDefenceを表示
    public int getNowDefence() {
        int nowDefence = 0;
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query("STATUS", new String[]{"defence"}, null, null, null, null, null);
            cur.moveToNext();
            nowDefence = cur.getInt(0);
            cur.close();
        } finally {
            db.close();
        }
        return nowDefence;
    }


//    public void getAllStatus(ArrayList<Status> ary) {
//        ary.clear();
//        SQLiteDatabase db = getReadableDatabase();
//        if( db == null ) return;
//        try {
//            // money,date,idを取り出す
//            Cursor cur = db.query("Status", new String[]{"id", "level", "stamina",
//                    "attack", "defence", "exp", }, null, null, null, null, null);
//            while( cur.moveToNext() ) {
//                ary.add(new Status(cur.getInt(0), cur.getInt(1),
//                        cur.getInt(2), cur.getInt(3), cur.getInt(4), cur.getInt(5)));
//            }
//            cur.close();
//        } finally {
//            db.close();
//        }
//    }

    //    public void getAllChattelItem(ArrayList<Chattel> ary) {
//        ary.clear();
//        SQLiteDatabase db = getReadableDatabase();
//        if( db == null ) return;
//        try {
//            // money,date,idを取り出す
//            Cursor cur = db.query("CHATTEL", new String[]{"_id","name","count","use" }, null, null, null, null, null);
//            while( cur.moveToNext() ) {
//                ary.add(new Chattel(cur.getInt(0), cur.getString(1), cur.getInt(2) ,cur.getInt(3)));
//            }
//            cur.close();
//        } finally {
//            db.close();
//        }
//    }




}