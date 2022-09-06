using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TurnManager : MonoBehaviour
{
    public GameObject player1, player2;

    public int turn;

    // Start is called before the first frame update
    void Start()
    {
        //Pick who starts the game
        //Flip a coin
        int coinFlip = Random.Range(1, 3);

        turn = coinFlip;
        ChangeTurn();
    }

    public void ChangeTurn()
    {
        if (turn == 1)
        {
            //It's player 1's turn, change it to player2
            turn = 2;

            player2.GetComponent<CannonScript>().camera.SetActive(true);
            player1.GetComponent<CannonScript>().camera.SetActive(false);

            //Also disable the script
            player2.GetComponent<CannonScript>().enabled = true;
            player1.GetComponent<CannonScript>().enabled = false;
        }
        else
        {
            turn = 1;

            player1.GetComponent<CannonScript>().camera.SetActive(true);
            player2.GetComponent<CannonScript>().camera.SetActive(false);

            //Also disable the script
            player1.GetComponent<CannonScript>().enabled = true;
            player2.GetComponent<CannonScript>().enabled = false;
        }
    }

    // Update is called once per frame
    void Update()
    {
        //Testing the change turn 
        //DONT ADD IT TO THE GAME
        if (Input.GetButtonDown("Jump"))
        {
            ChangeTurn();
        }
    }
}
