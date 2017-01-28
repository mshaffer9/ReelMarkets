using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UserItems;
using ErrorScenes;

public class RegisterScreenManager : MonoBehaviour {

    //holding info in variables, put in database at the very end
    private string userName = "";
    private string userPassword = "";
    private string userEmail = "";
    private string userDOB = "";
    private string userGen = "";

    private bool passwordError = false;

    public ErrorPanelManager errorPanel;

    public void Update()
    {
    }

    //User's info will be put in database, and they will be re-routed to log in
    public void onRegisterPressed()
    {
        errorPanel = ErrorPanelManager.Instance();
        Debug.Log("ATTEMPTING TO REGISTER ACCOUNT . . .");

        bool isValid = (userName != null && userName != null
            && !userPassword.Equals("") && !userPassword.Equals("")
            && !userEmail.Equals("") && !userEmail.Equals("")
            && !userDOB.Equals("") && !userDOB.Equals(""));


        if (passwordError)
        {
            errorPanel.displayError("Error:\nPassword's do not match!");
            Debug.Log("Passwords do not match");
        }
        else
        {
            if (!isValid)
            {
                errorPanel.displayError("Error:\nFields cannot be blank.");
                Debug.Log("Fields cannot be blank.");
            }
            else
            {
                if (UserManager.isExistingUser(userName))
                {
                    // throw error panel that user exists
                    errorPanel.displayError("Error:\nUsername already exists. Please choose a different one.");
                    Debug.Log("Username already exists.");
                }
                else
                {

                    User.Gender g = User.Gender.NONE;
                    userGen = userGen.ToLower();
                    if (userGen.Equals("m") || userGen.Equals("Male"))
                    {
                        g = User.Gender.MALE;
                    }
                    else if (userGen.Equals("f") || userGen.Equals("female"))
                    {
                        g = User.Gender.FEMALE;
                    }
                    User newUser = new User(userName, userPassword, userEmail, userDOB, g, User.AccountType.USER);
                    UserManager.addNewUser(newUser);
                    Debug.Log("SUCCESS! Created new User!");
                    Debug.Log("Redirecting to login screen. . .");
                    SceneManager.LoadScene(0);
                }
                //TODO: put stuff in database
            }
        }


    }

    //takes username input from UI
    public void onUsernameEntered(UnityEngine.UI.Text input)
    {
        this.userName = input.text;
    }

    //takes email input from UI
    public void onEmailEntered(UnityEngine.UI.Text input)
    {
        this.userEmail = input.text;
    }

    //takes dob input from UI
    public void onDOBEntered(UnityEngine.UI.Text input)
    {
        this.userDOB = input.text;
    }

    //takes gender input from UI
    public void onGenEntered(UnityEngine.UI.Text input)
    {
        this.userGen = input.text;
    }

    //takes password input from UI
    public void onPassEntered(UnityEngine.UI.InputField input)
    {
        this.userPassword = input.text;
    }

    //checks password confirmation from UI
    public void onConfirmEntered(UnityEngine.UI.InputField input)
    {
        if(!((input.text).Equals(userPassword)))
        {
            this.passwordError = true;
        } else
        {
            passwordError = false;
        }
    }

}
