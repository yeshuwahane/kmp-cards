package com.yeshuwahane.cards.presentation.create

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


data class FinalPreviewScreen(val name: String,val startColor: String,val endColor: String) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        FinalTouchScreen(
            initialName = name,
            onSaveCard = { name, role, website, email ->
               navigator.push(AnimatedFinalScreen(name,role, website, email,startColor, endColor))
            }
        )
    }

    @Composable
    fun FinalTouchScreen(
        initialName: String,
        onSaveCard: (String, String, String, String) -> Unit
    ) {
        var name by remember { mutableStateOf(initialName) }
        var role by remember { mutableStateOf("") }
        var website by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        val transitionState = remember { MutableTransitionState(false) }
        val transition = updateTransition(transitionState, label = "Screen Animation")

        val animatedAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 500) },
            label = "Alpha Animation"
        ) { state -> if (state) 1f else 0f }

        LaunchedEffect(Unit) {
            transitionState.targetState = true
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
                .alpha(animatedAlpha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Final Touch",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(24.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "I am an",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                    )

                   /* Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = name,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )*/

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = role,
                        onValueChange = { role = it },
                        textStyle = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Profession",
                                style = TextStyle(
                                    fontSize = 34.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Divider(color = Color.DarkGray, thickness = 1.dp)

                    TextField(
                        value = website,
                        onValueChange = { website = it },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Website") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent
                        ),

                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Email") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
                    .background(Color.Black, shape = RoundedCornerShape(24.dp))
                    .clickable {
                        onSaveCard.invoke(name,role,website,email)
                        focusManager.clearFocus()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "save card",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}
