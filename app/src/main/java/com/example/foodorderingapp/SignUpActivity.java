package com.example.foodorderingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity {

    Button btnloginRP,btnsignupRP;
    EditText etfullnameRP,etemailRP,etphoneRP,etPasswordRP;
   FirebaseAuth mAuth;
   ProgressBar progressBar;
   DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();
//         Check if user is signed in (non-null) and update UI accordingly.
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
        setContentView(R.layout.activity_sign_up);

        btnloginRP = findViewById(R.id.btnloginRP);
        btnsignupRP = findViewById(R.id.btnsignupRP);
        etfullnameRP = findViewById(R.id.etfullnameRP);
        etemailRP = findViewById(R.id.etemailRP);
        etphoneRP = findViewById(R.id.etphoneRP);
        etPasswordRP = findViewById(R.id.etPasswordRP);
        progressBar = findViewById(R.id.progressbar);

         mAuth = FirebaseAuth.getInstance();
         databaseReference = FirebaseDatabase.getInstance().getReference();


        btnloginRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


        btnsignupRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);

                String name = etfullnameRP.getText().toString();
                String email = etemailRP.getText().toString();
                String phone = etphoneRP.getText().toString();
                String password = etPasswordRP.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("userdata",MODE_PRIVATE);
                SharedPreferences.Editor  editor = sharedPreferences.edit();


                if(name.equals("") || email.equals("") || phone.equals("") || password.equals("") ){
                    Toast.makeText(SignUpActivity.this,"Please Enter Data",Toast.LENGTH_SHORT).show();
                }
                else{


                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                                        HashMap<String,Object> userdataMap = new HashMap<>();
                                        userdataMap.put("fullname",name);
                                        userdataMap.put("email",email);
                                        userdataMap.put("password",password);

                                        databaseReference.child("users").child(phone).updateChildren(userdataMap);

                                        editor.putString("Name",name);
                                        editor.putString("Email",email);
                                        editor.putString("Phone",phone);
                                        editor.putBoolean("login",true);
                                        editor.apply();

                                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(i);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                }


//


            }
        });


    }

}