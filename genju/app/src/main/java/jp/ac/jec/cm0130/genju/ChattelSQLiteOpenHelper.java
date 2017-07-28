package jp.ac.jec.cm0130.genju;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by minayu on 17/03/06.
　***所持アイテムに関する処理
  Chattel = 財産（Itemは予約語のため使用不可
*/

public class ChattelSQLiteOpenHelper extends SQLiteOpenHelper {
    public ChattelSQLiteOpenHelper(Context context) {
        super(context, "CHATTEL_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // アイテムの初期設定
        /*　name...名称
            count..個数
            use...特殊（使用可能かどうか...?
        
        */
        
        db.execSQL("CREATE TABLE CHATTEL( _id integer primary key autoincrement," +
                "name TEXT, count INTEGER, use INTEGER)");
        db.execSQL("INSERT INTO CHATTEL VALUES(1, '石ころ', 1, 0)");
//        db.execSQL("INSERT INTO CHATTEL VALUES(2, '鉄のかけら', 5, 0)");
//        db.execSQL("INSERT INTO CHATTEL VALUES(3, '太古の宝箱', 2, 1)");
        db.execSQL("INSERT INTO CHATTEL VALUES(4, 'ぼろい布', 1, 1)");
        db.execSQL("INSERT INTO CHATTEL VALUES(5, '金のかけら', 1, 1)");
        db.execSQL("INSERT INTO CHATTEL VALUES(6, 'エメラルド', 1, 1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 全ての所持アイテムの出力　アイテム画面で一覧表示する用
    public void getAllChattelItem(ArrayList<Chattel> ary) {
        ary.clear();
        SQLiteDatabase db = getReadableDatabase();
        if( db == null ) return;
        try {
            // id,name,count,useを取り出す
            Cursor cur = db.query("CHATTEL", new String[]{"_id","name","count","use" }, null, null, null, null, null);
            while( cur.moveToNext() ) {
                ary.add(new Chattel(cur.getInt(0), cur.getString(1), cur.getInt(2) ,cur.getInt(3)));
            }
            cur.close();
        } finally {
            db.close();
        }
    }
    // 新規アイテム追加処理　ダンジョン、討伐のドロップなど用
    public boolean insertChattel(Chattel src) {
        long ret = -1;
        Chattel tmp = getItemFromName(src.getName());
        if ( tmp == null ) {
            SQLiteDatabase db = getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                //            values.put("_id", src.getId());
                values.put("name", src.getName());
                values.put("count", src.getCount());
                values.put("use", src.getUse());
                ret = db.insert("CHATTEL", null, values);
            } finally {
                db.close();
            }
        } else {
            SQLiteDatabase db = getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                values.put("count", tmp.getCount() + 1);
                ret = db.update("CHATTEL", values, "_id=?", new String[]{String.valueOf(tmp.getId())});
            } finally {
                db.close();
            }

        }
        return ret != -1;
    }
    
    // 名称が同じアイテムを個数だけ追加する処理用...?
    public Chattel getItemFromName(String name) {
        Chattel tmp = null;
        SQLiteDatabase db = getReadableDatabase();
        if( db == null ) return tmp;
        try {
            // 同じnameのアイテムを取り出す
            Cursor cur = db.rawQuery("select _id, name, count, use from CHATTEL where name = ?", new String[]{name});
            if( cur.moveToNext() ) {
                tmp = new Chattel(cur.getInt(0), cur.getString(1), cur.getInt(2) ,cur.getInt(3));
            }
            cur.close();
        } finally {
            db.close();
        }
        return tmp;
    }
}
