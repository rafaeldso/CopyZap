package br.com.rafael.copyzap.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private const val SEPARATOR_IDENTIFIER =  "ʭ§"
private const val COLON =  ":"
class ClipboardViewModel : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var clipboardText by mutableStateOf("")

    var isShowLoading by mutableStateOf(false)

    var formatText by mutableStateOf("")

    var isError by mutableStateOf(false)

    fun setZapValue(text: String) {
        if (text.lowercase() != "null")
            clipboardText = text
    }

    fun isShowLoading(isShow: Boolean) {
        isShowLoading = isShow
    }

    fun isError(isErrorValue: Boolean) {
        isError = isErrorValue
    }

    fun formatText() {
        scope.launch {
            val text = clipboardText
            try {
                if (text.isNotBlank()) {
                    formatText = removeAllPhoneNumberOrContactName(
                        text.replace(
                            Regex(
                                "\\[\\d/\\d\\s\\d\\d:\\d\\d\\]",
                                RegexOption.IGNORE_CASE
                            ), SEPARATOR_IDENTIFIER
                        ).replace(
                            Regex(
                                "\\[\\d/\\d\\d\\s\\d\\d:\\d\\d\\]",
                                RegexOption.IGNORE_CASE
                            ), SEPARATOR_IDENTIFIER
                        ).replace(
                            Regex(
                                "\\[\\d\\d/\\d\\s\\d\\d:\\d\\d\\]",
                                RegexOption.IGNORE_CASE
                            ), SEPARATOR_IDENTIFIER
                        ).replace(
                            Regex(
                                "\\[\\d\\d/\\d\\d\\s\\d\\d:\\d\\d\\]",
                                RegexOption.IGNORE_CASE
                            ), SEPARATOR_IDENTIFIER
                        ).replace(
                            Regex(
                                "\\[\\d\\d:\\d\\d,\\s\\d\\d/\\d\\d/\\d\\d\\d\\d\\]",
                                RegexOption.IGNORE_CASE
                            ), SEPARATOR_IDENTIFIER
                        )
                    )
                }
            } catch (exception : Exception){
                isError = true
                formatText = clipboardText
            }
        }
    }


    private fun removeAllPhoneNumberOrContactName(text: String): String {
        var formatText = text
        while (formatText.indexOf(SEPARATOR_IDENTIFIER) != -1) {
            formatText = removePhoneNumberOrContactName(formatText)
        }
        return formatText
    }

    private fun removePhoneNumberOrContactName(text: String): String {
        val indexBegin = text.indexOf(SEPARATOR_IDENTIFIER)
        val indexEnd = text.indexOf(COLON,indexBegin) + 2
        return text.replace(text.substring(indexBegin, indexEnd), "")
    }
}

