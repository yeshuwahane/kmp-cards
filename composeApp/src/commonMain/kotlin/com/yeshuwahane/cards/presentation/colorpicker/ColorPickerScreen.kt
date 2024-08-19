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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


class ColorPickerScreen():Screen {

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            ColorSelectionUI()
        }

    }

    @Composable
    fun ColorSelectionUI() {
        var selectedColorIndex by remember { mutableStateOf(0) }
        val colors = listOf(
            Brush.verticalGradient(listOf(Color(0xFFFFC1E3), Color(0xFFFFF0F8))),
            Brush.verticalGradient(listOf(Color(0xFF80DEEA), Color(0xFFB3E5FC))),
            Brush.verticalGradient(listOf(Color(0xFFFFE082), Color(0xFFFFF8E1))),
            Brush.verticalGradient(listOf(Color(0xFFFFAB91), Color(0xFFFFCCBC))),
            Brush.verticalGradient(listOf(Color(0xFFA5D6A7), Color(0xFFC8E6C9))),
            Brush.verticalGradient(listOf(Color(0xFFFFF176), Color(0xFFFFF59D))),
            Brush.verticalGradient(listOf(Color(0xFFCE93D8), Color(0xFFD1C4E9))),
            Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFFF59D))),
            Brush.verticalGradient(listOf(Color(0xFFF8BBD0), Color(0xFFFFC1E3))),
            Brush.verticalGradient(listOf(Color(0xFFB39DDB), Color(0xFFD1C4E9))),
            Brush.verticalGradient(listOf(Color(0xFFB3E5FC), Color(0xFF80DEEA))),
            Brush.verticalGradient(listOf(Color(0xFFCCFF90), Color(0xFFAED581))),
            Brush.verticalGradient(listOf(Color(0xFFFFF8E1), Color(0xFFFFE082))),
            Brush.verticalGradient(listOf(Color(0xFF82B1FF), Color(0xFF448AFF))),
            Brush.verticalGradient(listOf(Color(0xffd02b17), Color(0xffef8282))),
            Brush.verticalGradient(listOf(Color(0xFFDCE775), Color(0xFFC0CA33))),
            Brush.verticalGradient(listOf(Color(0xFFFFD740), Color(0xffe8d9a7))),
            Brush.verticalGradient(listOf(Color(0xFF8C9EFF), Color(0xFF536DFE))),
            Brush.verticalGradient(listOf(Color(0xFFFFA726), Color(0xFFFF7043))),
            Brush.verticalGradient(listOf(Color(0xFF80CBC4), Color(0xFF4DB6AC))),
            Brush.verticalGradient(listOf(Color(0xFF64B5F6), Color(0xFF42A5F5))),
            Brush.verticalGradient(listOf(Color(0xffd89de5), Color(0xfff542dd)))
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

            Spacer(modifier = Modifier.height(10.dp))

            // Card Display
            Box(
                modifier = Modifier
                    .size(180.dp, 280.dp)
                    .background(brush = colors[selectedColorIndex], shape = RoundedCornerShape(24.dp)),
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

            Spacer(modifier = Modifier.height(10.dp))

            // Scrollable Color Options
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(colors.chunked(2)) { rowColors ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        rowColors.forEachIndexed { index, color ->
                            Box(
                                modifier = Modifier
                                    .size(150.dp, 90.dp)
                                    .background(brush = color, shape = RoundedCornerShape(16.dp))
                                    .border(
                                        width = 3.dp,
                                        color = if (colors.indexOf(color) == selectedColorIndex) Color.Black else Color.Transparent,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clickable { selectedColorIndex = colors.indexOf(color) },
                                contentAlignment = Alignment.Center
                            ) {
                                if (colors.indexOf(color) == selectedColorIndex) {
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
                    .padding(vertical = 10.dp)
                    .height(48.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(24.dp))
                    .align(Alignment.CenterHorizontally)
                    .clickable { /* Handle continue */ },
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