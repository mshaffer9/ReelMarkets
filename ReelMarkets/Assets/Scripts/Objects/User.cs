using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace UserItems {
    public class User
    {
        public enum Gender { MALE, FEMALE };
        public enum AccountType { USER, MODERATOR, ADMIN };

        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public int Currency { get; set; }
        public string DoB { get; set; }
        public Gender gender { get; set; }
        public AccountType accountType { get; set; }

        public User()
        {

        }
        public User(string username, string password, string email, string dob, Gender g, AccountType aT)
        {
            Username = username;
            Password = password;
            Email = email;
            Currency = 2500;
            DoB = dob;
            gender = g;
            accountType = aT;
        }
    }
}
