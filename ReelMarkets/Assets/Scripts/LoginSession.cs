using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UserItems;

namespace Session
{

    public static class LoginSession
    {


        public static User currentUser { get; set; }

        public static bool isValidSession()
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
}
