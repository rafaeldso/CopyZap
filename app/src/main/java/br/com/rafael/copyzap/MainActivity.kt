package br.com.rafael.copyzap

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rafael.copyzap.ui.component.CopyZapApp
import br.com.rafael.copyzap.ui.component.ZapContent
import br.com.rafael.copyzap.ui.theme.CopyZapTheme
import br.com.rafael.copyzap.viewModel.ClipboardViewModel


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ClipboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopyZapTheme {
                CopyZapApp(viewModel)
            }
        }

    }
   /* @Preview
    @Composable@Preview(showBackground = true, widthDp = 1024, heightDp = 600)
    fun PreviewContent() {
        CopyZapTheme {
           // Surface(modifier = Modifier.fillMaxSize()) {
                ZapContent({}, getClipboard = { "" }, value = "")
           // }
        }
    }*/

}