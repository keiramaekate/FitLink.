package com.example.fitlinkv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database1 extends SQLiteOpenHelper {


    //class Database1 extends SQLiteOpenHelper {

        private Context context;
        private static final String DATABASE_NAME = "databaseSql.dp"; //TABLE NAME

        //TABLE INDO
        public static final String TABLE_NAME = "Nutritioninfo";
        private static final String COLUMN_ID ="id";
        private static final String COLUMN_CALORIES = "cal";
        private static final String COLUMN_PROTEIN = "pro";
        private static final String COLUMN_FAT = "fat";


        @Override
        public void onCreate(SQLiteDatabase db) { //SQL METHOD

            String query = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + COLUMN_CALORIES + " INTEGER," + COLUMN_PROTEIN + " INTEGER, "+ COLUMN_FAT +"INTEGER) ;" ;

            db.execSQL(query);
        }

        public Database1(@Nullable Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context = context;
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // this will check if the table exist first,
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);


        }
        void insertAction (int cal, int pro, int fat){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cinfo = new ContentValues();
            cinfo.put(COLUMN_CALORIES, cal );
            cinfo.put(COLUMN_PROTEIN, pro);
            cinfo.put(COLUMN_FAT, fat);
            long outcome = db.insert(TABLE_NAME, null, cinfo); //storing the mehod into the outcome method, to check if it works, using toast method to let the user know the if it worked or not.
            if (outcome== 0 ){
                Toast.makeText(context, "Unsuccessfully upload", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Successfully uploaded", Toast.LENGTH_LONG).show();

            }
        }
    }
