package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.User;
import com.cmdev.reelmarkets.model.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    String[] genderOptions = {"Gender", "Male", "Female"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, genderOptions);
        Spinner spinner = (Spinner) findViewById(R.id.gender);
        spinner.setAdapter(arrayAdapter);

        //BELOW IS HOW TO DO THE FONT THING YAY
        //Gets the font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"SciFly-Sans.ttf");
        //Gets the TextView whose font we wanna change
        TextView registerUser = (TextView) findViewById(R.id.etRegisterUser);
        //Sets the TextView's font
        registerUser.setTypeface(myTypeface);
    }

    public void onClickRegister(View v) {
        String username = ((EditText)findViewById(R.id.etRegisterUser)).getText().toString();
        String email = ((EditText)findViewById(R.id.etEmail)).getText().toString();
        String pass = ((EditText)findViewById(R.id.etRegisterPass)).getText().toString();
        String passConf = ((EditText)findViewById(R.id.etRegisterPassConf)).getText().toString();
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = (new SimpleDateFormat("MM/dd/yyyy")).parse(((EditText)findViewById(R.id.etBirthday)).getText().toString());
            c.setTime(date);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),
                    "Invalid birthday!", Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(),
                    "Invalid birthday!", Toast.LENGTH_LONG).show();
        }
        int year = c.get(Calendar.YEAR);
        User.Gender g = User.Gender.NONE;
        int pos = ((Spinner) findViewById(R.id.gender)).getSelectedItemPosition();
        if (pos == 1) {
            g = User.Gender.MALE;
        } else if (pos == 2) {
            g = User.Gender.FEMALE;
        }

        boolean isValid = !username.equals("") && !email.equals("") && !pass.equals("") && !passConf.equals("");
        boolean isOld = year <= (c.getInstance().get(Calendar.YEAR) - 13);
        boolean passMatch = pass.equals(passConf);
        boolean isExistingUser = UserManager.isExistingUser(username);
        if (!isValid) {
            Toast.makeText(getApplicationContext(),
                    "Cannot leave username, email, or password blank", Toast.LENGTH_LONG).show();
        } else if (!isOld) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Must be 13 years or older to register.", Toast.LENGTH_LONG).show();
        } else if (!passMatch) {
            Toast.makeText(getApplicationContext(),
                    "Oops! Passwords do not match.", Toast.LENGTH_LONG).show();
        } else if (isExistingUser) {
                Toast.makeText(getApplicationContext(),
                        "Username already exists. Please choose a different one.", Toast.LENGTH_LONG).show();
        }
        else {
            User newUser = new User(username, pass, email, date.toString(), g, User.AccountType.USER);
            UserManager.addNewUser(newUser);
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }

}
