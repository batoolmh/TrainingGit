package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button register,back;
    EditText name, password, email, weight, height, age, fa, sa, ta;
    Spinner firstQ, secondQ, thirdQ, gen;
    String[] questions={"","Whats is your favourite book ?","Where did you born ?","In what year was your father born ?"};
    String[] gender={"","Male","Female"};

    String n, p, e, f, s, t, g,fqa, sqa, tqa;
    int w, h, a;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button)findViewById(R.id.button4);
        back = (Button)findViewById(R.id.button7);
        name = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText5);
        email = (EditText)findViewById(R.id.editText8);
        weight = (EditText)findViewById(R.id.editText14);
        height = (EditText)findViewById(R.id.editText15);
        age = (EditText)findViewById(R.id.editText16);
        fa = (EditText)findViewById(R.id.editText9);
        sa = (EditText)findViewById(R.id.editText10);
        ta = (EditText)findViewById(R.id.editText11);



        firstQ = (Spinner) findViewById(R.id.spinner);
        secondQ = (Spinner) findViewById(R.id.spinner2);
        thirdQ = (Spinner) findViewById(R.id.spinner3);
        gen = (Spinner) findViewById(R.id.spinner4);

        firstQ.setOnItemSelectedListener(this);
        secondQ.setOnItemSelectedListener(this);
        thirdQ.setOnItemSelectedListener(this);
        gen.setOnItemSelectedListener(this);

        db = new DBHelper(RegisterActivity.this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,questions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstQ.setAdapter(aa);
        secondQ.setAdapter(aa);
        thirdQ.setAdapter(aa);
        gen.setAdapter(bb);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n =  name.getText().toString();
                p = password.getText().toString();
                e = email.getText().toString();
                f = firstQ.getSelectedItem().toString();
                s = secondQ.getSelectedItem().toString();
                t = thirdQ.getSelectedItem().toString();
                g = gen.getSelectedItem().toString();
                fqa = fa.getText().toString();
                sqa = sa.getText().toString();
                tqa = ta.getText().toString();


                w = Integer.parseInt(weight.getText().toString());
                h = Integer.parseInt(height.getText().toString());
                a = Integer.parseInt(age.getText().toString());

                if(n.matches("") && p.matches("") && e.matches("") && f.matches("") && s.matches("") && t.matches("") && weight.getText().toString().matches("") && height.getText().toString().matches("") && age.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter The User Information !" , Toast.LENGTH_LONG).show();
                }
                else if(n.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Name !" , Toast.LENGTH_LONG).show();
                }
                else if(p.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Password !" , Toast.LENGTH_LONG).show();
                }
                else if(e.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Email !" , Toast.LENGTH_LONG).show();
                }
                else if(f.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Answer The First Verification Question !" , Toast.LENGTH_LONG).show();
                }
                else if(s.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Answer The Second Verification Question !" , Toast.LENGTH_LONG).show();
                }
                else if(t.matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Answer The Third Verification Question !" , Toast.LENGTH_LONG).show();
                }
                else if(weight.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Weight !" , Toast.LENGTH_LONG).show();
                }
                else if(height.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Height !" , Toast.LENGTH_LONG).show();
                }
                else if(age.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You Must Enter Your Age !" , Toast.LENGTH_LONG).show();
                }

                else if(g.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "You Must Select Your Gender !" , Toast.LENGTH_LONG).show();
                }

                else if(f.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "You Must Select First Verification Question !" , Toast.LENGTH_LONG).show();
                }

                else if(s.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "You Must Select Second Verification Question !" , Toast.LENGTH_LONG).show();
                }

                else if(t.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "You Must Select Third Verification Question !" , Toast.LENGTH_LONG).show();
                }

                else {

                    db.insertUser(n, f, fqa, s, sqa, t, tqa, p, e, g, w, h, a);

                   // Toast.makeText(getApplicationContext(), n + f+fqa+s+sqa+t+tqa+p+e+g+w+h+a, Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
