using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

namespace ErrorScenes
{
    public class ErrorPanelManager : MonoBehaviour
    {

        public GameObject errorPanel;
        public Text errorMessage;
        public delegate void ErrorMethod();
        public ErrorMethod okErrorPressedFunction;
        private static ErrorPanelManager errorPanelManager;

        public static ErrorPanelManager Instance()
        {
            if (!errorPanelManager)
            {
                errorPanelManager = FindObjectOfType(typeof(ErrorPanelManager)) as ErrorPanelManager;
                if (!errorPanelManager)
                    Debug.LogError("There needs to be one active ErrorPanelManager!\n Something is wrong with delaration");
            }
            return errorPanelManager;
        }


        public void displayError()
        {
            displayError("Display Error Message Here");
        }

        public void displayError(string message)
        {
            errorMessage.text = message;
            errorPanel.SetActive(true);
        }

        public void setErrorMessage(string message)
        {
            errorMessage.text = message;
        }

        public void hideErrorPanel()
        {
            errorPanel.SetActive(false);
        }

        public void okErrorPressed()
        {
            errorPanel.SetActive(false);
            this.okErrorPressedFunction.Invoke();
        }

        public void assignErrorPressed(ErrorMethod e)
        {
            this.okErrorPressedFunction = e;
        }
    }
}
