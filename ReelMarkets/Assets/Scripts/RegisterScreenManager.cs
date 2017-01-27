using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class RegisterScreenManager : MonoBehaviour {

    //holding info in variables, put in database at the very end
    private string userName = "";
    private string userPassword = "";
    private string userEmail = "";
    private string userDOB = "";
    private string userGen = "";

    private bool passwordError = false;

    public GameObject passErrorPanel;

    public void Update()
    {
        if (passwordError)
        {
            passErrorPanel.SetActive(true);
        }
    }

    //User's info will be put in database, and they will be re-routed to log in
    public void onRegisterPressed()
    {
        if(!passwordError)
        {
            //TODO: put stuff in database

            //Go to login
            SceneManager.LoadScene(0);
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
    public void onPassEntered(UnityEngine.UI.Text input)
    {
        this.userPassword = input.text;
    }

    //checks password confirmation from UI
    public void onConfirmEntered(UnityEngine.UI.Text input)
    {
        if(!((input.text).Equals(userPassword)))
        {
            this.passwordError = true;
        }
    }

    //closes the error popup when a password error is present
    public void okErrorPressed()
    {
        passErrorPanel.SetActive(false);
        this.passwordError = false;
    }
}
