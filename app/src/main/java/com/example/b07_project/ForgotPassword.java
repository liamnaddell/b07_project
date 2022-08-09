package com.example.b07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import java.util.regex.*;


public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextView email = (TextView) findViewById(R.id.email);
        MaterialButton send = (MaterialButton) findViewById(R.id.send);
        MaterialButton login = (MaterialButton) findViewById(R.id.login);
        FirebaseAuth f;
        f = FirebaseAuth.getInstance();
        login.setOnClickListener(v -> {
            Toast.makeText(ForgotPassword.this, "LOGIN PAGE", Toast.LENGTH_SHORT).show();
            goToLogInPage(v);
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                if(TextUtils.isEmpty(mail)) {
                    email.setError("Email field cannot be empty.");
                    return;
                }

                //Email Format Check Using Regex
                Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+.)+[\\w]{2,4}$");
                Matcher m = p.matcher(mail);
                if(!m.matches()) {
                    email.setError("Invalid Email format. Please try again.");
                    return;
                }

                f.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                            goToLogInPage(v);
                        }
                        else {
                            Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void goToLogInPage(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}