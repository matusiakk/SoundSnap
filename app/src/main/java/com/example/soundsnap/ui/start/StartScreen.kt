package com.example.soundsnap.ui.start

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soundsnap.R
import com.example.soundsnap.ui.theme.DarkGray
import com.example.soundsnap.ui.theme.LightBlue
import com.example.soundsnap.ui.theme.SoundSnapTheme

@Composable
fun StartScreen(viewModel: StartViewModel = hiltViewModel()) {
    StartScreen(viewModel::onIntent)

}

@Composable
private fun StartScreen(onIntent: (StartIntent) -> Unit) {
    val fredokaFontFamily = FontFamily(Font(R.font.fredoka))

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        Image(
            painter = painterResource(R.drawable.backbround),
            contentDescription = "background"
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = stringResource(R.string.descriptions),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = fredokaFontFamily,
                    modifier = Modifier.padding(
                        horizontal = 120.dp
                    ),
                    textAlign = TextAlign.Center
                )
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 40.dp,
                    horizontal = 144.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue,
                    contentColor = Color.Black
                ),
                onClick = { onIntent(StartIntent.OnGameClick) }) {
                Text(
                    text = stringResource(R.string.start),
                    fontSize = 24.sp,
                    fontFamily = fredokaFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 800,
    heightDp = 360
)
@Composable
fun StartScreenPreview() {
    SoundSnapTheme {
        StartScreen()
    }

}