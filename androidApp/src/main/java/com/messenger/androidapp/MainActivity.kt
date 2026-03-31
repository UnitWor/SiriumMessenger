package com.messenger.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.messenger.androidapp.ui.theme.SiriumTheme
import io.github.fletchmckee.liquid.liquefiable
import io.github.fletchmckee.liquid.liquid
import io.github.fletchmckee.liquid.rememberLiquidState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContent {
            SiriumTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val liquidState = rememberLiquidState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .liquefiable(liquidState)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .liquid(liquidState) {
                    frost = 5.dp
                    shape = RoundedCornerShape(30.dp)
                    refraction = 0.10f
                    edge = 0.1f
                    curve = 0.05f
                }
        ) {
            Text("Glass", color = Color.White, modifier = Modifier.padding(80.dp))
        }
    }
}