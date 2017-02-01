package com.cmdev.reelmarkets.model;

import java.util.HashMap;

/**
 * Created by Vagdevi on 2/1/2017.
 */

public final class UserManager {
    private static HashMap<String, User> userMap;

    private UserManager() {
    }

    private static void initMap() {
        userMap = new HashMap<>();
        userMap.put("bob", new User("bob", "password", "bob@waters.com", "09-05-1922", User.Gender.MALE, User.AccountType.MODERATOR));
        userMap.put("admin", new User("admin", "admin", "admin@admin.com", "01-01-1999", User.Gender.MALE, User.AccountType.ADMIN));
        userMap.put("resercher", new User("researcher", "science", "research@gatech.edu", "04-12-1993", User.Gender.FEMALE, User.AccountType.USER));
    }

    public static boolean isExistingUser(String name)
    {
        if (userMap == null) {
            initMap();
        }
        return userMap.containsKey(name.toLowerCase());
    }

    public static void addNewUser(User user)
    {
        if (userMap == null) {
            initMap();
        }
        userMap.put(user.Username.toLowerCase(), user);
    }

    public static void removeUser(String user)
    {
        if (userMap == null) {
            initMap();
        }
        userMap.remove(user.toLowerCase());
    }

    public static void removeUser(User user)
    {
        if (userMap == null) {
            initMap();
        }
        removeUser(user.Username);
    }

    public static User getUser(String username)
    {
        if (userMap == null) {
            initMap();
        }
        if (isExistingUser(username.toLowerCase()))
        {
            return userMap.get(username);
        } else
        {
            return null;
        }
    }
}