package com.example.lenovo.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText first_name,pwd;
    Button login,signup;
    TextView logform;
    MydataBase mydataBase;
    SQLiteDatabase database;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_name=(EditText)findViewById(R.id.et_id_main);
        pwd=(EditText)findViewById(R.id.et_pw_main);
        login=(Button)findViewById(R.id.btn_login_main);
        signup=(Button)findViewById(R.id.btn_cA_main);
        logform=(TextView)findViewById(R.id.tv_logF_main);


        mydataBase=new MydataBase(getApplicationContext());
        database=mydataBase.getReadableDatabase();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
            }
        });

    }
    public void save(){
        String name = first_name.getText().toString();
        String password = pwd.getText().toString();
//            //shared preferences
//                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
//                SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("saved", true); // Storing boolean - true/false
        editor.putString("_name", name); // Storing string
        editor.putString("_pwd",password ); // Storing integer
        editor.commit();
//                editor.putString("_names",name);
//                editor.putString("_passwords", String.valueOf(password));
//                editor.commit();


//                String _names=sp.getString("_names","");
//                String _passwords=sp.getString("_passwords", "");
//
//                Intent intent = new Intent(getApplicationContext(),Activity1.class);
//                startActivity(intent);
//
//                Toast.makeText(getApplicationContext(),_names+":"+_passwords,Toast.LENGTH_LONG).show();
////                Snackbar.make(v,_names+":"+_passwords,Snackbar.LENGTH_LONG).show();
//
//                first_name.setText("");
//                pwd.setText("");

        cursor = database.rawQuery("SELECT *FROM " + mydataBase.TABLE_NAME + " WHERE " + mydataBase.COL_2 + "=? AND " + mydataBase.COL_5 + "=?", new String[]{name, password});
        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
            String _fname = cursor.getString(cursor.getColumnIndex(mydataBase.COL_2));
            String _email = cursor.getString(cursor.getColumnIndex(mydataBase.COL_5));
            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Activity1.class);
            intent.putExtra("fullname", _fname);
            intent.putExtra("email", _email);
            startActivity(intent);

            //Removing MainActivity[Login Screen] from the stack for preventing back button press.
            finish();
        } else {

            Toast.makeText(getApplicationContext(),"name and password not matching",Toast.LENGTH_SHORT).show();

        }
    }
}
