package org.androidtown.cursoradapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.androidtown.cursoradapter.MainActivity.KEY_AGE;
import static org.androidtown.cursoradapter.MainActivity.KEY_NAME;
import static org.androidtown.cursoradapter.MainActivity.TABLE_NAME;

/**
 * Created by 1hk_s on 2018-06-07.
 */

class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context) {
        super(context, "MyData.db", null, 3);
    }

    public void onCreate(SQLiteDatabase db) {
        // AUTOINCREMENT 속성 사용 시 PRIMARY KEY로 지정한다.
        String query = String.format("CREATE TABLE %s ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "%s TEXT, "
                + "%s INTEGER );", TABLE_NAME, KEY_NAME, KEY_AGE);
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }
}