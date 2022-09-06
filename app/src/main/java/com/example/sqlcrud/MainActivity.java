
package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnAdmin, btnLogin;
    private TextView tvCreateUser;
    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText tieUsername, tiePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObj();
        allListeners();
    }

    private void initObj() {
        tilUsername = findViewById(R.id.til_username);
        tilPassword = findViewById(R.id.til_password);
        btnAdmin = findViewById(R.id.btn_adminLogin);
        btnLogin = findViewById(R.id.btn_login);
        tvCreateUser = findViewById(R.id.tv_linkRegister);
        tieUsername = findViewById(R.id.tie_username);
        tiePassword = findViewById(R.id.tie_password);
    }

    private void allListeners() {
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLogin.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ));
            }
        });
        tvCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateLogin.class));
            }
        });

        tieUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if (!tieUsername.getText().toString().equals("")){
                        tilUsername.setHelperText(null);
                    }
                }
            }
        });
        tiePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    tilPassword.setHelperText(validPassword());
                }
            }
        });
    }
    private String validPassword() {
        String passwordText = tiePassword.getText().toString();
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