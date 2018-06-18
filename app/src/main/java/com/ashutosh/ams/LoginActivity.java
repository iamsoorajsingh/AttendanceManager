package com.ashutosh.ams;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText u_id;
    TextInputEditText pass;
    Button login;
    TextView no_account;
    TextInputLayout login_uid, login_password;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db =new DBHelper(this);
        u_id=findViewById(R.id.login_uid);
        pass=findViewById(R.id.login_password);
        login=findViewById(R.id.login);
        no_account=findViewById(R.id.no_account);
        login_uid = findViewById(R.id.layout_login_uid);
        login_password = findViewById(R.id.layout_login_password);

        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_id = u_id.getText().toString();
                String login_pass = pass.getText().toString();

                login_id=login_id.toLowerCase();

                if(login_id.isEmpty())
                    login_uid.setError("Enter a valid UID");
                else
                    login_uid.setError(null);

                if (login_pass.isEmpty())
                    login_password.setError("Enter a Password");
                else
                    login_password.setError(null);

                if (!login_id.isEmpty() && !login_pass.isEmpty())
                {
                    Cursor cursor=db.check_login(login_id ,login_pass);
                    if(cursor!=null)
                    {
                        if(cursor.getCount() > 0)
                        {
                            cursor.moveToNext();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"User Data doesn't exist",Toast.LENGTH_SHORT).show();
                    }

//                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        });

    }
}
