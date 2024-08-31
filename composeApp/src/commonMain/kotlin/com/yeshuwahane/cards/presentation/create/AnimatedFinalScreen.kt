package com.yeshuwahane.cards.presentation.create

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


data class AnimatedFinalScreen(
    val initialName: String,
    val role: String,
    val website: String,
    val email: String,
    val startColor: String,
    val endColor: String
) : Screen {

    @Composable
    override fun Content() {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            AnimatedFinalTouchScreen(
                initialName, role, website, email,startColor, endColor
            )
        }



    }







    @Composable
    fun AnimatedFinalTouchScreen(
        initialName: String,
        role: String,
        website: String,
        email: String,
        startColor: String,
        endColor: String
    ) {
        var rotated by remember { mutableStateOf(false) }

        val rotation by animateFloatAsState(
            targetValue = if (rotated) 180f else 0f,
            animationSpec = tween(500)
        )

        // Retrieve the density using LocalDensity
        val density = LocalDensity.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(Color.Black)
        ) {

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            ) {
                Text(
                    text = "Hey, ",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(text = initialName.uppercase(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
            // initialName at the top


            Text(
                text = "Here's your Card",
                color = Color.LightGray,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 10.dp, start = 16.dp)
            )


            // card


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable { rotated = !rotated },
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .graphicsLayer {
                            rotationY = rotation
                            cameraDistance = 8 * density.density
                            // Flip the content back to its correct orientation when rotationY is near 180 degrees
                            scaleX = if (rotation > 90f) -1f else 1f
                        }
                        .background(Color.White, shape = RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp),
                    elevation = 4.dp
                ) {
                    // Show front content only when rotation is between -90 to 90 degrees
                    if (rotation <= 90f) {
                        FrontCardContent(initialName, role, website, email)
                    }
                    // Show back content only when rotation is between 90 to 270 degrees
                    else {
                        BackCardContent(
                            startColor = startColor, endColor = endColor, name = initialName
                        )
                    }
                }
            }


        }



    }















    @Composable
    fun FrontCardContent(
        name: String,
        role: String,
        website: String,
        email: String
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(24.dp)
        ) {
            Text(
                text = "I am an",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = role,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            Divider(color = Color.DarkGray, thickness = 1.dp)


            Spacer(modifier = Modifier.height(25.dp))


            Text(
                text = website,
                style =  TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = email,
                style =  TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            )
        }


    }

    @Composable
    fun BackCardContent(
        name: String,
        startColor: String,
        endColor: String,
    ) {
        // Content for the back side of the card
        val startColorParsed = Color(startColor.toLong(16) or 0xFF000000)
        val endColorParsed = Color(endColor.toLong(16) or 0xFF000000)

        var isEditing by remember { mutableStateOf(false) }
        var editableName by remember { mutableStateOf(name) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(16.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(startColorParsed, endColorParsed)
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .clickable {
                            isEditing = true
                        }
                ) {

                        Text(
                            text = editableName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )

                }
            }


        }
    }


}