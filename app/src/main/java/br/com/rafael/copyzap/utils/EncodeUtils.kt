package br.com.rafael.copyzap.utils

import java.nio.charset.StandardCharsets


private const val HEX = "0123456789ABCDEF"

fun String?.encodeURIComponent(): String? {
    if (this == null) return null
    val bytes = this.toByteArray(StandardCharsets.UTF_8)
    val builder = StringBuilder(bytes.size)
    for (c in bytes) {
        if (if (c >= 'a'.code.toByte()) c <= 'z'.code.toByte() || c == '~'.code.toByte() else if (c >= 'A'.code.toByte()) c <= 'Z'.code.toByte() || c == '_'.code.toByte() else if (c >= '0'.code.toByte()) c <= '9'.code.toByte() else c == '-'.code.toByte() || c == '.'.code.toByte()) builder.append(
            Char(c.toUShort())
        ) else builder.append('%')
            .append(HEX[c.toInt() shr 4 and 0xf])
            .append(HEX[c.toInt() and 0xf])
    }
    return builder.toString()
}