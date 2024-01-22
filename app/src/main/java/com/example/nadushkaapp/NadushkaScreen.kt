package com.example.nadushkaapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun DateInviteApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dateInviteScreen") {
        composable("dateInviteScreen") {
            DateInviteScreen(navController)
        }
        composable("cuteBunnyScreen") {
            CuteBunnyScreen()
        }
    }
}

@Composable
fun DateInviteScreen(navController: NavHostController) {
    var noButtonOffset by remember { mutableStateOf(Offset(0F, 0F)) }
    var noButtonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.cute_bunny_screen),
            contentDescription = null,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
        Text(
            text = "Would you like to go on a date?",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("cuteBunnyScreen")
                },
                Modifier.size(width = 120.dp, height = 60.dp)
            ) {
                Text(text = "Yes")
            }

            Button(
                onClick = {
                    if (noButtonEnabled) {
                        // Move the button to a random position on the screen
                        noButtonOffset = Offset(
                            Random.nextInt(-200, 200).toFloat(),
                            Random.nextInt(-400, 400).toFloat()
                        )
                    }
                },
                modifier = Modifier
                    .graphicsLayer(
                        translationX = noButtonOffset.x,
                        translationY = noButtonOffset.y
                    )
                        .size(width = 120.dp, height = 60.dp)
            ) {
                Text(text = "No")
            }
        }
    }
}

@Composable
fun CuteBunnyScreen() {
    val bunnyImageModifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .graphicsLayer(translationY = animatedBounce())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cute_bunny),
            contentDescription = "Cute Bunny",
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "В среду в 20:00 жду тебя, с любовью мальчик Никитосик <3",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun animatedBounce(): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    return yOffset
}