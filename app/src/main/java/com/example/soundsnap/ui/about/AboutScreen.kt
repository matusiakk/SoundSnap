package com.example.soundsnap.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soundsnap.R
import com.example.soundsnap.ui.theme.DarkGray
import com.example.soundsnap.ui.theme.SoundSnapTheme

@Composable
fun AboutScreen(viewModel: AboutViewModel = hiltViewModel()) {
    AboutScreen(viewModel::onIntent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutScreen(onIntent: (AboutIntent) -> Unit) {
    val fredokaFontFamily = FontFamily(Font(R.font.fredoka))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.about),
                        modifier = Modifier.padding(start = 80.dp),
                        fontFamily = fredokaFontFamily
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { onIntent(AboutIntent.OnBackClick) }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

            )
            Text(
                text = stringResource(id = R.string.about_content),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                fontFamily = fredokaFontFamily,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

}

@Preview
@Composable
fun AboutScreenPreview() {
    SoundSnapTheme {
        AboutScreen()
    }
}