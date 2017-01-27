using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ProfileScreenManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	public void onLogout() {
		//TODO: end user session

		SceneManager.LoadScene (0);
	}

	public void Delete() {
		//TODO: delete user from map


		SceneManager.LoadScene (0);
	}
}
