package com.example.soundsnap.ui.start

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.soundsnap.ui.game.Categories
import com.example.soundsnap.ui.theme.DarkGray
import com.example.soundsnap.ui.theme.SoundSnapTheme

@Composable
fun StartScreen(viewModel: StartViewModel = hiltViewModel()) {
    StartScreen(viewModel::onIntent)
}

@Composable
private fun StartScreen(
    onIntent: (StartIntent) -> Unit
) {
    val fredokaFontFamily = FontFamily(Font(R.font.fredoka))

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        Image(
            painter = painterResource(R.drawable.backbround),
            contentDescription = "background",
            modifier = Modifier.alpha(0.8f)
        )
        Column(modifier = Modifier.padding(start = 120.dp)) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.End)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = "settings"
                )
            }
            Text(
                text = stringResource(R.string.descriptions),
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = fredokaFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    vertical = 8.dp
                ),
                textAlign = TextAlign.Center
            )
            Row {
                Categories.values().forEach { category ->
                    IconButton(
                        onClick = { onIntent(StartIntent.OnGameClick(category))},
                        modifier = Modifier
                            .size(160.dp)
                            .padding(8.dp)
                            .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                    ) {
                        val categoryImage = when (category) {
                            Categories.INSTRUMENTS -> R.drawable.instruments
                            Categories.ANIMALS-> R.drawable.animals
                            Categories.VEHICLES -> R.drawable.vehicles
                        }
                        Image(
                            painter = painterResource(categoryImage),
                            contentDescription = null
                        )
                    }
                }
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