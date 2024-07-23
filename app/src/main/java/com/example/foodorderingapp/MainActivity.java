package com.example.foodorderingapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button btnsignupLP ,btnloginLP;
    EditText etPasswordLP,etemailLP;
    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;
        ProgressBar progressBar;
        DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignupLP = findViewById(R.id.btnsignupLP);
        btnloginLP = findViewById(R.id.btnloginLP);
        etPasswordLP = findViewById(R.id.etPasswordLP);
        etemailLP = findViewById(R.id.etemailLP);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnloginLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  passwordlogin,emaillogin;
                progressBar.setVisibility(View.VISIBLE);



                sharedPreferences = getSharedPreferences("logindata",MODE_PRIVATE);
                SharedPreferences.Editor  editor = sharedPreferences.edit();

                passwordlogin = etPasswordLP.getText().toString();
                emaillogin = etemailLP.getText().toString();

                if (passwordlogin.equals("") || emaillogin.equals("")) {

                    Toast.makeText(MainActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.GONE);


                    mAuth.signInWithEmailAndPassword(emaillogin, passwordlogin)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                                    editor.putString("save_email",emaillogin);
                                    editor.apply();

                                   Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(i);
                                     finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                }


            }
        });


        btnsignupLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);

            }
        });



    }


}

