using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using ErrorScenes;
using Session;
using UnityEngine.UI;

public class ProfileScreenManager : MonoBehaviour {

    public ErrorPanelManager errorPanel;
    public Text Currency;
    public Text Username;
    public Text Email;
    public Text DoB;
    public Text Gender;



    // Use this for initialization
    void Start()
    {
        if (LoginSession.currentUser == null)
        {
            errorPanel = ErrorPanelManager.Instance();
            errorPanel.assignErrorPressed(returnToLogin);
            errorPanel.displayError("Login Error\nTry again");

        } else
        {
            Currency.text = LoginSession.currentUser.Currency.ToString();
            Username.text = LoginSession.currentUser.Username;
            Email.text = LoginSession.currentUser.Email;
            DoB.text = LoginSession.currentUser.DoB;
            Gender.text = LoginSession.currentUser.gender.ToString();

        }
    }

    public void onLogout() {
        //TODO: end user session
		SceneManager.LoadScene (0);
        LoginSession.clearSession();
    }

	public void Delete() {
        //TODO: delete user from map
        UserItems.User.AccountType accountType = LoginSession.currentUser.accountType;
        if (accountType != UserItems.User.AccountType.USER)
        {
            errorPanel = ErrorPanelManager.Instance();
            errorPanel.assignErrorPressed(returnToLogin);
            errorPanel.displayError("Error\nCannot delete " + accountType.ToString() + " account");
        } else
        {
            UserItems.UserManager.removeUser(LoginSession.currentUser);
            LoginSession.clearSession();
            SceneManager.LoadScene(0);
        }

	}

    public void returnToLogin()
    {
        SceneManager.LoadScene(0);
    }
}
