package com.example.cosmo.studentprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentInfo {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "student_name";
    public static final String KEY_PROGRAM = "academic_program";
    public static final String KEY_LEVEL = "year_level";

    private static final String DATABASE_NAME = "StudentInfoDB";
    private static final String DATABASE_TABLE = "studentTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private SQLiteDatabase db;

    private static class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) { super(context,DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
                    + " TEXT PRIMARY KEY, " + KEY_NAME
                    + " TEXT NOT NULL, " + KEY_PROGRAM + " TEXT NOT NULL, " + KEY_LEVEL + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public StudentInfo(Context c){ ourContext = c; }

    public StudentInfo open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        db = ourHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String id, String name, String program, String level){
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID, id);
        cv.put(KEY_NAME, name);
        cv.put(KEY_PROGRAM, program);
        cv.put(KEY_LEVEL, level);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData(){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        //ourDatabase.query(TABLE NAME, COLUMNS, SPECIFIC ROW SELECTED,)
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iProgram = c.getColumnIndex(KEY_PROGRAM);
        int iLevel = c.getColumnIndex(KEY_LEVEL);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName) +  " " + c.getString(iProgram) + " " + c.getString(iLevel) +"\n";
        }
        return result;
    }

    public String getName(String id){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "='" + id + "'", null, null, null, null);
        String result = "";
        if(c !=null){
            c.moveToFirst();
            result = c.getString(1);
            return result;
        }
        return null;
    }

    public String getProgram(String id){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "='" + id + "'", null, null, null, null);
        if(c !=null){
            c.moveToFirst();
            String program = c.getString(2);
            return program;
        }
        return null;
    }

    public String getLevel(String id){
        String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_PROGRAM,KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "='" + id + "'", null, null, null, null);
        if(c !=null){
            c.moveToFirst();
            String level = c.getString(3);
            return level;
        }
        return null;
    }

    public void updateEntry(String id, String sname, String sprogram, String slevel){
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_ROWID,id);
        cvUpdate.put(KEY_NAME,sname);
        cvUpdate.put(KEY_PROGRAM,sprogram);
        cvUpdate.put(KEY_LEVEL,slevel);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + " = ?", new String[]{id});
    }

    public void deleteEntry(String id){
        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "='" + id + "'", null);
    }


    public String[] allData(){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String[] result = new String[4];

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iProgram = c.getColumnIndex(KEY_PROGRAM);
        int iLevel = c.getColumnIndex(KEY_LEVEL);
        result[0] = "\n";
        result[1] = "\n";
        result[2] = "\n";
        result[3] = "\n";
        for(c.moveToFirst(  ); !c.isAfterLast(); c.moveToNext()){
                result[0] = result[0] + c.getString(iRow) + "\n";
                result[1] = result[1] + c.getString(iName) + "\n";
                result[2] = result[2] + c.getString(iProgram) + "\n";
                result[3] = result[3] + c.getString(iLevel) + "\n";
        }
        return result;
    }

    public int getRows(){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        int x = 0;
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            x++;
        }
        return x;
    }

    public boolean exists(String id){
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PROGRAM, KEY_LEVEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID+" = ? ", new String[]{id}, null, null, null);
        boolean exists = (c.getCount() > 0);
        c.close();
        return exists;
    }
}
