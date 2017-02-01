package com.cmdev.reelmarkets.model;

/**
 * Created by Vagdevi on 2/1/2017.
 */

public class LoginSession {
    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        LoginSession.currentUser = currentUser;
    }

    public static boolean isValidSession()
    {
        if (currentUser != null)
        {
            return true;
        }
        return false;
    }

    public static void clearSession()
    {
        currentUser = null;
    }
}
