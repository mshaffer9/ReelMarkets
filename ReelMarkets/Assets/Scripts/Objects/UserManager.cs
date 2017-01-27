using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace UserItems
{
    public class UserManager
    {

        Dictionary<string, User> map = new Dictionary<string, User>()
    {
            {"bob", new User("bob", "password", "bob@waters.com", "09-05-1922", User.Gender.MALE, User.AccountType.MODERATOR) },
            { "admin", new User("admin", "admin", "admin@admin.com", "01-01-1999", User.Gender.MALE, User.AccountType.ADMIN) },
            { "resercher", new User("researcher", "science", "research@gatech.edu", "04-12-1993", User.Gender.FEMALE, User.AccountType.USER) }
    };

        public bool isExistingUser(string name)
        {
            return map.ContainsKey(name.ToLower());
        }

        public void addNewUser(User user)
        {
            map[user.Username.ToLower()] = user;
        }

        public void removeUser(string user)
        {
            map.Remove(user.ToLower());
        }

        public void removeUser(User user)
        {
            removeUser(user.Username);
        }

        public User getUser(string username)
        {
            if (isExistingUser(username.ToLower()))
            {
                return map[username];
            } else
            {
                return null;
            }
        }
    }
}
