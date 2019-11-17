package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Players arrayList
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    //Initially set the first player 1
    private var currentPlayer = 1
    //Initially no winner
    private var winner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //OnClick method for button selected
    fun btnClickEvent(view: View) {
        //which button selected and getId
        val btnSelected = view as Button
        //Initially set the btnId to 0
        var btnId = 0
        //Listen to any click events and get the ID and assign to @btnId
        when (btnSelected) {
            btn1 -> btnId = 1
            btn2 -> btnId = 2
            btn3 -> btnId = 3
            btn4 -> btnId = 4
            btn5 -> btnId = 5
            btn6 -> btnId = 6
            btn7 -> btnId = 7
            btn8 -> btnId = 8
            btn9 -> btnId = 9
        }
        Toast.makeText(this, "Button $btnId selected", Toast.LENGTH_LONG).show()
        playGame(btnSelected, btnId)
    }

    //A function that play the game
    private fun playGame(btnSelected: Button, btnId: Int) {
        //Player one plays first
        if (currentPlayer == 1) {
            //to set all properties for the selected button
            btnSelected.setBackgroundColor(Color.RED)
            btnSelected.text = "X"
            btnSelected.setTextColor(Color.WHITE)
            player1.add(btnId)
            currentPlayer = 2
            autoPlay()
        } else {
            //to set all properties for the selected button
            btnSelected.setBackgroundColor(Color.BLUE)
            btnSelected.text = "O"
            btnSelected.setTextColor(Color.WHITE)
            player2.add(btnId)
            currentPlayer = 1
        }
//When a button is clicked it disable it immediately
        btnSelected.isEnabled = false
        //If any button is selected then check winner
        checkWinner()
    }

    private fun checkWinner() {
        //All conditions for player 1 to win the game
        if (
            player1.contains(1) && player1.contains(2) && player1.contains(3) ||
            player1.contains(4) && player1.contains(5) && player1.contains(6) ||
            player1.contains(7) && player1.contains(8) && player1.contains(9) ||
            player1.contains(1) && player1.contains(5) && player1.contains(9) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7) ||
            player1.contains(1) && player1.contains(4) && player1.contains(7) ||
            player1.contains(3) && player1.contains(6) && player1.contains(9) ||
            player1.contains(2) && player1.contains(5) && player1.contains(8)

        ) {
            winner = 1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            //All conditions for player 2 to win
        } else if (
            player2.contains(1) && player2.contains(2) && player2.contains(3) ||
            player2.contains(4) && player2.contains(5) && player2.contains(6) ||
            player2.contains(7) && player2.contains(8) && player2.contains(9) ||
            player2.contains(1) && player2.contains(5) && player2.contains(9) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) ||
            player2.contains(1) && player2.contains(4) && player2.contains(7) ||
            player2.contains(3) && player2.contains(6) && player2.contains(9) ||
            player2.contains(2) && player2.contains(5) && player2.contains(8)
        ) {
            winner = 2
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
        }
//Check if there is winner if yes disable all buttons
        if (winner != 0) {
            val allBtn = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            for (btnItems in allBtn) {
                btnItems.isEnabled = false
            }
        }
    }
    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()
        for (unClickedBtn in 1..9) {
            if (!(player1.contains(unClickedBtn)|| player2.contains(unClickedBtn))) {
                emptyCells.add(unClickedBtn)
            }
        }
        val randomNumber = Random.nextInt(emptyCells.size)
        var cellId = emptyCells[randomNumber]
        var btn:Button? = null
        when(cellId){
            1 -> btn = btn1
            2 -> btn = btn2
            3 -> btn = btn3
            4 -> btn = btn4
            5 -> btn = btn5
            6 -> btn = btn6
            7 -> btn = btn7
            8 -> btn = btn8
            9 -> btn = btn9
        }
        playGame(btn!!,cellId)
    }
}
