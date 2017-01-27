using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class HomeScreenManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
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
}
