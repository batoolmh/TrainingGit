package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button back, food, sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        back = (Button)findViewById(R.id.button11);
        food = (Button)findViewById(R.id.button5);
        sport = (Button)findViewById(R.id.button6);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, FoodActivity.class);
                startActivity(intent);

            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, SportActivity.class);
                startActivity(intent);
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu_search, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_profile){
            Intent intent = new Intent(Main2Activity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_report){
            Intent intent = new Intent(Main2Activity.this, ReportActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(Main2Activity.this, SearchActivity.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}
