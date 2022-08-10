package com.example.b07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import java.util.regex.*;


public class SignUpPage extends AppCompatActivity{
    Database db;
    MaterialButton registerButton,loginPageButton;
    EditText editUserName, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        Database db = DatabaseInstance.get_instance();
        editUserName = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);
        loginPageButton = (MaterialButton) findViewById(R.id.login_link);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userString = editUserName.getText().toString().trim();
                String passString = editPassword.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                System.out.println("Adding user start");
                /*if( db.logged_in() != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }*/
                if(userString.isEmpty()) {
                    editUserName.setError("Email field cannot be empty.");
                    editUserName.requestFocus();
                    return;
                }
                Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+.)+[\\w]{2,4}$");
                Matcher m = p.matcher(userString);
                if(!m.matches()) {
                    editUserName.setError("Improper email format given.");
                    editUserName.requestFocus();
                    return;
                }
                if(passString.isEmpty()) {
                    editPassword.setError("Password field cannot be empty.");
                    editPassword.requestFocus();
                    return;
                }
                if(passString.length() < 6) {
                    editPassword.setError("Your password is too short.");
                    editPassword.requestFocus();
                    return;
                }
                System.out.println("added user to db");
                boolean res = db.add_user(userString,passString,false);
                if (res == true) {
                    mAuth.createUserWithEmailAndPassword(userString, passString);
                    mAuth.signInWithEmailAndPassword(userString, passString);
                    Toast.makeText(SignUpPage.this, "User Created", Toast.LENGTH_SHORT).show();
                    goToMainPage(view);
                } else {
                    Toast.makeText(SignUpPage.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                }
                    //Toast.makeText(SignUpPage.this, "Your email has already used.", Toast.LENGTH_SHORT).show();
                    //goToLoginPage(view);
            }
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