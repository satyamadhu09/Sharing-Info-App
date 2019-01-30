package com.example.vidya.newproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FacReg extends AppCompatActivity {

    EditText fname,  fmail, fbranch, fpass;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_reg);

        fname = (EditText) findViewById(R.id.fname);
        fmail = (EditText) findViewById(R.id.fmail);
        fbranch =(EditText)findViewById(R.id.fbranch);
        fpass=(EditText)findViewById(R.id.fpass);

        auth = FirebaseAuth.getInstance();
    }
    private void registerUser() {

        String name = fname.getText().toString().trim();
        String email = fmail.getText().toString().trim();
        String branch = fbranch.getText().toString().trim();
        String password = fpass.getText().toString().trim();

        if (name.isEmpty()) {
            fname.setError("Name is required");
            fname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            fmail.setError("Email is required");
            fmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            fmail.setError("Please enter a valid email");
            fmail.requestFocus();
            return;
        }

        if (branch.isEmpty()) {
            fbranch.setError("Branch is required");
            fbranch.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            fpass.setError("Password is required");
            fpass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            fpass.setError("Minimum length of password should be 6");
            fpass.requestFocus();
            return;
        }


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(FacReg.this, "Registered", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(FacReg.this,Faclog.class));
                }
                else{
                    Toast.makeText(FacReg.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void fbuttonReg(View view) {

        registerUser();
    }
}
