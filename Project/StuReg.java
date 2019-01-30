package com.example.vidya.newproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StuReg extends AppCompatActivity {

    EditText uname, uregdno, uemail, uyear, ubranch, upass;
    private FirebaseAuth auth;
    //Button button_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_reg);

        uname = (EditText) findViewById(R.id.uname);
        uregdno = (EditText) findViewById(R.id.uregdno);
        uemail = (EditText) findViewById(R.id.uemail);
        uyear = (EditText) findViewById(R.id.uyear);
        ubranch = (EditText) findViewById(R.id.ubranch);
        upass = (EditText) findViewById(R.id.upass);

        //findViewById(R.id.button_reg).setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }
    private void registerUser() {

        String name = uname.getText().toString().trim();
        String email = uemail.getText().toString().trim();
        String regdno = uregdno.getText().toString().trim();
        String year = uyear.getText().toString().trim();
        String branch = ubranch.getText().toString().trim();
        String password = upass.getText().toString().trim();

        if (name.isEmpty()) {
            uname.setError("Name is required");
            uname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            uemail.setError("Email is required");
            uemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            uemail.setError("Please enter a valid email");
            uemail.requestFocus();
            return;
        }
        if (regdno.isEmpty()) {
            uregdno.setError("RegdNo is required");
            uregdno.requestFocus();
            return;
        }
        if (year.isEmpty()) {
            uyear.setError("Year is required");
            uyear.requestFocus();
            return;
        }
        if (branch.isEmpty()) {
            ubranch.setError("Branch is required");
            ubranch.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            upass.setError("Password is required");
            upass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            upass.setError("Minimum lenght of password should be 6");
            upass.requestFocus();
            return;
        }


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(StuReg.this, "Registered", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(StuReg.this,Stulog.class));
                }
                else{
                    Toast.makeText(StuReg.this, "Failed", Toast.LENGTH_SHORT).show();
                }

                }

        });

    }


    /*@Override
    public void onClick(View v) {
        registerUser();
    }*/

    public void stbuttonReg(View view) {

        registerUser();
    }
}
