package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlcrud.data.MyDbHandler;
import com.example.sqlcrud.model.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateLogin extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword, tilemailId, tilphoneNumber;
    private TextInputEditText username, password, emailId, phoneNumber;
    private Button btnRegister;
    MyDbHandler myDbHandler;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);

        initObj();
        allListeners();

    }

    private void initObj() {
        tilUsername = findViewById(R.id.til_createUsername);
        tilPassword = findViewById(R.id.til_createPassword);
        tilemailId = findViewById(R.id.til_createEmail);
        tilphoneNumber = findViewById(R.id.til_createPhoneNum);

        username = findViewById(R.id.tie_createUsername);
        password = findViewById(R.id.tie_createPassword);
        emailId = findViewById(R.id.tie_createEmail);
        phoneNumber = findViewById(R.id.tie_createPhoneNum);
        btnRegister = findViewById(R.id.btn_register);

        myDbHandler = new MyDbHandler(CreateLogin.this);
        users = new Users();
   }

    private void allListeners() {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilUsername.setHelperText(validUsername());
                }
            }
        });
/*
        username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    btnRegister.setEnabled(validUsername()== null && validPassword()== null && validEmail()== null && validPhoneNumber()==null);
                }else{
                    btnRegister.setEnabled(false);
                }
                return false;
            }
        });
*/
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilPassword.setHelperText(validPassword());

                }
            }
        });
/*
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    btnRegister.setEnabled(validUsername()== null && validPassword()== null && validEmail()== null && validPhoneNumber()==null);
                }else{
                    btnRegister.setEnabled(false);
                }
                return false;
            }
        });
*/

        emailId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilemailId.setHelperText(validEmail());
                }
            }
        });

        /*
        emailId.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    btnRegister.setEnabled(validUsername()== null && validPassword()== null && validEmail()== null && validPhoneNumber()==null);
                }else{
                    btnRegister.setEnabled(false);
                }
                return false;
            }
        });
        */
//        tilphoneNumber.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (i == 10){
//
//                }
//                return false;
//            }
//        });

        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    tilphoneNumber.setHelperText(validPhoneNumber());
//                    btnRegister.setEnabled(phoneNumber.getText().length() == 10);
//                    btnRegister.requestFocus();
                }
            }

        });
/*
        phoneNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    btnRegister.setEnabled(validUsername()== null && validPassword()== null && validEmail()== null && validPhoneNumber()==null);
                }else{
                    btnRegister.setEnabled(false);
                }
                return false;
            }
        });
*/
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validUsername()== null && validPassword()== null && validEmail()== null && validPhoneNumber()==null){
                    postDataToSqlite();

                }
            }
        });
    }

    private void postDataToSqlite() {
        if (!myDbHandler.checkUser(username.getText().toString().trim())){
            users.setName(username.getText().toString());
            users.setPassword(password.getText().toString());
            users.setEmail(emailId.getText().toString());
            users.setPhoneNum(phoneNumber.getText().toString());

            myDbHandler.AddUser(users);
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            emptyInputFields();
            startActivity(new Intent(CreateLogin.this, LoginPage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }else{
            username.requestFocus();
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputFields() {
        username.setText("");
        password.setText("");
        emailId.setText("");
        phoneNumber.setText("");
    }


    private String validUsername(){
        if (username.getText().toString().equals("")){
            return "Invalid username";
        }
        return null;
    }
    private String validPhoneNumber() {
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