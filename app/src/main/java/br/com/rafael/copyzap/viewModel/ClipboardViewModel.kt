package br.com.rafael.copyzap.viewModel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel

class ClipboardViewModel(private val app: Application) : AndroidViewModel(app) {
    var clipboardText by mutableStateOf<String?>(null)
    lateinit var clipboard: ClipboardManager

    init {
        clipboard = (getApplication() as Application).baseContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        with(clipboard) {
            addPrimaryClipChangedListener {
                clipboardText = primaryClip?.getItemAt(0)?.toString()
            }
        }
    }

    fun getClipboard() {
        val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
        clipboardText = item?.text.toString()
    }
}

