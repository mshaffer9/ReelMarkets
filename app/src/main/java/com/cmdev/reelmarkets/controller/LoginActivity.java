package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;
import com.cmdev.reelmarkets.model.User;
import com.cmdev.reelmarkets.model.UserManager;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void onClickConfirmLogin(View v) {
        Log.d("LoginActivity", "Attempting to login...");
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        Log.d("LoginActivity", "Username text: " + username + " Password text: " + password);

        boolean isValid = (!username.equals("") && !password.equals(""));
        if (!isValid) {
            Toast.makeText(getApplicationContext(),
                    "You cannot leave fields blank!", Toast.LENGTH_LONG).show();
        } else {
            User userInfo = UserManager.getUser(username);
            if (userInfo != null && userInfo.Password.equals(password)) {
                LoginSession.currentUser = userInfo;
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Invalid credentials", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onClickSwitchToRegister(View v) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
