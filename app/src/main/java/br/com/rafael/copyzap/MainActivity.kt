package br.com.rafael.copyzap

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.ClipboardManager as ClipboardManagerCompose
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rafael.copyzap.viewModel.ClipboardViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ClipboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ClipboardViewModel(application)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ContentScreen()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getClipboard()
    }

    @Composable
    fun ContentScreen() {
        val clipboardManager: ClipboardManagerCompose = LocalClipboardManager.current
        //val clipBoardText by rememberClipboardText()
        var inputvalue by remember { mutableStateOf(TextFieldValue()) }
        /*ClipboardDialog(
            { text -> if (text?.isBlank() == false) inputvalue = TextFieldValue(text) },
            zapValue = viewModel.clipboardText
        )*/
        ContentEditText(
            { text ->
                if (text?.isBlank() == false)
                    inputvalue = TextFieldValue(text)
            },
            value = inputvalue.text
        )
    }

    @Composable
    fun ContentEditText(
        onSetZapValue: (String?) -> Unit,
        modifier: Modifier = Modifier,
        value: String
    ) {
        val focusRequester = remember { FocusRequester() }
        Column(
            // we are using column to align our
            // imageview to center of the screen.
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .focusRequester(focusRequester),

            // below line is used for specifying
            // vertical arrangement.
            verticalArrangement = Arrangement.Top,

            // below line is used for specifying
            // horizontal arrangement.
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = value,
                    onValueChange = { onSetZapValue(it) },
                    label = { Text(text = "Cole seu texto aqui") },
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .weight(2f)
                        .height(300.dp),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(all = 16.dp)
                        .weight(1.2f)
                ) {
                    Button(
                        onClick = {
                            onSetZapValue(getClipboard())
                        },
                        enabled = getClipboard() != null,
                        modifier =
                        Modifier
                            .width(125.dp)
                            .height(IntrinsicSize.Min)
                            .padding(top = 5.dp, start = 2.dp, end = 2.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cz_ic_content_paste),
                            contentDescription = stringResource(R.string.cz_paste),
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Colar")
                    }
                    Button(
                        onClick = { onSetZapValue("") },
                        Modifier
                            .width(125.dp)
                            .height(IntrinsicSize.Min)
                            .padding(top = 5.dp, start = 2.dp, end = 2.dp)
                    ) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Limpar")

                    }

                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
            if (!value.isNullOrBlank()) {
                Text(
                    text = "Resultado: ",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                )
                SelectionContainer {
                    Text(
                        text = formatText(value),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 20.dp)
                            .fillMaxWidth()
                    )
                }
            }


        }
    }

    @Composable
    private fun ClipboardDialog(
        onSetZapValue: (String?) -> Unit,
        modifier: Modifier = Modifier,
        zapValue: String?
    ) {
        var showDialog by remember { mutableStateOf(true) }
        if (showDialog) {
            val data = zapValue
            if ((data?.isBlank()) == false && data != "null") {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            onSetZapValue(data)
                            showDialog = false
                        }) {
                            Text("Copiar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text(
                                "Cancelar" +
                                        ""
                            )
                        }
                    },
                    title = {
                        Text(
                            text = "Copiar dados da área de transferência",
                            fontSize = 20.sp
                        )
                    },
                    text = { Text(text = "Identificamos dados na sua área de tranferência, deseja realizar a cópia dos dados?") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    private fun getClipboard(): String? {
        val clipboard: ClipboardManager =
            getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
        return item?.text.toString()
    }

    @Preview
    @Composable
    fun PreviewContent() {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                ContentEditText({}, value = "")
            }
        }
    }

    fun formatText(text: String?): String {
        var formatText = ""
        if (!text.isNullOrBlank()) {
            formatText = text.replace(
                Regex(
                    "\\[\\d\\d:\\d\\d,\\s\\d\\d/\\d\\d/\\d\\d\\d\\d\\]\\s(.*)+:\\s",
                    RegexOption.IGNORE_CASE
                ), ""
            )
        }
        return formatText
    }

}