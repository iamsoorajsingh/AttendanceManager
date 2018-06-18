package com.ashutosh.ams;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText fname,lname,e_mail,phone,u_id,repass;
    TextInputEditText pass;

    Button submit;

    TextView already_account;

    TextInputLayout lfname,llname,lemail,lphone,luid,lrepass,lpass;

    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        e_mail = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        u_id = findViewById(R.id.uid);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.re_password);
        submit = findViewById(R.id.register);
        already_account =findViewById(R.id.already_registered);
        lpass = findViewById(R.id.layout_password);
        lfname = findViewById(R.id.layout_first_name);
        llname = findViewById(R.id.layout_last_name);
        lemail = findViewById(R.id.layout_email);
        lphone = findViewById(R.id.layout_phone);
        luid = findViewById(R.id.layout_uid);
        lrepass = findViewById(R.id.layout_re_password);
        db=new DBHelper(this);

        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first_name=fname.getText().toString();
                final String last_name=lname.getText().toString();
                final String email=e_mail.getText().toString();
                final String ph_no=phone.getText().toString();
                String uid=u_id.getText().toString();
                final String password=pass.getText().toString();
                final String re_pass=repass.getText().toString();
                ContentValues contentValues=new ContentValues();

                uid=uid.toLowerCase();

                if(first_name.isEmpty())
                    lfname.setError("First Name cannot be Empty");
                else {
                    lfname.setError(null);
                    lfname.clearFocus();
                }

                if (last_name.isEmpty())
                    llname.setError("Last Name cannot be Empty");
                else {
                    llname.setError(null);
                    llname.clearFocus();
                }
                if (email.isEmpty())
                    lemail.setError("Email Required");
                else {
                    lemail.setError(null);
                    lemail.clearFocus();
                    if(!validEmail(email))
                        lemail.setError("Enter a valid email");
                    else {
                        lemail.setError(null);
                        lemail.clearFocus();
                    }
                }

                if(ph_no.isEmpty())
                    lphone.setError("Phone Required");
                else {
                    lphone.setError(null);
                    lphone.clearFocus();
                    if(ph_no.length()<10)
                        lphone.setError("Enter a Valid Phone Number");
                    else {
                        lphone.setError(null);
                        lphone.clearFocus();
                    }
                }

                if (uid.isEmpty())
                    luid.setError("UID Required");
                else
                    luid.setError(null);

                if (password.isEmpty())
                    lpass.setError("Password Required");
                else {
                    lpass.setError(null);
                    lpass.clearFocus();
                    if (!re_pass.equals(password)) {
                        lrepass.setError("Passwords don't match");
                    }
                    else {
                        lrepass.setError(null);
                        lrepass.clearFocus();
                    }
                }

                if (!first_name.isEmpty() && !last_name.isEmpty() && !email.isEmpty() && !ph_no.isEmpty() && !uid.isEmpty() && !password.isEmpty() && re_pass.equals(password) && validEmail(email))
                {
                    contentValues.put("user_fname",first_name);
                    contentValues.put("user_lname",last_name);
                    contentValues.put("user_email",email);
                    contentValues.put("user_phone",ph_no);
                    contentValues.put("user_uid",uid);
                    contentValues.put("user_pass",password);
                    db.insert_userData("registration",contentValues);
                    Toast.makeText(RegistrationActivity.this, "Details Saved. Login  to Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validEmail(String email)
    {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
