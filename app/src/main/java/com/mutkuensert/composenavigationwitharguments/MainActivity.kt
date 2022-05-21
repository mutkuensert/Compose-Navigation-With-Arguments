package com.mutkuensert.composenavigationwitharguments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mutkuensert.composenavigationwitharguments.ui.theme.ComposeNavigationWithArgumentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeNavigationWithArgumentsTheme {
                NavHost(navController = navController, startDestination = "FirstScreen" ){
                    composable("FirstScreen"){ FirstScreen(navController = navController)}

                    composable("SecondScreen/{someString}"){backStackEntry -> //Bu string argümanı null veya boş string olamaz, hata verecektir.
                        SecondScreen(navController = navController, backStackEntry.arguments?.getString("someString"))
                    }

                    composable(
                        "ThirdScreen?someString={someString}",//Bu string argümanı null veya boş olabilir.
                        //Bu tür argüman kullanımı dokümantasyonda isteğe bağlı argüman(Optional Argument) olarak geçiyor.
                        //navController.navigate() fonksiyonu optional argument kullanımında temel kullanımdan farklılık göstermekte.
                        arguments = listOf(
                            navArgument("someString"){
                                nullable = true
                            })
                    ){ backStackEntry ->
                        ThirdScreen(navController = navController, backStackEntry.arguments?.getString("someString"))
                    }

                    composable(//Argüman tipini değiştirelim.
                        "FourthScreen/{someInteger}",
                        arguments = listOf(navArgument("someInteger"){
                            type = NavType.IntType
                        })
                    ){backStackEntry ->
                        FourthScreen(navController = navController, backStackEntry.arguments?.getInt("someInteger"))
                    }
                }
            }
        }
    }
}