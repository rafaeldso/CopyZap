package br.com.rafael.copyzap.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.ClipboardManager as ClipboardManagerCompose
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ClipboardViewModel : ViewModel() {
    var clipboardText by mutableStateOf("")
   // private var clipboard: ClipboardManager = (getApplication() as Application).baseContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    /*init {
        with(clipboard) {
            addPrimaryClipChangedListener {
                getClipboard()
            }
        }
    }*/

    /*fun getClipboard() {
        val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
        setZapValue(item?.text.toString().orEmpty())
    }*/

    fun setZapValue(text: String) {
        if (text != null && text.lowercase() != "null")
            clipboardText = text
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

    /*private fun getClipboard(): String? {
        val clipboard: ClipboardManager =
            getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
        return item?.text.toString()
    }*/
}

