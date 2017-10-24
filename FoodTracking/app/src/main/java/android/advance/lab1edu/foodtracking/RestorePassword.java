package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RestorePassword extends AppCompatActivity {

    TextView firstQues ,secondQues,thirdQues ;
    EditText firstAns, secondAns, thirdAns, name, email;
    Button back, finish ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_password);

        firstQues = (TextView)findViewById(R.id.textView11);
        secondQues = (TextView)findViewById(R.id.textView12);
        thirdQues = (TextView)findViewById(R.id.textView13);

        firstAns = (EditText)findViewById(R.id.editText4);
        secondAns = (EditText)findViewById(R.id.editText6);
        thirdAns = (EditText)findViewById(R.id.editText7);
        name = (EditText)findViewById(R.id.editText12);
        email = (EditText)findViewById(R.id.editText13);

        back = (Button)findViewById(R.id.button9);
        finish = (Button)findViewById(R.id.button8);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestorePassword.this, LoginActivity.class);
                startActivity(intent);


            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RestorePassword.this, LoginActivity.class);
                startActivity(intent);

            }
        });



    }
}
