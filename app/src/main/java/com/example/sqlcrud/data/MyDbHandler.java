package com.example.sqlcrud.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlcrud.model.Users;
import com.example.sqlcrud.params.Params;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkUser(String username) {
        String[] columns = {Params.KEY_ID};
        String selection = Params.KEY_NAME + " = ?";
        String[] selectionArgs = {username};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Params.TABLE_NAME, columns, selection, selectionArgs, null,
                                null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;

    }

    public boolean checkUser(String username, String password) {
        String[] columns = {Params.KEY_ID};
        String selection = Params.KEY_NAME + " = ?" +" AND " + Params.KEY_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Params.TABLE_NAME, columns, selection, selectionArgs, null,
                null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    @SuppressLint("Range")
    public List<Users> getAllUsers(){
        List<Users> usersList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()){
            do {
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Params.KEY_ID))));
                users.setName(cursor.getString(cursor.getColumnIndex(Params.KEY_NAME)));
                users.setPassword(cursor.getString(cursor.getColumnIndex(Params.KEY_PASSWORD)));
                users.setEmail(cursor.getString(cursor.getColumnIndex(Params.KEY_EMAIL)));
                users.setPhoneNum(cursor.getString(cursor.getColumnIndex(Params.KEY_PHONE)));
                usersList.add(users);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usersList;
    }
    @SuppressLint("Recycle")
    public void deleteUser(String Username){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM "+ Params.TABLE_NAME +" WHERE "+ Params.KEY_NAME+ " = ? ", new String[]{Username});
//        if(cursor.getCount() > 0) {
//            db.delete(Params.TABLE_NAME, Params.KEY_NAME + " = ? ", new String[]{Username});
//        }
//        db.close();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_NAME + " =? ",new String[]{Username});
        db.close();
    }
}
