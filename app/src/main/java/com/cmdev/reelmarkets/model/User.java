package com.cmdev.reelmarkets.model;

/**
 * Created by Vagdevi on 2/1/2017.
 */

public class User {
    public final int INITIAL_CURRENCY = 2500;

    public enum Gender { MALE, FEMALE, NONE };
    public enum AccountType { USER, MOD, ADMIN };

    public String Username;
    public String Password;
    public String Email;
    public int Currency;
    public String DoB;
    public Gender gender;
    public AccountType accountType;

    /*
    Used to register a user.
     */
    public User(String username, String password, String email, String dob, Gender g, AccountType aT)
    {
        Username = username;
        Password = password;
        Email = email;
        Currency = INITIAL_CURRENCY;
        DoB = dob;
        gender = g;
        accountType = aT;
    }

    /*
    Used to login a user.
     */
    public User(String username, String email, String dob, int currency, Gender g, AccountType aT)
    {
        Username = username;
        Password = "";
        Email = email;
        Currency = currency;
        DoB = dob;
        gender = g;
        accountType = aT;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getCurrency() {
        return Currency;
    }

    public void setCurrency(int currency) {
        Currency = currency;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
