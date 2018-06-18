package com.ashutosh.ams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Student.db";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table registration "+"(res_id integer primary key autoincrement, user_fname text,user_lname text,user_email text,user_phone text,user_uid text,user_pass text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registration");
    }

    public boolean insert_userData(String tablename, ContentValues contentValues)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(tablename,null,contentValues);
        return true;
    }

    public Cursor get_userdata()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from registration",null);
        return c;
    }

    public Cursor check_login(String username, String userpass)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from registration where user_uid='"+username+"'and user_pass='"+userpass+"'",null);
        return cursor;
    }
}
