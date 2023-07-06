package br.com.rafael.copyzap.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import br.com.rafael.copyzap.R
import br.com.rafael.copyzap.ui.theme.TEAL_GREEN_LIGHT
import br.com.rafael.copyzap.viewModel.ClipboardViewModel


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    clipboardViewModel: ClipboardViewModel,
    onBackToStack: () -> Unit = {}
) {
    Result(clipboardViewModel, onBackToStack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Result(
    clipboardViewModel: ClipboardViewModel,
    onBackToStack: () -> Unit = {}
) {
    if (clipboardViewModel.isShowLoading && clipboardViewModel.formatText.isBlank()) {

        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            CircularProgressIndicator(color = TEAL_GREEN_LIGHT)
        }
    } else {
        if (clipboardViewModel.formatText.isBlank()) {
            clipboardViewModel.formatText()
            clipboardViewModel.isShowLoading(true)
        } else {
            val clipboardManager: ClipboardManager = LocalClipboardManager.current
            clipboardViewModel.isShowLoading(false)
            if (clipboardViewModel.isError){
                val context = LocalContext.current
                Toast.makeText(
                    context,
                    context.getString(R.string.cz_error_result),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Column(
                horizontalAlignment = CenterHorizontally, modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                TextField(
                    value = clipboardViewModel.formatText,
                    onValueChange = { clipboardViewModel.formatText },
                    readOnly = true,
                    label = { Text(text = stringResource(R.string.cz_result)) },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .weight(2f)
                )
                AdvertView(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .weight(0.5f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(
                        onClick = { onBackToStack() },
                        Modifier
                            .height(IntrinsicSize.Min)
                            .padding(start = 4.dp),
                        colors = ButtonDefaults.textButtonColors(contentColor = TEAL_GREEN_LIGHT)
                    ) {
                        Text(stringResource(R.string.cz_back))
                    }
                    Button(
                        onClick = {
                            clipboardManager.setText(AnnotatedString((clipboardViewModel.formatText)))
                        },
                        colors = ButtonDefaults.buttonColors(TEAL_GREEN_LIGHT),
                        modifier =
                        Modifier
                            .height(IntrinsicSize.Min)
                            .padding(start = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cz_ic_content_copy),
                            contentDescription = stringResource(R.string.cz_copy),
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(stringResource(R.string.cz_copy))
                    }
                }
            }
        }
    }
}
