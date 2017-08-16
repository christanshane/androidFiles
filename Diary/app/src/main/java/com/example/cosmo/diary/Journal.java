package com.example.cosmo.diary;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;

public class Journal{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "journalDb";
    public static final String TABLE_NAME = "entries";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_DATE = "entry_date";
    public static final String KEY_TITLE = "entry_title";
    public static final String KEY_CONTENT = "entry_content";

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATE
                    + " TEXT NOT NULL, " + KEY_TITLE + " TEXT NOT NULL, " + KEY_CONTENT + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public Journal(Context c ){
        ourContext = c;
    }

    public Journal open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String date, String title, String content){
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, date);
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);
        return ourDatabase.insert(TABLE_NAME, null, cv);
    }

    public String[] getData(){
        String[] columns = new String[]{KEY_ROWID,KEY_DATE,KEY_TITLE,KEY_CONTENT};
        Cursor c = ourDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        String[] result = new String[4];
        String[] temp;

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iDate = c.getColumnIndex(KEY_DATE);
        int iTitle = c.getColumnIndex(KEY_TITLE);
        int iContent = c.getColumnIndex(KEY_CONTENT);

        int x = 0;
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            temp = new String[1];
            result[0]=c.getString(iRow);
            result[1]=c.getString(iDate);
            result[2]=c.getString(iTitle);
            result[3]=c.getString(iContent);
        }
    }


}
