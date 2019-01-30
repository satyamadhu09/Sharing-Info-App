package com.example.vidya.newproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stuLog(View view) {
        Intent i=new Intent(MainActivity.this,Stulog.class);
        startActivity(i);
    }

    public void Faclog(View view) {
        Intent i1=new Intent(MainActivity.this,Faclog.class);
        startActivity(i1);
    }
}
