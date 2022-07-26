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

        // using just admin for now; but need to incorporate Firebase DB
        MockDB db = new MockDB();
        db.add_user("admin","admin");
        signing.setOnClickListener(v -> {
            if (db.check_pw(username.getText().toString(),password.getText().toString())) {
                // correct password and then send them to main app interface
                Toast.makeText(LoginPage.this, "SUCCESSFUL LOGIN", Toast.LENGTH_SHORT).show();
                goToMainPage(v);
            } else
                //failed
                Toast.makeText(LoginPage.this, "FAILED LOGIN", Toast.LENGTH_SHORT).show();
        });
    }
    /** Called when the user clicks the Sign In button */
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}