package org.androidtown.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by 1hk_s on 2018-06-07.
 */

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    //리스트뷰에 표시될 뷰 반환
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View v = inflater.inflate( R.layout.list_item, parent,false );
        return v;

    }

    //뷰의 속성 지정
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvName = (TextView) view.findViewById( R.id.tv_name );
        TextView tvAge = (TextView) view.findViewById( R.id.tv_age );

        //getColumnindex(name) : name에 해당하는 필드의 인덱스 번호를 반환한다.
        //cursor.getString(index) : 해당 커서가 위치한 인덱스 위치의 값을 반환한다.
        String name = cursor.getString( cursor.getColumnIndex( MainActivity.KEY_NAME ) );
        String age = cursor.getString( cursor.getColumnIndex( MainActivity.KEY_AGE ) );

        Log.d("스트링 확인", name + ", " + age);

        tvName.setText( name );
        tvAge.setText( age );

    }
}
