package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sportSpen;
    String[] sportArray={"Running", "Swimming", "Biking", "Inline Skating", "Frisbee", "Beach Volleyball", "Football", "Soccer", "Kayaking",
            "Surfing","Hiking", "Horseback Riding", "Yard Work", "Car Wash", "Yoga","Brisk Walking"};
    String[] sportArray1={"","Running", "Swimming", "Biking", "Inline Skating", "Frisbee", "Beach Volleyball", "Football", "Soccer", "Kayaking",
            "Surfing","Hiking", "Horseback Riding", "Yard Work", "Car Wash", "Yoga","Brisk Walking"};

    int [] cal = new int[] {700, 550, 560, 500, 208, 484, 500, 600, 346, 208, 400, 270, 270, 200, 340, 242};
    int size = sportArray.length;
    int i, login;
    int hou = -1;
    int cl = 0;
    DBHelper db;
    Cursor c;
    String names = "Sport"+ "  " + "Calories" + "\n";
    TextView text, ca, ho;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        db = new DBHelper(SportActivity.this);
        text = (TextView)findViewById(R.id.textSport);
        ca = (TextView)findViewById(R.id.sumcal);
        ho = (TextView)findViewById(R.id.textView26);


        //String names = "";

        sportSpen = (Spinner) findViewById(R.id.spinnerSport);
        sportSpen.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sportArray1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpen.setAdapter(aa);

        for( i = 0; i < size ; i ++){
            db.insertSport(sportArray[i], cal[i]);
        }
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
            Intent intent = new Intent(SportActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_report){
            Intent intent = new Intent(SportActivity.this, ReportActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(SportActivity.this, MainActivity.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        c = db.isExistS(sportSpen.getSelectedItem().toString());
        hou = hou +1;

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
        Cursor c2 = db.getSport(login);

        if(c2.getCount() != 0)
        {
            try {
                while (c2.moveToNext()) {
                    names = names + c2.getString(1) + "  " + c2.getInt(2) + "\n";
                    cl = cl + c2.getInt(2);
                }
            } finally {
                c2.close();
            }
            text.setText(names);
            ho.setText(String.valueOf(hou) + " Hours");
            ca.setText(String.valueOf(cl) + " Calories ");

            db.updateHo(LoginActivity.loginID, hou);
            int diff = FoodActivity.calSum - cl;
            db.updateCal(LoginActivity.loginID, diff);


        }







    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
