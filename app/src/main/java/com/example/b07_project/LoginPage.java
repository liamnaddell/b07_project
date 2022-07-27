package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton signing = (MaterialButton) findViewById(R.id.signing);
        MaterialButton new_user = (MaterialButton) findViewById(R.id.new_user);

        // using just admin for now; but need to incorporate Firebase DB
        Database db = DatabaseInstance.get_instance();
        db.add_user("admin","admin",true);
        signing.setOnClickListener(v -> {
            if (db.check_pw(username.getText().toString(),password.getText().toString()) != null) {
                // correct password and then send them to main app interface
                Toast.makeText(LoginPage.this, "SUCCESSFUL LOGIN", Toast.LENGTH_SHORT).show();
                goToMainPage(v);
            } else
                //failed
                Toast.makeText(LoginPage.this, "FAILED LOGIN", Toast.LENGTH_SHORT).show();
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
    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }

}