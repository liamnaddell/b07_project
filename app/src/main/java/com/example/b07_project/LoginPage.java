package com.example.b07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextView username =  findViewById(R.id.username);
        TextView password =  findViewById(R.id.password);
        MaterialButton signing = findViewById(R.id.signing);
        MaterialButton password_reset = findViewById(R.id.password_reset);
        MaterialButton new_user = findViewById(R.id.new_user);
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(TextUtils.isEmpty(user)) {
                    username.setError("Email field cannot be empty.");
                    username.requestFocus();
                    return;
                }
                Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+.)+[\\w]{2,4}$");
                Matcher m = p.matcher(user);
                if(!m.matches()) {
                    username.setError("Improper Email format.");
                    username.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    password.setError("Password field cannot be empty.");
                    password.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            goToMainPage(v);
                        } else {
                            Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        password_reset.setOnClickListener(v -> {
            Toast.makeText(LoginPage.this, "FORGOT PASSWORD PAGE", Toast.LENGTH_SHORT).show();
            goToForgotPasswordPage(v);
        });
        new_user.setOnClickListener(v -> {
            Toast.makeText(LoginPage.this, "LOGIN PAGE", Toast.LENGTH_SHORT).show();
            goToSignUpPage(v);
        });
    }

    /** Called when the user clicks the Sign In button */
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToForgotPasswordPage(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }
    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }
}