package com.example.lenovo.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LENOVO on 20-11-2017.
 */

public class MydataBase extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DATABASE_NAME="DB";
    public static final String TABLE_NAME = "dataTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "F_NAME";
    public static final String COL_3 = "L_NAME";
    public static final String COL_4 = "MBL";
    public static final String COL_5 = "PWD";

    public MydataBase(Context context)
    {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, F_NAME TEXT, L_NAME TEXT, MBL INTEGER, PWD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertData(String _firstname, String _secondname, int mobile, String _password)
    {

        db=getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,_firstname);
        contentValues.put(COL_3,_secondname);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,_password);

        db.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getData() {

        db=getReadableDatabase();

        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);

        return res;

    }

    public void updateData(int id, String _firstname, String _secondname, int mobile, String _password )

    {

        db=getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,_firstname);
        contentValues.put(COL_3,_secondname);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,_password);

        db.update(TABLE_NAME, contentValues, "ID=?",new String[]{String.valueOf(id)});

    }


    public void delete(int id)

    {
        db=getWritableDatabase();
        db.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
    }

}
