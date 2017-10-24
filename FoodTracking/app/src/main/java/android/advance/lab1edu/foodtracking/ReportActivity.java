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

public class ReportActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    TextView text;
    DBHelper db;
    Cursor c;
    String name;
    int hours, cal;
    Spinner time ;
    String [] array = {"Last Week", "Today", "Last Month", "Last Year"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        text = (TextView)findViewById(R.id.textView21);
        db = new DBHelper(ReportActivity.this);
        time = (Spinner) findViewById(R.id.spinner5);

        time.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        time.setAdapter(aa);




        c = db.getUser(LoginActivity.loginID);


        if(c.getCount() != 0) {
            try {
                while (c.moveToNext()) {
                    name = c.getString(1);
                    hours = c.getInt(14);
                    cal = c.getInt(15);

                }
            } finally {
                c.close();
            }

            text.setText("Hello " + name + ", You Play " + String.valueOf(hours) + " Hours, And Eat " + String.valueOf(cal) + " Calories Until Now .");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_r, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.men_profile){
            Intent intent = new Intent(ReportActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(ReportActivity.this, MainActivity.class);
            startActivity(intent);

        }

        if(item.getItemId() == R.id.men_sport){
            Intent intent = new Intent(ReportActivity.this, SportActivity.class);
            startActivity(intent);

        }
        if(item.getItemId() == R.id.men_food){
            Intent intent = new Intent(ReportActivity.this, FoodActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String choice = (time.getSelectedItem().toString());

        if (choice.compareTo("Today") == 0){

        }
        if (choice.compareTo("Last Week") == 0){

        }
        if (choice.compareTo("Last Month") == 0){

        }
        if (choice.compareTo("Last Year") == 0){

        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
