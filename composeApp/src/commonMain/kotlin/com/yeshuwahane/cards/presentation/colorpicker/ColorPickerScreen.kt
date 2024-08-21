package com.yeshuwahane.cards.presentation.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.yeshuwahane.cards.presentation.create.AddNameScreen


class ColorPickerScreen():Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            ColorSelectionUI(
                onCTA = { startColor, endColor ->
                    navigator.push(AddNameScreen(startColor,endColor))
                }
            )
        }

    }

    @Composable
    fun ColorSelectionUI(onCTA: (startColor:String,endColor:String) -> Unit) {
        var selectedColorIndex by remember { mutableStateOf(0) }
        val colors = listOf(
            listOf(Color(0xFFFFC1E3), Color(0xFFFFF0F8)),
            listOf(Color(0xFF80DEEA), Color(0xFFB3E5FC)),
            listOf(Color(0xFFFFE082), Color(0xFFFFF8E1)),
            listOf(Color(0xFFFFAB91), Color(0xFFFFCCBC)),
            listOf(Color(0xFFA5D6A7), Color(0xFFC8E6C9)),
            listOf(Color(0xFFFFF176), Color(0xFFFFF59D)),
            listOf(Color(0xFFCE93D8), Color(0xFFD1C4E9)),
            listOf(Color(0xFFFFF9C4), Color(0xFFFFF59D)),
            listOf(Color(0xFFF8BBD0), Color(0xFFFFC1E3)),
            listOf(Color(0xFFB39DDB), Color(0xFFD1C4E9)),
            listOf(Color(0xFFB3E5FC), Color(0xFF80DEEA)),
            listOf(Color(0xFFCCFF90), Color(0xFFAED581)),
            listOf(Color(0xFFFFF8E1), Color(0xFFFFE082)),
            listOf(Color(0xFF82B1FF), Color(0xFF448AFF)),
            listOf(Color(0xFFFF8A80), Color(0xFFFF5252)),
            listOf(Color(0xFFDCE775), Color(0xFFC0CA33)),
            listOf(Color(0xFFFFD740), Color(0xFFFFC400)),
            listOf(Color(0xFF8C9EFF), Color(0xFF536DFE)),
            listOf(Color(0xFF80CBC4), Color(0xFF4DB6AC)),
            listOf(Color(0xFF64B5F6), Color(0xFF42A5F5))
        )

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
                text = "Choose Color",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Card Display
            val selectedBrush = Brush.verticalGradient(colors[selectedColorIndex])
            val startColor = colors[selectedColorIndex][0]
            val endColor = colors[selectedColorIndex][1]
            val startColorString = "" + startColor.toArgb().toUInt().toString(16).takeLast(6).uppercase()
            val endColorString = "" + endColor.toArgb().toUInt().toString(16).takeLast(6).uppercase()

            Box(
                modifier = Modifier
                    .size(180.dp, 280.dp)
                    .background(brush = selectedBrush, shape = RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                ) {
                    Text(
                        text = "hello.",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Scrollable Color Options
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(colors.chunked(3)) { rowColors ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        rowColors.forEachIndexed { index, colorList ->
                            val gradientBrush = Brush.verticalGradient(colorList)
                            Box(
                                modifier = Modifier
                                    .size(90.dp, 60.dp)
                                    .background(brush = gradientBrush, shape = RoundedCornerShape(16.dp))
                                    .border(
                                        width = 3.dp,
                                        color = if (colors.indexOf(colorList) == selectedColorIndex) Color.Black else Color.Transparent,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clickable {
                                        selectedColorIndex = colors.indexOf(colorList)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (colors.indexOf(colorList) == selectedColorIndex) {
                                    Text(
                                        text = "✓",
                                        color = Color.Black,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
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
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        // Handle continue
                        println("Selected colors: Start - $startColorString, End - $endColorString")

                        onCTA.invoke(startColorString,endColorString)
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