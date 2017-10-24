package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login, back;
    EditText username, password ;
    TextView forget;
    DBHelper db;
    Cursor c;
    public static int loginID, loginHours, loginCal;
    public static String loginAge, loginHeight, loginWeight;
    public static String loginName, loginFq, loginSq, loginTq, loginFqa, loginSqa, loginTqa,  loginGender, loginEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (Button)findViewById(R.id.button3);
        back = (Button)findViewById(R.id.button10);

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText3);
        forget = (TextView)findViewById(R.id.textView10);
        db = new DBHelper(LoginActivity.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = username.getText().toString();
                String pass = password.getText().toString();

                c = db.isExist(username.getText().toString(),password.getText().toString());

               if(c.getCount() != 0)
                {
                    try {
                        while (c.moveToNext()) {
                            loginID = Integer.parseInt(c.getString(0));
                            loginName = c.getString(1).toString();
                            loginFq = c.getString(2).toString();
                            loginFqa = c.getString(3).toString();

                            loginSq = c.getString(4).toString();
                            loginSqa = c.getString(5).toString();

                            loginTq = c.getString(6).toString();
                            loginTqa = c.getString(7).toString();

                            loginEmail = c.getString(9).toString();
                            loginGender = c.getString(10).toString();

                            loginWeight = c.getString(11).toString();
                            loginHeight = c.getString(12).toString();
                            loginAge = c.getString(13).toString();
                            loginHours = Integer.parseInt(c.getString(14));
                            loginCal = Integer.parseInt(c.getString(15));
                        }
                    } finally {
                        c.close();
                    }


                    Intent intent = new Intent(LoginActivity.this, ReportActivity.class);
                    startActivity(intent);
                }
                else if(name.matches("")&& pass.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter The User Information !" , Toast.LENGTH_LONG).show();
                }
                else if(name.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter The User Name !" , Toast.LENGTH_LONG).show();
                }
                else if(pass.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter The Password !" , Toast.LENGTH_LONG).show();
                }


                else {

                    if (name.compareTo("Batool") == 0 && pass.compareTo("123") == 0) {
                        Intent intent = new Intent(LoginActivity.this, ReportActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error In User Name Or Password !", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RestorePassword.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
}
