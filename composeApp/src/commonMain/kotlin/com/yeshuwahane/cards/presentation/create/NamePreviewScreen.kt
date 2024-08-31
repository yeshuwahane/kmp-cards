package com.yeshuwahane.cards.presentation.create

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


data class NamePreviewScreen( val name: String,val startColor: String,val endColor: String):Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        PreviewScreen(
            name = name,
            startColor = startColor,
            endColor = endColor,
            onContinueCTA = { name->
                navigator.push(FinalPreviewScreen(name,startColor, endColor))
            }
        )


    }



    @Composable
    fun PreviewScreen(
        name: String,
        startColor: String,
        endColor: String,
        onContinueCTA: (String) -> Unit
    ) {
        val startColorParsed = Color(startColor.toLong(16) or 0xFF000000)
        val endColorParsed = Color(endColor.toLong(16) or 0xFF000000)

        var isEditing by remember { mutableStateOf(false) }
        var editableName by remember { mutableStateOf(name) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
                .clickable {
                    if (isEditing) {
                        focusManager.clearFocus()
                        isEditing = false
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Title
            Text(
                text = "Preview",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Editable Name Display
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
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
                    if (isEditing) {
                        TextField(
                            value = editableName,
                            onValueChange = { editableName = it },
                            textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                            modifier = Modifier
                                .padding(16.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus()
                                isEditing = false
                            }),
                            singleLine = true
                        )
                    } else {
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

            // Continue Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
                    .background(Color.Black, shape = RoundedCornerShape(24.dp))
                    .clickable {
                        onContinueCTA(editableName)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "continue",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }











}