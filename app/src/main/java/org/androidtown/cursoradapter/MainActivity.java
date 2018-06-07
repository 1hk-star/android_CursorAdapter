package org.androidtown.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper mHelper;
    SQLiteDatabase db;
    Cursor cursor;
    MyCursorAdapter myAdapter;

    final static String KEY_ID = "_id";
    final static String KEY_NAME = "name";
    final static String KEY_AGE = "age";
    final static String TABLE_NAME = "mytable";

    final static String querySelectAll = String.format( "SELECT * FROM %s", TABLE_NAME );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById( R.id.lv_name_age );

        //데이터베이스 생성
        mHelper = new MyDBHelper(this);
        db = mHelper.getWritableDatabase();

        //커서 어댑터 생성
        cursor = db.rawQuery( querySelectAll, null );
        myAdapter = new MyCursorAdapter ( this, cursor );

        //리스트뷰 어댑터 설정
        list.setAdapter( myAdapter );

    }

    public void mOnClick( View v )
    {
        EditText eName = (EditText) findViewById( R.id.et_name );
        EditText eAge = (EditText) findViewById( R.id.et_age );

        String name = eName.getText().toString();
        try
        {
            int age = Integer.parseInt( eAge.getText().toString() );

            // 문자열은 ''로 감싸야 한다.
            String query = String.format(
                    "INSERT INTO %s VALUES ( null, '%s', %d );", TABLE_NAME, name, age );
            db.execSQL( query );

            // 아래 메서드를 실행하면 리스트가 갱신된다. 하지만 구글은 이 메서드를 deprecate한다. 고로 다른 방법으로 해보자.
            // cursor.requery();
            cursor = db.rawQuery( querySelectAll, null );
            myAdapter.changeCursor( cursor );
        }
        catch( NumberFormatException e )
        {
            Toast.makeText( this, "나이는 정수를 입력해야 합니다", Toast.LENGTH_LONG).show();
        }

        eName.setText( "" );
        eAge.setText( "" );

        // 저장 버튼 누른 후 키보드 안보이게 하기
        InputMethodManager imm =
                (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow( eAge.getWindowToken(), 0 );
    }

}
