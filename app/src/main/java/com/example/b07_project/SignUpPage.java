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
    FirebaseFirestore db;
    MaterialButton registerButton,loginPageButton;
    EditText editUserName, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        db = FirebaseFirestore.getInstance();
        editUserName = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);
        loginPageButton = (MaterialButton) findViewById(R.id.login_link);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userString = editUserName.getText().toString().trim();
                String passString = editPassword.getText().toString().trim();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                if(mAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
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
                User newUser = new User(userString, passString, false);
                FirebaseDB database = new FirebaseDB();
                User testUser = database.find_user_by_name(userString);
                // if find_user_by_name fails, null is returned
                if (testUser == null) {
                    db.collection("users")
                            .add(newUser)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    mAuth.createUserWithEmailAndPassword(userString, passString);
                                    mAuth.signInWithEmailAndPassword(userString, passString);
                                    Toast.makeText(SignUpPage.this, "User Created", Toast.LENGTH_SHORT).show();
                                    goToMainPage(view);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpPage.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(SignUpPage.this, "Your email has already used.", Toast.LENGTH_SHORT).show();
                    goToLoginPage(view);
                }
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