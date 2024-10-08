package com.example.soundsnap.ui.game

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.delay


@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    GameScreen(state, viewModel::onIntent, context)
}

@Composable
private fun GameScreen(
    state: GameState,
    onIntent: (GameIntent) -> Unit,
    context: Context
) {

    with(state) {
        val fredokaFontFamily = FontFamily(Font(R.font.fredoka))

        val mediaPlayer =
            remember(sound) { MediaPlayer.create(context, sound) }

        var timeLeft by remember {
            mutableIntStateOf(60)
        }

        LaunchedEffect(timeLeft) {
            if (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1
            } else onIntent(GameIntent.OnTimeout)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DarkGray
        ) {
            Image(
                painter = painterResource(R.drawable.backbround3),
                contentDescription = "background",
                modifier = Modifier.alpha(0.8f))
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(modifier = Modifier.size(40.dp),
                        onClick = { onIntent(GameIntent.OnBackClick) }) {
                        Image(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "back"
                        )
                    }
                    Text(
                        text = String.format("%01d:%02d", timeLeft / 60, timeLeft % 60),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fredokaFontFamily,
                        color = LightBlue
                    )
                    Box(
                        modifier = Modifier
                            .size(56.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            painter = painterResource(R.drawable.score),
                            contentDescription = "score"
                        )
                        Text(
                            text = score.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fredokaFontFamily,
                            color = DarkGray
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .size(50.dp)
                                .wrapContentSize(align = Alignment.Center),
                            color = LightBlue
                        )
                    } else {

                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .clickable(
                                    onClick = {
                                        onIntent(
                                            GameIntent.OnImageClick(
                                                firstImageIndex
                                            )
                                        )
                                    },
                                    enabled = isClickable
                                )
                        ) {
                            Image(
                                painter = painterResource(firstImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .clickable(
                                    onClick = {
                                        onIntent(
                                            GameIntent.OnImageClick(
                                                secondImageIndex
                                             )
                                        )
                                    },
                                    enabled = isClickable
                                )
                        ) {
                            Image(
                                painter = painterResource(secondImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                    }
                }
                if (isPlayingSound) mediaPlayer.start()
                else mediaPlayer.pause()
            }
            if (showMessage) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(64.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        if (isAnswerCorrect)
                            Image(
                                painter = painterResource(R.drawable.good),
                                contentDescription = "good"
                            )
                        else
                            Image(
                                painter = painterResource(R.drawable.wrong),
                                contentDescription = "wrong"
                            )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun GameScreenPreview() {
    SoundSnapTheme {
        GameScreen()
    }
}
