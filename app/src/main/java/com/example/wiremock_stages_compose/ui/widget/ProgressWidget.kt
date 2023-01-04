package com.example.wiremock_stages_compose.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun Circle(
    modifier: Modifier,
    color: Color,
    colorChecked: Color,
    checked: Boolean,
    content: @Composable (BoxScope.() -> Unit) = { }
) {
    Box(
        modifier = modifier.drawBehind {
            if (checked) {
                drawCircle(color = colorChecked)
            } else {
                drawCircle(color = color, style = Stroke(3.dp.toPx()))
            }
        },
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
private fun Line(modifier: Modifier, color: Color, checked: Boolean) {
    Canvas(modifier = modifier) {
        drawLine(
            color = if (checked) color else Color.Gray,
            start = Offset(0f, -40f),
            end = Offset(size.width, -40f),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Composable
fun ProgressWidget(size: Int, currentIndex: Int, states: List<String>) {
    val circleColor: Color = Color.Gray
    val circleColorChecked: Color = Color.Blue
    val lineColor: Color = Color.Blue
    val checkColor: Color = Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { i ->
            val checked = i <= currentIndex - 1
            Column(
                modifier = Modifier.width(40.dp)
            ) {
                Circle(
                    modifier = Modifier.size(40.dp),
                    color = circleColor,
                    colorChecked = circleColorChecked,
                    checked = checked
                ) {
                    if (checked) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            tint = checkColor,
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    text = states[i],//.substring(0,4),
                    //fontSize = 10.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            if (i < size - 1)
                Line(
                    modifier = Modifier.weight(1f),
                    color = lineColor,
                    checked = i <= currentIndex - 2
                )
        }
    }
}

