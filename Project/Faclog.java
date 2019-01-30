package com.example.vidya.newproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Faclog extends AppCompatActivity {

    private EditText fusername,fpass;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faclog);

        fusername= (EditText) findViewById(R.id.fusername);
        fpass = (EditText) findViewById(R.id.fpass);
        auth=FirebaseAuth.getInstance();
    }
    private void validate(String fusername,String fpass) {
        check();

        auth.signInWithEmailAndPassword(fusername, fpass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Intent intent = new Intent(Faclog.this,FacAction.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        public void facRegister(View view) {
        startActivity(new Intent(this, FacReg.class));
    }

    public void fbuttonlog(View view) {
        check();
        if(check()){
            validate(fusername.getText().toString(),fpass.getText().toString());
        }
    }
    private boolean check()
    {
        Boolean result=false;
        String email=fusername.getText().toString();
        String password=fpass.getText().toString();
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
