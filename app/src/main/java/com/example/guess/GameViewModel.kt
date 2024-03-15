package com.example.guess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var secretNumber = generateRandomNumber();
    var numberOfGuessesLeft: Int by mutableIntStateOf(3)
    var userInput: String by mutableStateOf("");
    var messageToUser: String by mutableStateOf("");

    private fun generateRandomNumber(): Int {
        return (1..10).random();
    }

    fun handleInput(): Unit {
        numberOfGuessesLeft--

        messageToUser = when {
            numberOfGuessesLeft < 0 -> Messages.LOSS.message
            userInput.toInt() > secretNumber -> Messages.HIGHER.message
            userInput.toInt() < secretNumber -> Messages.LOWER.message
            else -> Messages.WIN.message
        }
    }

    fun resetGame(): Unit{
        //Todo
    }

    enum class Messages(val message: String) {
        WELCOME("Welcome to the game - guess a number between 1-10"),
        HIGHER("Your guess was too hight"),
        LOWER("You guess was too low"),
        WIN("Wow you guess it ! Congrats"),
        LOSS("You lost")
    }
}