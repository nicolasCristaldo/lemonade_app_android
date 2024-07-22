package com.nicolascristaldo.lemonade

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        TitleText(modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
        )

        LemonadeImageCard(modifier.align(Alignment.Center))
    }
}

@Composable
fun TitleText(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .background(colorResource(R.color.header_app))
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun LemonadeImageCard(modifier: Modifier = Modifier) {
    var requiredTaps by remember { mutableStateOf((2..4).random()) }
    var step by remember { mutableStateOf(1) }
    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val tapTextResource = when(step) {
        1 -> R.string.tap_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_lemonade_glass
        else -> R.string.tap_empty_glass
    }
    val textContentDescriptionResource = when(step) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade_glass
        else -> R.string.empty_glass
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = {
                if(step != 2 && step != 4) {
                    step++
                }
                else if(step == 2) {
                    if(requiredTaps != 0) {
                        requiredTaps--
                    }
                    else {
                        requiredTaps = (2..4).random()
                        step++
                    }
                }
                else {
                    step = 1
                }
            },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.background_button))
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(textContentDescriptionResource)
            )
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = stringResource(tapTextResource)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
