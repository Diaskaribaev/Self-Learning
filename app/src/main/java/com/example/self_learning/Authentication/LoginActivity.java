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

import com.example.self_learning.MainActivity;
import com.example.self_learning.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText username, passwor;
    Button btn_login;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.Username);
        passwor = findViewById(R.id.Password);
        btn_login = findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();





        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ( username.getText().toString().length() <= 0  ) {
                    Toast.makeText(
                          LoginActivity.this ,"Please enter email",Toast.LENGTH_SHORT

                    ).show();
                }

                if(passwor.getText().toString().length()<= 0){
                    Toast.makeText(
                            LoginActivity.this ,"Please enter password",Toast.LENGTH_SHORT

                    ).show();
                }

                         String email = username.getText().toString();
                         String password = passwor.getText().toString();

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(
                                                @NonNull Task<AuthResult> task)
                                        {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Login successful!!",
                                                        Toast.LENGTH_SHORT)
                                                        .show();
                                                openMainActivity();
                                            }

                                            else {

                                                // sign-in failed
                                                Toast.makeText(getApplicationContext(),
                                                        "Login failed!!",
                                                        Toast.LENGTH_LONG)
                                                        .show();


                                            }
                                        }
                                    });

                }


        });


    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void RegisterPage(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);




    }
}