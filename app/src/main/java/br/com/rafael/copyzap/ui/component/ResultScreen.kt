package br.com.rafael.copyzap.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.rafael.copyzap.R
import br.com.rafael.copyzap.viewModel.ClipboardViewModel


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    formatText: String?,
    clipboardViewModel: ClipboardViewModel,
    onBackToStack: () -> Unit = {}
) {
    Result(formatText, clipboardViewModel, onBackToStack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Result(
    formatText: String?,
    clipboardViewModel: ClipboardViewModel,
    onBackToStack: () -> Unit = {}
) {
    if (!formatText.isNullOrBlank()) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            TextField(
                value = formatText,
                onValueChange = { formatText },
                readOnly = true,
                label = { Text(text = stringResource(R.string.cz_result)) },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.End)
            ) {
                TextButton(
                    onClick = { onBackToStack() },
                    Modifier
                        .height(IntrinsicSize.Min)
                        .padding(start = 4.dp)
                ) {
                    Text(stringResource(R.string.cz_back))
                }
                Button(
                    onClick = {
                        clipboardManager.setText(AnnotatedString((formatText)))
                        //Toast.makeText(context, "Texto Copiado!", Toast.LENGTH_LONG).show()
                        //clipboardViewModel.getClipboard()
                    },
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


            /*Text(
                text = stringResource(R.string.cz_result),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            )
            SelectionContainer(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text(
                    text = formatText,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxSize().background(MaterialTheme.colorScheme.primary)
                )
            }*/
        }
    }
}