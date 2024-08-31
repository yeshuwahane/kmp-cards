package com.yeshuwahane.cards.presentation.create

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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class AddNameScreen(val startColor: String,val endColor: String):Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        ColorDetailScreen(
            startColor = startColor,
            endColor = endColor,
            onPreviewCTA = { name->
                navigator.push(NamePreviewScreen(startColor = startColor, endColor = endColor, name = name))
            }
        )


    }


    @Composable
    fun ColorDetailScreen(
        startColor: String,
        endColor: String,
        onPreviewCTA: (name:String) -> Unit
    ) {
        val startColorParsed = Color(startColor.toLong(16) or 0xFF000000) // Ensure full opacity
        val endColorParsed = Color(endColor.toLong(16) or 0xFF000000) // Ensure full opacity

        var name by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Title
            Text(
                text = "Add name",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Input field for name
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(text = "Start typing...", color = Color.Gray)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black, shape = RoundedCornerShape(8.dp)),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White)
            )

            // Card Display with selected colors and name preview
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
                ) {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Done Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
                    .background(Color.Black, shape = RoundedCornerShape(24.dp))
                    .clickable {
                        onPreviewCTA(name)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "done",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }










}