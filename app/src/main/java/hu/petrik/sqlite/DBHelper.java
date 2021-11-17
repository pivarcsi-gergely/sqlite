package hu.petrik.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tanulok.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "tanulok";
    private static final String COL_ID = "id";
    private static final String COL_VEZNEV = "vezeteknev";
    private static final String COL_KERNEV = "keresztnev";
    private static final String COL_JEGY = "jegy";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_VEZNEV + " TEXT NOT NULL, " +
                COL_KERNEV + " TEXT NOT NULL, " +
                COL_JEGY + " INTEGER NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
     onCreate(sqLiteDatabase);
    }
    public boolean rogzites(String vezeteknev, String keresztnev, int jegy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
                value.put(COL_VEZNEV, vezeteknev);
                value.put(COL_KERNEV, keresztnev);
                value.put(COL_JEGY, jegy);
        return db.insert(TABLE_NAME, null, value) != -1;
    }

    public Cursor listazas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor eredmeny = db.query(TABLE_NAME, new String[]{COL_ID, COL_VEZNEV, COL_KERNEV, COL_JEGY},
                null, null, null, null, null);
        return eredmeny;
    }
}
