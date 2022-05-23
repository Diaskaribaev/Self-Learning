package com.example.self_learning.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.self_learning.R;
import com.example.self_learning.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText name,surname,email, passwor;
    Button btn_signup,btn_gotologin;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ActivityRegisterBinding binding;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityRegisterBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(binding.getRoot());


        email = findViewById(R.id.Email);
        passwor = findViewById(R.id.Password);
        btn_signup = findViewById(R.id.signup);
        btn_gotologin = findViewById(R.id.loginPage);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerNewUser();
            }
        });

        btn_gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });





    }





    private void registerNewUser(){



        String emaill, password;
        emaill = email.getText().toString().trim();
        password = passwor.getText().toString().trim();



        if ( email.getText().toString().length() <= 0  ) {
            Toast.makeText(
                    RegisterActivity.this ,"Please enter email",Toast.LENGTH_SHORT

            ).show();
        }

        if(passwor.getText().toString().length()<= 0){
            Toast.makeText(
                    RegisterActivity.this ,"Please enter password",Toast.LENGTH_SHORT

            ).show();
        }

       mAuth.createUserWithEmailAndPassword(emaill,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {

               if (task.isSuccessful()){
                   User user = new User(binding.name.getText().toString(),binding.surname.getText().toString(),emaill,password);
                   String id = task.getResult().getUser().getUid();
                   database.getReference().child("Users").child(id).setValue(user);
                   Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

               }

           }
       });




    }






}
