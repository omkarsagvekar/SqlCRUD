package com.example.sqlcrud.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PageRange;

import androidx.annotation.Nullable;

import com.example.sqlcrud.model.Users;
import com.example.sqlcrud.params.Params;

public class MyDbHandler extends SQLiteOpenHelper{

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "(" +
                Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME + " TEXT, " +
                Params.KEY_PASSWORD + " TEXT, " + Params.KEY_EMAIL + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";

        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, user.getName());
        values.put(Params.KEY_PASSWORD, user.getPassword());
        values.put(Params.KEY_EMAIL, user.getEmail());
        values.put(Params.KEY_PHONE, user.getPhoneNum());

        db.insert(Params.TABLE_NAME, null, values);
        db.close();
    }
}
