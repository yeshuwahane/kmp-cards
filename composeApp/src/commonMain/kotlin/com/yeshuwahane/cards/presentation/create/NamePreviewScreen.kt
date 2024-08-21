package com.yeshuwahane.cards.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


data class NamePreviewScreen( val name: String,val startColor: String,val endColor: String):Screen {
    @Composable
    override fun Content() {

        PreviewScreen(
            name = name,
            startColor = startColor,
            endColor = endColor,
            onCTA = {

            }
        )


    }

    @Composable
    fun PreviewScreen(
        name: String,
        startColor: String,
        endColor: String,
        onCTA: () -> Unit
    ) {
        val startColorParsed = Color(startColor.toLong(16) or 0xFF000000) // Ensure full opacity
        val endColorParsed = Color(endColor.toLong(16) or 0xFF000000) // Ensure full opacity

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Preview Title
            Text(
                text = "Preview",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Card Display with selected colors and name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
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

            // Continue Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
                    .background(Color.Black, shape = RoundedCornerShape(24.dp))
                    .clickable {
                        onCTA()
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