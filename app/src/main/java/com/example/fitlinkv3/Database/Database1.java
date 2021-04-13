package com.example.fitlinkv3.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.RoomDatabase;


//@Database(entities = AddingShat.class, exportSchema = false, version = 1 )
//public abstract class Database1 extends RoomDatabase {
public class Database1 extends SQLiteOpenHelper {


public static final String DATABASE_NAME = "databaseSql.db"; //TABLE NAME
    Context context;
    public Database1(Context context) {
        super(context, DATABASE_NAME, null, 1);
//            this.context = context;
    }




    //TABLE INDO
    public static final String TABLE_NAME = "Nutritioninfo";
    public static final String COLUMN_ID ="ID";
    public static final String COLUMN_CALORIES = "cal";
    public static final String COLUMN_PROTEIN = "pro";
    public static final String COLUMN_FAT = "fat";



    @Override
    public void onCreate(SQLiteDatabase db) { //SQL METHOD

        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, cal TEXT, pro TEXT, fat TEXT )") ;


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // this will check if the table exist first,
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    public boolean insertAction (int cal, int pro, int fat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CALORIES, cal );
        contentValues.put(COLUMN_PROTEIN, pro);
        contentValues.put(COLUMN_FAT, fat);
        long outcome = db.insert(TABLE_NAME, null, contentValues); //storing the mehod into the outcome method, to check if it works, using toast method to let the user know the if it worked or not.
        if (outcome== -1){
            Toast.makeText(context, "Unsuccessfully upload", Toast.LENGTH_LONG).show();
            return false;

        }
        else {
            Toast.makeText(context, "Successfully uploaded", Toast.LENGTH_LONG).show();
            return true;


        }
    }
}
