using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using Session;
using ErrorScenes;

public class HomeScreenManager : MonoBehaviour {

    public ErrorPanelManager errorPanel;


    // Use this for initialization
    void Start () {
		if (LoginSession.currentUser == null)
        {
            errorPanel = ErrorPanelManager.Instance();
            errorPanel.assignErrorPressed(returnToLogin);
            errorPanel.displayError("Login Error\nTry again");
            
        }
	}

	public void onInternationalPress () {
		SceneManager.LoadScene (0);//not active
	}

	public void onComedyPress() {
		SceneManager.LoadScene (0);//not active
	}

	public void onHorrorPress () {
		SceneManager.LoadScene (0);//not active
	}

	public void onActionPress () {
		SceneManager.LoadScene (0);//not active
	}

	public void onProfilePress () {
		SceneManager.LoadScene (3);
	}

    private void returnToLogin()
    {
        SceneManager.LoadScene(0);
    }
}
