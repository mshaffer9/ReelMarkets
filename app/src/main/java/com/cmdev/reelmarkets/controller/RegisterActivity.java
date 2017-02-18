package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.User;
import com.cmdev.reelmarkets.model.UserManager;
import com.cmdev.reelmarkets.util.ReelMarketsRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    String[] genderOptions = {"Gender", "Male", "Female"};

    EditText date;

    TextWatcher tw = new TextWatcher() {
        private String current = "";
        private String mmddyyyy = "MMDDYYYY";
        private Calendar cal = Calendar.getInstance();

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8){
                    clean = clean + mmddyyyy.substring(clean.length());
                }else{
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    if(mon > 12) mon = 12;
                    cal.set(Calendar.MONTH, mon-1);
                    year = (year<1900)?1900:(year>2100)?2100:year;
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d",day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                date.setText(current);
                date.setSelection(sel < current.length() ? sel : current.length());
            }
        }
    };

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

        date = (EditText)findViewById(R.id.etBirthday);
        date.addTextChangedListener(tw);
    }

    public void onClickRegister(View v) {
        String username = ((EditText)findViewById(R.id.etRegisterUser)).getText().toString();
        String email = ((EditText)findViewById(R.id.etEmail)).getText().toString();
        String pass = ((EditText)findViewById(R.id.etRegisterPass)).getText().toString();
        String passConf = ((EditText)findViewById(R.id.etRegisterPassConf)).getText().toString();
        Calendar c = Calendar.getInstance();
        Date date = null;
        String parsedDate = "";
        try {
            date = (new SimpleDateFormat("MM/dd/yyyy")).parse(((EditText)findViewById(R.id.etBirthday)).getText().toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            parsedDate = formatter.format(date);
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

        }
        /*else if (isExistingUser) {
                Toast.makeText(getApplicationContext(),
                        "Username already exists. Please choose a different one.", Toast.LENGTH_LONG).show();
        }
        */
        else {
            String path = "userRegistration/";
            RequestParams params = new RequestParams();
            params.put("username", username);
            params.put("password", pass);
            params.put("email", email);
            params.put("birthday", parsedDate);
            params.put("gender", g);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String creation_date = df.format(new Date());
            params.put("creation_date", creation_date);

            ReelMarketsRestClient.post(path, params, new JsonHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.println("Status Code = " + statusCode);
                    System.out.println("responseString = " + responseString);
                    if (statusCode == 200) {
                        if (responseString.equals("Exists")) {
                            Toast.makeText(getApplicationContext(),
                                    "Username already exists! Please choose a different one.", Toast.LENGTH_LONG).show();
                        } else if (responseString.equals("Success!")) {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Could not connect to database!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            /*
            ** This is to access user info stored locally in UserManager.
            *
            User newUser = new User(username, pass, email, date.toString(), g, User.AccountType.USER);
            UserManager.addNewUser(newUser);
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            */
        }

    }

}
