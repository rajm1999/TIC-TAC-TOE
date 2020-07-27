package com.example.tictactoekotlin

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class TicTacToeActivity : AppCompatActivity() {

        enum class  PLAYINGPLAYER {
            FIRST_PLAYER,
            SECOND_PLAYER
        }
        enum class WINNER_OF_GAME{
            PLAYER_ONE,
            PLAYER_TWO,
            NO_ONE
        }
        //Instance Variables
    var playingPLayer: PLAYINGPLAYER? = null
    var winnerOfGame: WINNER_OF_GAME? = null
    var player1Options: ArrayList<Int> = ArrayList()
    var player2Options: ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            try{
                startService(Intent(this@TicTacToeActivity,
                    SoundService::class.java))
            }catch (e: Exception){
                e.printStackTrace()
            }
        playingPLayer =PLAYINGPLAYER.FIRST_PLAYER

    }


     fun imageButtonTapped(view: View){

         val selectedImageButton: ImageButton =view as ImageButton
         //to generate a random value between number 1 and number 9] is generated
         var randomNumber = (Math.random()* 9 + 1).toInt()
         when(randomNumber){

                1 -> tableLayout.setBackgroundColor(Color.YELLOW)
                2 -> tableLayout.setBackgroundColor(Color.RED)
                3 -> tableLayout.setBackgroundColor(Color.BLUE)
                4 -> tableLayout.setBackgroundColor(Color.GREEN)
                5 -> tableLayout.setBackgroundColor(Color.MAGENTA)
                6 -> tableLayout.setBackgroundColor(Color.CYAN)
                7 -> tableLayout.setBackgroundColor(Color.BLACK)
                8 -> tableLayout.setBackgroundColor(Color.LTGRAY)
                9 -> tableLayout.setBackgroundColor(Color.WHITE)
         }
         var optionNumber = 0
         when(selectedImageButton.id){
             R.id.imgButton1 -> optionNumber = 1
             R.id.imgButton2 -> optionNumber = 2
             R.id.imgButton3 -> optionNumber = 3
             R.id.imgButton4 -> optionNumber = 4
             R.id.imgButton5 -> optionNumber = 5
             R.id.imgButton6 -> optionNumber = 6
             R.id.imgButton7 -> optionNumber = 7
             R.id.imgButton8 -> optionNumber = 8
             R.id.imgButton9 -> optionNumber = 9

           }
         action(optionNumber,selectedImageButton)

     }

    private fun action(optionNumber: Int,_selectedImageButton: ImageButton){
        var selectedImageButton =_selectedImageButton
        if(playingPLayer == PLAYINGPLAYER.FIRST_PLAYER) {
            selectedImageButton.setImageResource(R.drawable.x_letter)
            //player1Options is an array list which add optionNumbers of that
            //button which you click
            player1Options.add(optionNumber)
            //this will disable the interactivity with that imgButton
            selectedImageButton.isEnabled=false
            //when there comes situation of draw when all button is clicked
            allDisabledImages.add(selectedImageButton)
            playingPLayer =PLAYINGPLAYER.SECOND_PLAYER
         }
       // else
            if(playingPLayer == PLAYINGPLAYER.SECOND_PLAYER) {
                //Algorithm to Play with OurSelves
//            selectedImageButton.setImageResource(R.drawable.o_letter)
//            //player1Options is an array list which add optionNumbers of that
//            //button which you click
//            player2Options.add(optionNumber)
//            //this will disable the interactivity with that imgButton
//            selectedImageButton.isEnabled=false
//            //when there comes situation of draw when all button is clicked
//            allDisabledImages.add(selectedImageButton)
//            playingPLayer =PLAYINGPLAYER.FIRST_PLAYER
                //Algorithm to play with Computer
            var notSelectedImageNumbers =ArrayList<Int>()
            for (imageNumber in 1..9) {
                if (!(player1Options.contains((imageNumber)))) {
                    if (!player2Options.contains(imageNumber)) {
                        //notSelectedImageNumbers is created in order to hold
                        //the image numbers of the image buttons that are not selected
                        notSelectedImageNumbers.add(imageNumber)
                    }
                }
            }
            try{
                var randomNumber =(Math.random() * notSelectedImageNumbers.size).toInt()
                var imageNumber =notSelectedImageNumbers[randomNumber]
                when(imageNumber){
                    1 -> selectedImageButton =imgButton1
                    2 -> selectedImageButton =imgButton2
                    3 -> selectedImageButton =imgButton3
                    4 -> selectedImageButton =imgButton4
                    5 -> selectedImageButton =imgButton5
                    6 -> selectedImageButton =imgButton6
                    7 -> selectedImageButton =imgButton7
                    8 -> selectedImageButton =imgButton8
                    9 -> selectedImageButton =imgButton9

                }
                selectedImageButton.setImageResource(R.drawable.o_letter)
                player2Options.add(imageNumber)
                selectedImageButton.isEnabled =false
                allDisabledImages.add(selectedImageButton)
                playingPLayer = PLAYINGPLAYER.FIRST_PLAYER
            }
            catch (e: Exception){
                e.printStackTrace()
            }

        }

        specifyTheWinnerOfGame()

    }
    private fun specifyTheWinnerOfGame(){
        if(player1Options.contains(1)
            &&player1Options.contains(2)
            &&player1Options.contains(3)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(1)
            &&player2Options.contains(2)
            &&player2Options.contains(3)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(4)
            &&player1Options.contains(5)
            &&player1Options.contains(6)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(4)
            &&player2Options.contains(5)
            &&player2Options.contains(6)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(7)
            &&player1Options.contains(8)
            &&player1Options.contains(9)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(7)
            &&player2Options.contains(8)
            &&player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(1)
            &&player1Options.contains(4)
            &&player1Options.contains(7)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(1)
            &&player2Options.contains(4)
            &&player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(2)
            &&player1Options.contains(5)
            &&player1Options.contains(8)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(2)
            &&player2Options.contains(5)
            &&player2Options.contains(8)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(3)
            &&player1Options.contains(6)
            &&player1Options.contains(9)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(3)
            &&player2Options.contains(6)
            &&player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(1)
            &&player1Options.contains(5)
            &&player1Options.contains(9)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(1)
            &&player2Options.contains(5)
            &&player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }else if(player1Options.contains(3)
            &&player1Options.contains(5)
            &&player1Options.contains(7)){

            winnerOfGame =WINNER_OF_GAME.PLAYER_ONE

        } else if(player2Options.contains(3)
            &&player2Options.contains(5)
            &&player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        if(winnerOfGame == WINNER_OF_GAME.PLAYER_ONE){
            createAlert("Player One Wins",
                "Congratulations to Player 1",
            AlertDialog.BUTTON_POSITIVE,"OK",false)
            return
        }else if(winnerOfGame == WINNER_OF_GAME.PLAYER_TWO){
            createAlert("Player Two Wins",
                "Congratulations to Player 2",
                AlertDialog.BUTTON_NEGATIVE,"OK",false)
            return
        }
        //we put return in above  if and in else if statement because if
        //any player wins then below function should not run

        checkDrawState()
    }

    private fun createAlert(title: String,message: String,whichButton: Int,
                            buttonText: String, cancelable: Boolean){

        val alertDialog: AlertDialog = 
            AlertDialog.Builder(this@TicTacToeActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        
        alertDialog.setButton(whichButton,buttonText,{
            dialog: DialogInterface?, which: Int ->
            resetGame()
        })
        alertDialog.setCancelable(cancelable)
        alertDialog.show()
    }

    private fun resetGame(){
        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winnerOfGame =WINNER_OF_GAME.NO_ONE
        playingPLayer=PLAYINGPLAYER.FIRST_PLAYER

        imgButton1.setImageResource(R.drawable.place_holder)
        imgButton2.setImageResource(R.drawable.place_holder)
        imgButton3.setImageResource(R.drawable.place_holder)
        imgButton4.setImageResource(R.drawable.place_holder)
        imgButton5.setImageResource(R.drawable.place_holder)
        imgButton6.setImageResource(R.drawable.place_holder)
        imgButton7.setImageResource(R.drawable.place_holder)
        imgButton8.setImageResource(R.drawable.place_holder)
        imgButton9.setImageResource(R.drawable.place_holder)

        imgButton1.isEnabled = true
        imgButton2.isEnabled = true
        imgButton3.isEnabled = true
        imgButton4.isEnabled = true
        imgButton5.isEnabled = true
        imgButton6.isEnabled = true
        imgButton7.isEnabled = true
        imgButton8.isEnabled = true
        imgButton9.isEnabled = true
    }

    private fun checkDrawState(){
        if(allDisabledImages.size == 9 && winnerOfGame!= WINNER_OF_GAME.PLAYER_ONE
            && winnerOfGame!= WINNER_OF_GAME.PLAYER_TWO){
           createAlert("DRAW!!!","No one won the game!",
                    AlertDialog.BUTTON_POSITIVE,"OK",false)
        }
    }
}
