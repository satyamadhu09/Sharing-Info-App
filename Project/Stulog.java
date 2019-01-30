package com.example.vidya.newproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Stulog extends AppCompatActivity {

    private EditText stusername,stpass;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stulog);
        auth = FirebaseAuth.getInstance();

        stusername= (EditText) findViewById(R.id.stusername);
        stpass = (EditText) findViewById(R.id.stpass);

    }
    private void validate(String stusername,String stpass){
        check();

        auth.signInWithEmailAndPassword(stusername, stpass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Intent intent = new Intent(Stulog.this,ViewUploads.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void buttonlog(View view) {
        check();
        if(check()){
            validate(stusername.getText().toString(),stpass.getText().toString());
        }
    }

    public void stuRegister(View view) {
        startActivity(new Intent(this, StuReg.class));
    }
    private boolean check()
    {
        Boolean result=false;
        String email=stusername.getText().toString();
        String password=stpass.getText().toString();
        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return  result;
    }

}
