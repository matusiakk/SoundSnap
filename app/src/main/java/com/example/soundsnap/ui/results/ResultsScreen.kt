package com.example.soundsnap.ui.results

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soundsnap.R
import com.example.soundsnap.ui.theme.DarkGray
import com.example.soundsnap.ui.theme.LightBlue
import com.example.soundsnap.ui.theme.SoundSnapTheme

@Composable
fun ResultsScreen(viewModel: ResultsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    ResultsScreen(state, viewModel::onIntent)
}

@Composable
private fun ResultsScreen(
    state: ResultsState,
    onIntent: (ResultsIntent) -> Unit
) {
    val fredokaFontFamily = FontFamily(Font(R.font.fredoka))

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        Image(
            painter = painterResource(R.drawable.backbround2),
            contentDescription = "background"
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier.size(184.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.score),
                    contentDescription = "score"
                )
                Text(
                    text = state.score.toString(),
                    fontSize = 80.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fredokaFontFamily,
                    color = DarkGray
                )
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    bottom = 40.dp,
                    start = 144.dp,
                    end = 144.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue,
                    contentColor = Color.Black
                ),
                onClick = { onIntent(ResultsIntent.OnAgainClick) }) {
                Text(
                    text = stringResource(R.string.again),
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
fun ResultsScreenPreview() {
    SoundSnapTheme {
        ResultsScreen()
    }

}