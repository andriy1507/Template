package com.spaceapps.loveyou.ui.hello

import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.spaceapps.loveyou.core.ui.backgroundColor
import com.spaceapps.loveyou.core.ui.borderColor
import com.spaceapps.loveyou.core.ui.buttonColor
import com.spaceapps.loveyou.core.ui.buttonTextStyle
import com.spaceapps.loveyou.core.ui.dp16
import com.spaceapps.loveyou.core.ui.dp2
import com.spaceapps.loveyou.core.ui.dp24
import com.spaceapps.loveyou.core.ui.dp32
import com.spaceapps.loveyou.core.ui.dp4
import com.spaceapps.loveyou.core.ui.dp64
import com.spaceapps.loveyou.core.ui.dp8
import com.spaceapps.loveyou.core.ui.instructionTextStyle
import com.spaceapps.loveyou.core.ui.ratio_1
import com.spaceapps.loveyou.core.ui.textColor
import com.spaceapps.loveyou.core.ui.textFieldTextStyle
import com.spaceapps.loveyou.core.ui.titleTextStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HelloScreen(viewModel: HelloViewModel) {
    val state by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.uiEvents) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvents.collect { sideEffect ->
                when (sideEffect) {
                    is HelloUiEvent.OnButtonClick -> {
                        val customTabIntent =
                            CustomTabsIntent.Builder()
                                .setDefaultColorSchemeParams(
                                    CustomTabColorSchemeParams.Builder()
                                        .setToolbarColor(backgroundColor.toArgb())
                                        .build(),
                                ).setShareState(CustomTabsIntent.SHARE_STATE_OFF)
                                .build()
                        customTabIntent.launchUrl(context, Uri.parse(sideEffect.url))
                    }
                    is HelloUiEvent.ShowToast ->
                        Toast.makeText(context, sideEffect.id, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    val pagerState = rememberPagerState(pageCount = viewModel::pagesCount)
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(backgroundColor),
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) {
            if (!viewModel.isLastPage(it)) {
                val data = viewModel.getPageData(it)
                PagerItem(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                    imageRes = data.imageId,
                    textRes = data.textId,
                )
            } else {
                GoToDateItem(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                    onValueChange = viewModel::onCodeEntered,
                    secretCode = state.secretCode,
                    onClick = viewModel::onButtonClick,
                )
            }
        }
        Row(
            Modifier
                .padding(bottom = dp64)
                .height(dp24)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(pagerState.pageCount) { i ->
                val color by animateColorAsState(
                    targetValue =
                        if (pagerState.currentPage == i) {
                            textColor
                        } else {
                            textColor.copy(alpha = .5f)
                        },
                    label = "",
                )
                val size by animateDpAsState(
                    targetValue = if (pagerState.currentPage == i) dp16 else dp8,
                    label = "",
                )
                Box(
                    modifier =
                        Modifier
                            .padding(dp4)
                            .clip(CircleShape)
                            .background(color)
                            .size(size),
                )
            }
        }
    }
}

@Composable
fun PagerItem(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes textRes: Int,
) = Column(
    modifier =
        modifier
            .fillMaxSize()
            .padding(dp24),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
) {
    Image(
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = ratio_1)
                .clip(CircleShape),
        painter = painterResource(id = imageRes),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
    )
    Text(
        text = stringResource(id = textRes),
        style = titleTextStyle,
    )
}

@Composable
fun GoToDateItem(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    secretCode: String,
    onClick: () -> Unit,
) = Column(
    modifier =
        modifier
            .windowInsetsPadding(WindowInsets.ime)
            .fillMaxSize()
            .padding(horizontal = dp24),
    verticalArrangement = Arrangement.Center,
) {
    Text(
        text = stringResource(R.string.ask_your_partner_for_secret_code),
        style = instructionTextStyle,
    )
    CustomTextField(
        modifier = Modifier.padding(vertical = dp24),
        text = secretCode,
        onValueChange = onValueChange,
    )
    Button(
        modifier =
            Modifier
                .fillMaxWidth(),
        onClick = onClick,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = textColor,
            ),
        elevation =
            ButtonDefaults.buttonElevation(
                defaultElevation = dp2,
                pressedElevation = dp8,
            ),
        contentPadding = PaddingValues(dp16),
    ) {
        Text(
            text = stringResource(R.string.start_the_journey),
            style = buttonTextStyle,
        )
    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier =
            modifier
                .height(dp64)
                .background(
                    color = textColor,
                    shape = RoundedCornerShape(dp32),
                )
                .border(
                    width = dp2,
                    color = borderColor,
                    shape = RoundedCornerShape(dp32),
                )
                .padding(horizontal = dp24),
        contentAlignment = Alignment.Center,
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(backgroundColor),
            textStyle = textFieldTextStyle,
            singleLine = true,
        )
        if (text.isEmpty()) {
            Text(
                text = stringResource(id = R.string.enter_secret_code),
                style = textFieldTextStyle,
            )
        }
    }
}
