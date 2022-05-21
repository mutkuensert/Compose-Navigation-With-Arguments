package com.mutkuensert.composenavigationwitharguments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController){
    val (text, setText) = remember { mutableStateOf("")}
    MyColumn{
        Text(text = "First Screen")

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(value = text, onValueChange = setText)

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { navController.navigate("SecondScreen/${text}") }) {
            Text(text = "To second screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController, someString: String?){
    val (text, setText) = remember { mutableStateOf("")}
    MyColumn{
        Text(text = "Second Screen has received the string: $someString")

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(value = text, onValueChange = setText)
        
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { navController.navigate("ThirdScreen?someString=${text}") }) { //navigate() fonksiyonunun kullanımı farklı.
            Text(text = "To third screen")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController, someString: String?){
    val (text, setText) = remember { mutableStateOf("")}
    MyColumn{
        Text(text = "Third Screen has received the string: $someString")

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = text,
            onValueChange = setText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { navController.navigate("FourthScreen/${text}") }) {
            Text(text = "To fourth screen")
        }
    }
}

@Composable
fun FourthScreen(navController: NavController, someInteger: Int?){
    MyColumn{
        Text(text = "Fourth Screen has received the number: ${someInteger}")

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { navController.navigate("FirstScreen") }) {
            Text(text = "To first screen")
        }
    }
}