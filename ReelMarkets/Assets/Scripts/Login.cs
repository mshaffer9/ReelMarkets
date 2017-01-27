using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Text.RegularExpressions;
using UserItems;
using LoadScene;
using UnityEngine.SceneManagement;

public class Login : MonoBehaviour {
    public static User currentUser; 
    private string inputUsername;
    private string inputPassword;
    public InputField username;
    public InputField password;

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
        Debug.Log("Attempting to login. . .");
        bool isValid = (inputUsername != null && inputPassword != null 
            && !inputUsername.Equals("") && !inputPassword.Equals(""));
        if (isValid && UserManager.isExistingUser(inputUsername))
        {
            User userInfo = UserManager.getUser(inputUsername);
            if (userInfo != null && userInfo.Password.Equals(inputPassword)) {
                currentUser = userInfo;
                Debug.Log("Login worked");
                SceneManager.LoadScene(2);
            } else
            {
                Debug.Log("Invalid login: wrong password"); //wrong password
                Debug.Log("attempted pass: " + inputPassword);
                Debug.Log("actual pass: " + userInfo.Password);
            }
        } else
        {
            Debug.Log("Invalid login: fields blank or username doesn't exist"); //fields blank or username does not exist
        }
        // else user doesn't exist
    }

}
