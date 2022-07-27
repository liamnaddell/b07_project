package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton register = (MaterialButton) findViewById(R.id.register);
        MaterialButton login_link = (MaterialButton) findViewById(R.id.login_link);

        Database db = DatabaseInstance.get_instance();
        db.add_user("admin","admin", true);
        register.setOnClickListener(v -> {
            if (db.check_pw(username.getText().toString(),password.getText().toString()) != null) {
                // correct password and then send them to main app interface
                Toast.makeText(SignUpPage.this, "SUCCESSFUL REGISTER", Toast.LENGTH_SHORT).show();
                goToMainPage(v);
            } else
                //failed
                Toast.makeText(SignUpPage.this, "FAILED REGISTER", Toast.LENGTH_SHORT).show();
        });
        login_link.setOnClickListener(v -> {
            Toast.makeText(SignUpPage.this, "LOGIN PAGE", Toast.LENGTH_SHORT).show();
            goToLoginPage(v);
        });

    }
    /** Called when the user clicks the Sign In button */
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}