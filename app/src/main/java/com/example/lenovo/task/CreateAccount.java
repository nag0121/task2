package com.example.lenovo.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by LENOVO on 15-11-2017.
 */

public class CreateAccount extends AppCompatActivity {

    EditText _firstname,_lastname,mobile,password;

    RadioGroup gender;

    Button signup;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        _firstname = (EditText)findViewById(R.id.et_fn_create);
        _lastname = (EditText)findViewById(R.id.et_ln_create);
        mobile = (EditText)findViewById(R.id.et_phone_create);
        password = (EditText)findViewById(R.id.et_pw_create);

        signup=(Button)findViewById(R.id.btn_cA_create);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String firstname = _firstname.getText().toString();
                    String secondname = _lastname.getText().toString();
                    int mblno = Integer.parseInt(mobile.getText().toString());
                    String passwords = password.getText().toString();


                    MydataBase database = new MydataBase(CreateAccount.this);

                    database.insertData(firstname, secondname, mblno, passwords);

                    Toast.makeText(getApplicationContext(),"insert success", Toast.LENGTH_LONG).show();

                    _firstname.setText("");
                    _lastname.setText("");
                    mobile.setText("");
                    password.setText("");

                    Intent intent = new Intent(CreateAccount.this,MainActivity.class);
                    startActivity(intent);

                }

                    catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }
}
