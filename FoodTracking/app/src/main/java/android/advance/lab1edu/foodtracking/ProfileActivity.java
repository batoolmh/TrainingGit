package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView fq, sq, tq;
    EditText first, second, third, name, email, gender , weight, height, age;
    Button save;
    DBHelper db;

    int flag = 0;
    String  n, f, s, t, g, e, w, h, a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db=new DBHelper(ProfileActivity.this);

        fq = (TextView)findViewById(R.id.textView32);
        sq = (TextView)findViewById(R.id.textView35);
        tq = (TextView)findViewById(R.id.textView36);

        first = (EditText)findViewById(R.id.editText18);
        second = (EditText)findViewById(R.id.editText19);
        third = (EditText)findViewById(R.id.editText20);

        name = (EditText)findViewById(R.id.editText17);
        email = (EditText)findViewById(R.id.editText21);
        gender = (EditText)findViewById(R.id.editText22);
        weight = (EditText)findViewById(R.id.editText23);
        height = (EditText)findViewById(R.id.editText24);
        age = (EditText)findViewById(R.id.editText25);
        save = (Button)findViewById(R.id.button12);



        if (flag == 0) {
            fq.setText(LoginActivity.loginFq);
            first.setText(LoginActivity.loginFqa);

            sq.setText(LoginActivity.loginSq);
            second.setText(LoginActivity.loginSqa);

            tq.setText(LoginActivity.loginTq);
            third.setText(LoginActivity.loginTqa);

            name.setText(LoginActivity.loginName);
            email.setText(LoginActivity.loginEmail);
            gender.setText(LoginActivity.loginGender);
            weight.setText(LoginActivity.loginWeight);
            height.setText(LoginActivity.loginHeight);
            age.setText(LoginActivity.loginAge);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = flag + 1;
                n = name.getText().toString();
                f = first.getText().toString();
                s = second.getText().toString();
                t = third.getText().toString();
                e = email.getText().toString();
                g = gender.getText().toString();
                w = weight.getText().toString();
                h = height.getText().toString();
                a = age.getText().toString();

                db.updateUser(LoginActivity.loginID, n, f, s, t, e, g, Integer.parseInt(w), Integer.parseInt(h), Integer.parseInt(a));

                first.setText(f);
                second.setText(s);
                third.setText(t);
                name.setText(n);
                email.setText(e);
                gender.setText(g);
                weight.setText(w);
                height.setText(h);
                age.setText(a);

            }
        });


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
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_report){
            Intent intent = new Intent(ProfileActivity.this, ReportActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }



}
