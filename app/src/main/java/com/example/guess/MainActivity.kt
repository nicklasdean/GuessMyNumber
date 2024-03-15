package com.example.guess

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guess.ui.theme.GuessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessScreen()
                }
            }
        }
    }
}

@Composable
fun GuessScreen() {
    val viewModel: GameViewModel = viewModel()

    Column {
        Text(viewModel.secretNumber.toString())
        Row {
            if(viewModel.numberOfGuessesLeft == 3){
                Text(GameViewModel.Messages.WELCOME.message);
            }
            Text(viewModel.messageToUser)
        }

        Row {
            Text("You have " + viewModel.numberOfGuessesLeft + " Guesses left")
        }
        Row {
            TextField(
                value = viewModel.userInput, onValueChange = {viewModel.userInput = it},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }
        Row{
            Button(onClick = {viewModel.handleInput()}) {
                Text("Guess");
            }
            Button(onClick = {viewModel.resetGame()}) {
                Text("Reset");
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessTheme {
        GuessScreen()
    }
}