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
import com.cmdev.reelmarkets.util.ReelMarketsRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

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
            String path = "getLoginStatus/";
            RequestParams params = new RequestParams();
            params.put("username", username);
            params.put("password", password);
            ReelMarketsRestClient.get(path, params, new JsonHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    System.out.println("######" + response);
                    try {
                        String username = response.getString("Username");
                        String email = response.getString("Email");
                        String dob = response.getString("Birthday").split("T")[0];
                        int currency = (int) response.get("Currency");
                        String gender = response.getString("Gender");
                        User.Gender g = (gender != null && !gender.equals("null"))? User.Gender.valueOf(gender) : User.Gender.NONE;
                        User.AccountType aT = User.AccountType.valueOf(response.getString("Account_Type"));

                        System.out.println(username + " " + email + " " +  dob
                                + " " + currency+ " " +  gender+ " " +  g+ " " +  aT);

                        User user = new User(username, email, dob, currency, g, aT);
                        LoginSession.currentUser = user;
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.println("Status Code = " + statusCode);
                    System.out.println("responseString = " + responseString);
                    if (statusCode == 200) {
                        Toast.makeText(getApplicationContext(),
                                "Incorrect credentials!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Could not connect to database!", Toast.LENGTH_LONG).show();
                    }
                }
            });


            /*
            ** This is to access user info stored locally in UserManager.
            *
            User userInfo = UserManager.getUser(username);
            if (userInfo != null && userInfo.Password.equals(password)) {
                LoginSession.currentUser = userInfo;
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Invalid credentials", Toast.LENGTH_LONG).show();
            }
            */
        }

    }
    public void onClickSwitchToRegister(View v) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
