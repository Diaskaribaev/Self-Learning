package com.example.self_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.self_learning.Authentication.LoginActivity;
import com.example.self_learning.Authentication.RegisterActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);



    }


    public void StartApp(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}