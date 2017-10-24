package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button log, sign;
    TextView about;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            log = (Button)findViewById(R.id.button);
            sign = (Button)findViewById(R.id.button2);
            about = (TextView)findViewById(R.id.textView5);


            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent= new Intent(MainActivity.this , LoginActivity.class);
                    startActivity(intent);

                }
            });

            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(MainActivity.this , RegisterActivity.class);
                    startActivity(intent);

                }
            });


            about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "This application is used to keep track with your daily meals sport\n" , Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "and calculate your calories\n" , Toast.LENGTH_LONG).show();


                }
            });
    }
}
