package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {
    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText tieUsername, tiePassword;
    private Button btnAdminLogin;
    private TextView tvNoOfAttempt;
    int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        initObj();
        allListeners();

    }

    private void initObj() {
        tilUsername = findViewById(R.id.til_adminUser);
        tilPassword = findViewById(R.id.til_adPassword);
        tieUsername = findViewById(R.id.tie_adminUser);
        tiePassword = findViewById(R.id.tie_adPassword);
        btnAdminLogin = findViewById(R.id.btn_adLogin);
        tvNoOfAttempt = findViewById(R.id.tv_attempts);

    }

    private void allListeners() {
        tieUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    tilUsername.setHelperText(validUsername());
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

        tiePassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if((keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                    tilPassword.setHelperText(validPassword());
                    return true;

                }
                return false;
            }
        });

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tieUsername.getText().toString().equals("admin") && tiePassword.getText().toString().equals("123")) {
                    Toast.makeText(AdminLogin.this, "Admin login successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(AdminLogin.this, ));
                } else {
                    counter--;
                    Toast.makeText(AdminLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    tvNoOfAttempt.setText(getString(R.string.Attempt) + counter);
                    if (counter == 0){
                        btnAdminLogin.setEnabled(false);
                    }

                }
            }
        });

    }
    private String validUsername(){
        if (tieUsername.getText().toString().equals("") || !tieUsername.getText().toString().equals("admin")){
            return "Invalid admin name";
        }
        return null;
    }

    private String validPassword(){
        if (tiePassword.getText().toString().equals("") || tiePassword.getText().toString().equals("123")){
            return "Invalid password";
        }
        return null;
    }

}