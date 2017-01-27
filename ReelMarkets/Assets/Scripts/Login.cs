using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Text.RegularExpressions;
using UserItems;
using LoadScene;

public class Login : MonoBehaviour {
    public static User currentUser; 
    private string inputUsername;
    private string inputPassword;
    public InputField username;
    public InputField password;
    public UserManager usermanager = new UserManager();

	// Use this for initialization
	void Start () {
		
    }
	
	// Update is called once per frame
	void Update () {
        inputUsername = username.text;
        inputPassword = password.text;
    }

    public void LoginButton()
    {
        bool isValid = (inputUsername != null && inputPassword != null 
            && !inputUsername.Equals("") && !inputPassword.Equals(""));
        if (isValid && usermanager.isExistingUser(inputUsername))
        {
            User userInfo = usermanager.getUser(inputUsername);
            if (userInfo != null && userInfo.Password.Equals(inputPassword)) {
                currentUser = userInfo;
                //LoadSceneOnClick.LoadByIndex(2);
                Debug.Log("Login worked"); 
            } else
            {
                Debug.Log("Invalid login"); //wrong password
            }
        } else
        {
            Debug.Log("Invalid login"); //fields blank or username does not exist
        }
        // else user doesn't exist
    }

}
