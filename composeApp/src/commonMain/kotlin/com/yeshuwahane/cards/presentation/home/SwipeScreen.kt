package com.yeshuwahane.cards.presentation.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.yeshuwahane.cards.presentation.colorpicker.ColorPickerScreen
import com.yeshuwahane.cards.presentation.slideranimation.UnlockSlider
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource


class SwipeScreen : Screen{

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        CustomCardUI(
            onSwipeCTA = {
                navigator.push(ColorPickerScreen())
            }
        )

    }

    @Composable
    fun CustomCardUI(onSwipeCTA:()->Unit) {
        var isLoading by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Title and Subtitle
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Custom Card",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "just for you & your business",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Middle Section: Scrollable card list
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cards with animation
                val cards = listOf(
                    CardData(text = "hello.", color = Brush.verticalGradient(colors = listOf(Color(0xFFFFC1E3), Color(0xFFFFF0F8)))),
                    CardData(text = "heyya.", color = Brush.verticalGradient(colors = listOf(Color(0xFF80DEEA), Color(0xFFB3E5FC)))),
                    CardData(text = "hey.", color = Brush.verticalGradient(colors = listOf(Color(0xFFB39DDB), Color(0xFFD1C4E9))))
                )
                cards.forEachIndexed { index, cardData ->
                    AnimatedCard(text = cardData.text, color = cardData.color, delay = index * 300L)
                }
            }

            // Bottom Section: Navigation Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                SliderStatic(onSwipeCta = {
                    onSwipeCTA.invoke()
                    isLoading = true
                },
                    isLoading = isLoading
                    )
            }
        }
    }

    @Composable
    fun AnimatedCard(text: String, color: Brush, delay: Long) {
        var startPosition by remember { mutableStateOf(500.dp) }
        val offsetX by animateDpAsState(
            targetValue = startPosition,
            animationSpec = tween(durationMillis = 1000)
        )

        LaunchedEffect(Unit) {
            delay(delay)
            startPosition = 0.dp
        }

        Box(
            modifier = Modifier
                .offset(x = offsetX)
                .size(180.dp, 280.dp)
                .background(brush = color, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    data class CardData(val text: String, val color: Brush)


    @Composable
    fun SliderStatic(onSwipeCta: () -> Unit,isLoading:Boolean) {

        val imageVector = rememberVectorPainter(image = Icons.Default.ArrowForward)
        UnlockSlider(
            isLoading = isLoading,
            onSwipeComplete = {
                onSwipeCta.invoke()
            },
            startIcon = imageVector,
            completionColor = Color.LightGray,
            endIcon = rememberVectorPainter(image = Icons.Default.Done),
            hintText = "Swipe to Unlock"

        )

    }



}