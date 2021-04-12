package com.example.fitlinkv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addData")
public class AddingShat  {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = " user_name") // specifying the name of the collum
    private String name;

    @ColumnInfo(name ="user_email") //^
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name =name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail (){
        this.email = email;
    }

    //
//  data information
//    //TABLE INDO
//    public static final String TABLE_NAME = "Nutritioninfo";
//    public static final String COLUMN_ID ="ID";
//    public static final String COLUMN_CALORIES = "cal";
//    public static final String COLUMN_PROTEIN = "pro";
//    public static final String COLUMN_FAT = "fat";
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) { //SQL METHOD
//
//        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, cal TEXT, pro TEXT, fat TEXT )") ;
//
//
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // this will check if the table exist first,
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//
//
//    }
//    public boolean insertAction (int cal, int pro, int fat){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_CALORIES, cal );
//        contentValues.put(COLUMN_PROTEIN, pro);
//        contentValues.put(COLUMN_FAT, fat);
//        long outcome = db.insert(TABLE_NAME, null, contentValues); //storing the mehod into the outcome method, to check if it works, using toast method to let the user know the if it worked or not.
//        if (outcome== -1){
//            Toast.makeText(context, "Unsuccessfully upload", Toast.LENGTH_LONG).show();
//            return false;
//
//        }


    // THIS IS TO CALL IT - SQLite
//    EditText caloriesint, proteinint, fatint;
//    Button button2,button3, button4;
//    Database1 db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adding_shat);
//
//
//        proteinint = findViewById(R.id.proteinint);
//        caloriesint = findViewById(R.id.caloriesint);
//        fatint = findViewById(R.id.fatint);
//
//        button2 = findViewById(R.id.insertbutton);
//        button3 = findViewById(R.id.update);
//        button4 = findViewById(R.id.delete);
//
//
////        button2.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////                Database1 dbse = new Database1(AddingShat.this);
////                dbse.insertAction(Integer.valueOf(caloriesint.getText().toString().trim()),
////                        Integer.valueOf(proteinint.getText().toString().trim()),
////                        Integer.valueOf(fatint.getText().toString().trim()));
////setContentView(R.layout.activity_adding_shat);
////            }
////        });
//
//
//
//
//
//
//
//
//
//
//
//    }
}





