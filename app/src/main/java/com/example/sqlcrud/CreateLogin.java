package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateLogin extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword, tilemailId, tilphoneNumber;
    private TextInputEditText username, password, emailId, phoneNumber;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);

        tilUsername = findViewById(R.id.til_createUsername);
        tilPassword = findViewById(R.id.til_createPassword);
        tilemailId = findViewById(R.id.til_createEmail);
        tilphoneNumber = findViewById(R.id.til_createPhoneNum);

        username = findViewById(R.id.tie_createUsername);
        password = findViewById(R.id.tie_createPassword);
        emailId = findViewById(R.id.tie_createEmail);
        phoneNumber = findViewById(R.id.tie_createPhoneNum);

        allListeners();
    }

    private void allListeners() {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (!username.getText().toString().equals("")){
                        tilUsername.setHelperText(null);
                    }
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilPassword.setHelperText(validPassword());
                }
            }
        });

        emailId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilemailId.setHelperText(validEmail());
                }
            }
        });

        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilphoneNumber.setHelperText(validPhoneNumber());
                }
            }
        });
    }

    private CharSequence validPhoneNumber() {
        String phoneNumText = phoneNumber.getText().toString();
        if (!phoneNumText.matches(".*[0-9].*")) {
            return "Must be all Digits";
        }if (phoneNumText.length() != 10) {
            return "Must be 10 Digits";
        }
        return null;
    }

    private String validEmail() {
        String emailText = emailId.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email address";
        }
        return null;
    }

    private String validPassword() {
        String passwordText = password.getText().toString();
        if (passwordText.length() < 8){
            return "Minimum 8 character Password";
        }if (!passwordText.matches(".*[A-Z].*")){
            return "Must Contain 1 Upper-case Character";
        }if(!passwordText.matches(".*[a-z].*")){
            return "Must Contain 1 Lower-case Character";
        }if(!passwordText.matches(".*[@#$%^&+=].*")){
            return "Must Contain 1 Special Character (@#$%^&+=)";
        }
        return null;
    }


}