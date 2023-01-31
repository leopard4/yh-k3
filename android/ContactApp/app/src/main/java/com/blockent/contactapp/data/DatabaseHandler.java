package com.blockent.contactapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.blockent.contactapp.model.Contact;
import com.blockent.contactapp.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DB_NAME, null, Util.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 테이블 생성
        String CREATE_CONTACT_TABLE = "create table contact ( id integer primary key, name text, phone text )";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 기존의 테이블을 삭제하고, 새 테이블을 다시 만든다.

        String DROP_TABLE = "drop table contact";
//        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DB_NAME});

        onCreate(sqLiteDatabase);

    }

    // 이제부터는 우리가 앱 동작시키는데 필요한
    // CRUD 관련된 SQL문이 들어간,
    // 메소드를 만들면 된다.

    // 1. 연락처 추가하는 메소드(함수)
    public void addContact(Contact contact){
        // 1. 데이터베이스를 가져온다.
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. 저장가능한 형식으로 만들어 준다.
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.name);
        values.put(Util.KEY_PHONE, contact.phone);
        // 3. insert 한다.
        db.insert(Util.TABLE_NAME, null, values);
        // 4. db 사용이 끝나면, 닫아준다.
        db.close();
    }



}









