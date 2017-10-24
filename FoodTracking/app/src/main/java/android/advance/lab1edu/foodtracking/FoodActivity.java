package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    String name = "";
    Spinner foodSpen;
    String[] foodArray={"Nuts & Seeds 100g","Nuts & Seeds cup (132g) ","Nuts & Seeds ounce (28g)",
            "Chocolate 100g", "Chocolate bar (101g)","Chocolate ounce (28g)",
            "Avocados 100g", "Avocados cubes(150g)","Avocados avocado (201g)",
            "Milk, Dairy & Eggs 100g ", "Milk, Dairy & Eggs 2 oz (56g) ","Milk, Dairy & Eggs ounce (28g) ",
            "Oily Fish 100g" , "Oily Fish fillet (88g)" , "Oily Fish 3oz (85g)" };

    String[] foodArray1={"","Nuts & Seeds 100g","Nuts & Seeds cup (132g) ","Nuts & Seeds ounce (28g)",
            "Chocolate 100g", "Chocolate bar (101g)","Chocolate ounce (28g)",
            "Avocados 100g", "Avocados cubes(150g)","Avocados avocado (201g)",
            "Milk, Dairy & Eggs 100g ", "Milk, Dairy & Eggs 2 oz (56g) ","Milk, Dairy & Eggs ounce (28g) ",
            "Oily Fish 100g" , "Oily Fish fillet (88g)" , "Oily Fish 3oz (85g)"};

    int [] cal = new int[] {718,948,201 ,598 ,604 ,167 , 160 ,240, 332 , 452 ,254 ,127 , 262 ,231 ,223 };


    int size = foodArray.length;
    int i, login;
    DBHelper db;
    Cursor c;
    String names = "Food"+ "  " + "Calories" + "\n";
    TextView text, calo;
    public static int calSum = 0;

    private static String TAG = "FoodActivity";
    private float[] yData = {0};
    private String[] xData = {""};
    PieChart pieChart;
    int counter =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);



        Log.d(TAG,"on create: starting to create chart");
        pieChart=(PieChart)findViewById(R.id.idPieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(15f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Food Calories");
        pieChart.setCenterTextSize(5);
        //pieChart.setDrawEntryLabels(true);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

            }

            @Override
            public void onNothingSelected() {

            }
        });




        db = new DBHelper(FoodActivity.this);
        text = (TextView)findViewById(R.id.textFood);
        calo = (TextView)findViewById(R.id.sumcalf);

        foodSpen = (Spinner) findViewById(R.id.spinnerfood);
        foodSpen.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,foodArray1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpen.setAdapter(aa);

       for( i = 0; i < size ; i ++){
            db.insertFood(foodArray[i], cal[i]);
        }



    }

    private void addDataSet(PieChart chart) {
        Log.d(TAG,"addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0 ; i < yData.length ; i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for(int i = 1 ; i < xData.length ; i++){
            xEntrys.add(xData[i]);
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"Food Calories");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        // add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_profile){
            Intent intent = new Intent(FoodActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_report){
            Intent intent = new Intent(FoodActivity.this, ReportActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(FoodActivity.this, MainActivity.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        name = foodSpen.getSelectedItem().toString();
        c = db.isExistF(foodSpen.getSelectedItem().toString());

        if(c.getCount() != 0)
        {
            try {
                while (c.moveToNext()) {
                    login = Integer.parseInt(c.getString(0));
                }
            } finally {
                c.close();
            }
        }

        Cursor c1 = db.getUser(LoginActivity.loginID);


        if(c1.getCount() != 0) {
            try {
                while (c1.moveToNext()) {
                    counter = c1.getInt(16);

                }
            } finally {
                c1.close();
            }
        }

        Cursor c2 = db.getFood(login);

        if(c2.getCount() != 0)
        {
            try {
                while (c2.moveToNext()) {
                  //  names = names + c2.getString(1) + "  " + c2.getInt(2) + "\n";
                    db.insertFoodHistory(c2.getString(1), LoginActivity.loginID);
                    calSum = calSum + c2.getInt(2);
                    //xData[counter] = c2.getString(1);
                    //yData[counter] = c2.getInt(2);

                }
            } finally {
                c2.close();
            }
            //text.setText(xData[counter]);
            //fill the chart
            addDataSet(pieChart);
            calo.setText(String.valueOf(calSum)+ "Calories");
            db.updateCal(LoginActivity.loginID, calSum);
            db.updateCount(LoginActivity.loginID, counter +1 );
        }


        Cursor c3 = db.getfHis(LoginActivity.loginID);

        if(c3.getCount() != 0) {
            try {
                while (c3.moveToNext()) {
                    names = names + c3.getString(1)+ "\n";
                }
            } finally {
                c1.close();
            }
        }
        text.setText(names);



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
