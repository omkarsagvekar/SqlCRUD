
package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlcrud.data.MyDbHandler;
import com.example.sqlcrud.params.Params;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnAdmin, btnLogin;
    private TextView tvCreateUser;
    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText tieUsername, tiePassword;
    MyDbHandler myDbHandler;
    public static final String MYSHAREDPREF = "MySharedPref";
    SharedPreferences sharedPreferences;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        initObj();
        allListeners();

        sharedPreferences = getSharedPreferences(WelcomeLoginPage.MyPREFERENCES, MODE_PRIVATE);
        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
        if (hasLoggedIn){
            startActivity(new Intent(MainActivity.this, WelcomeLoginPage.class));
            finish();
        }

    }

    private void initObj() {
        tilUsername = findViewById(R.id.til_username);
        tilPassword = findViewById(R.id.til_password);
        btnAdmin = findViewById(R.id.btn_adminLogin);
        btnLogin = findViewById(R.id.btn_login);
        tvCreateUser = findViewById(R.id.tv_linkRegister);
        tieUsername = findViewById(R.id.tie_username);
        tiePassword = findViewById(R.id.tie_password);
        myDbHandler = new MyDbHandler(this);
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
                if (myDbHandler.checkUser(tieUsername.getText().toString(), tiePassword.getText().toString())){
//                    sharedPreferences.edit().putBoolean("hasLoggedIn", true).apply();
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, WelcomeLoginPage.class));
                    finish();

                }else{
                    Toast.makeText(MainActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyInputFields();
                tieUsername.requestFocus();
                startActivity(new Intent(MainActivity.this, CreateLogin.class));
            }
        });

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
//                    btnLogin.requestFocus();
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
    }

    private void emptyInputFields() {
        tieUsername.setText("");
        tiePassword.setText("");
    }

    private String validUsername(){
        if (tieUsername.getText().toString().equals("")){
            return "Invalid username";
        }
        return null;
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