package com.example.fitlinkv3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.fitlinkv3.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddingShat extends AppCompatActivity{


    private EditText fatint;
    private EditText proteinint;
    private EditText caloriesint;

    private TextView results;

    //piechart
    AnyChartView nutritionpiechart;




    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_shat);
        fatint = (EditText) findViewById(R.id.fatint);
        proteinint = (EditText) findViewById(R.id.proteinint);
        caloriesint = (EditText) findViewById(R.id.caloriesint);
        results = (TextView) findViewById(R.id.results);
        //setup piechart
        nutritionpiechart = findViewById(R.id.intakechart);

    }

    public void insertbutton (View View) {

        //Create instance of piechart
        Pie pie = AnyChart.pie();

        //Arraylist for the piechart
        List<DataEntry> nutritioninfo = new ArrayList<>();

        int no1 = Integer.parseInt(proteinint.getText().toString());
        int no2 = Integer.parseInt(caloriesint.getText().toString());
        int no3 = Integer.parseInt(fatint.getText().toString());
        int sum = no1 + no2 + no3;
        results.setText(String.valueOf(sum));

        //sets inputted data as piechart variables
        nutritioninfo.add(new ValueDataEntry("Protein",no1));
        nutritioninfo.add(new ValueDataEntry("Calories", no2));
        nutritioninfo.add(new ValueDataEntry("Fat", no3));
        //set piechart data
        pie.data(nutritioninfo);

        //customise pie chart
        pie.labels().position("inside");
        pie.legend().title().enabled(false);
        pie.legend()
                .position("inside")
                .fontSize(8)
                .iconSize(8)
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        pie.legend().title().enabled(false);

        //set piechart
        nutritionpiechart.setChart(pie);

    }
}







//// this is for the information inside the table inside the database
//@Entity(tableName = "addData")
//public class AddingShat implements Serializable {
//
//    @PrimaryKey (autoGenerate = true) // should i primary key the main colums? or should i auto genereate it?
//    private int id;
//
//
//    @ColumnInfo(name = "text") // specifying the name of the collum
//    private String text ;
//
////    @ColumnInfo(name = "pro") //^
////    private String pro;
//
////    public AddingShat (@NonNull int id, String kal , String pro) {
////
////        this.id = id;
////        this.kal = kal;
////        this.pro = pro;
////
////    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//
////    public String getKal() {
////        return kal;
////    }
////
////    public void setKal (String kal) {
////        this.kal = kal;
////    }
//
//
//
////
////    public int getName () {
////        return name;
////    }
////
////    public int setName (String name){
////        this.name =name;
////    }
////
//    public String getText() {
//        return text;
//    }
//    public void setText(String sTECT){
//        this.text = text;
//    }
//
//    //
////  data information
////    //TABLE INDO
////    public static final String TABLE_NAME = "Nutritioninfo";
////    public static final String COLUMN_ID ="ID";
////    public static final String COLUMN_CALORIES = "cal";
////    public static final String COLUMN_PROTEIN = "pro";
////    public static final String COLUMN_FAT = "fat";
////
////
////
////    @Override
////    public void onCreate(SQLiteDatabase db) { //SQL METHOD
////
////        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, cal TEXT, pro TEXT, fat TEXT )") ;
////
////
////    }
////
////
////    @Override
////    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // this will check if the table exist first,
////        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
////        onCreate(db);
////
////
////    }
////    public boolean insertAction (int cal, int pro, int fat){
////        SQLiteDatabase db = this.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(COLUMN_CALORIES, cal );
////        contentValues.put(COLUMN_PROTEIN, pro);
////        contentValues.put(COLUMN_FAT, fat);
////        long outcome = db.insert(TABLE_NAME, null, contentValues); //storing the mehod into the outcome method, to check if it works, using toast method to let the user know the if it worked or not.
////        if (outcome== -1){
////            Toast.makeText(context, "Unsuccessfully upload", Toast.LENGTH_LONG).show();
////            return false;
////
////        }
//
//
//    // THIS IS TO CALL IT - SQLite
////    EditText caloriesint, proteinint, fatint;
////    Button button2,button3, button4;
////    Database1 db;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_adding_shat);
////
////
////        proteinint = findViewById(R.id.proteinint);
////        caloriesint = findViewById(R.id.caloriesint);
////        fatint = findViewById(R.id.fatint);
////
////        button2 = findViewById(R.id.insertbutton);
////        button3 = findViewById(R.id.update);
////        button4 = findViewById(R.id.delete);
////
////
//////        button2.setOnClickListener(new View.OnClickListener() {
//////
//////            @Override
//////            public void onClick(View v) {
//////                Database1 dbse = new Database1(AddingShat.this);
//////                dbse.insertAction(Integer.valueOf(caloriesint.getText().toString().trim()),
//////                        Integer.valueOf(proteinint.getText().toString().trim()),
//////                        Integer.valueOf(fatint.getText().toString().trim()));
//////setContentView(R.layout.activity_adding_shat);
//////            }
//////        });
////
////
////
////
////
////
////
////
////
////
////
////    }
//}
//
//
//
//
//
