package com.yeshuwahane.cards.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource


class HomeScreen :Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        CreateCallingCardsScreen(
            onClick = {
                navigator.push(SwipeScreen())
            }
        )

    }


    @Composable
    fun CreateCallingCardsScreen(onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Heading text
                Text(
                    text = "Create\nPersonalised\nCalling Cards",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 40.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Subheading text
                Text(
                    text = "Fill the info prompts with the data you want to show, choose the color & designs to customise it!",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Custom card using Box with tilt
                Box(
                    modifier = Modifier
                        .size(250.dp, 200.dp) // Increase the size of the card
                        .clickable {
                            onClick.invoke()
                        }
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                        .graphicsLayer(
                            rotationZ = -10f // Tilt the card slightly
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // "Let's go!" text inside the card
                    Text(
                        text = "Let's go!",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}