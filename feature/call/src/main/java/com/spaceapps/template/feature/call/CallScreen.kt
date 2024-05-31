package com.spaceapps.template.feature.call

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class CallState {
    RINGING,
    CONNECTED,
    RECONNECTING,
}

@Preview
@Composable
fun CallScreen(modifier: Modifier = Modifier) {
    var callState by remember { mutableStateOf(CallState.RINGING) }
    val startGradientColor by animateColorAsState(
        targetValue =
            when (callState) {
                CallState.RINGING -> Color(0xff00b3ff)
                CallState.CONNECTED -> Color(0xff00ff2f)
                CallState.RECONNECTING -> Color(0xffff9100)
            },
        label = "",
    )
    val endGradientColor by animateColorAsState(
        targetValue =
            when (callState) {
                CallState.RINGING -> Color(0xff0055ff)
                CallState.CONNECTED -> Color(0xff008c17)
                CallState.RECONNECTING -> Color(0xffff5100)
            },
        label = "",
    )
    val gradient =
        Brush.verticalGradient(
            colors =
                listOf(
                    startGradientColor,
                    endGradientColor,
                ),
        )
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(gradient),
    ) {
        Row(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = if (callState == CallState.RINGING) Arrangement.SpaceBetween else Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = callState == CallState.RINGING,
                enter = EnterTransition.None,
                exit = fadeOut(),
            ) {
                Button(
                    modifier = Modifier.size(72.dp),
                    onClick = { callState = CallState.CONNECTED },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                ) {
                    Icon(
                        painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                        contentDescription = null,
                    )
                }
            }
            Button(
                modifier = Modifier.size(72.dp),
                onClick = { callState = CallState.RECONNECTING },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Icon(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_decline),
                    contentDescription = null,
                )
            }
        }
    }
}
