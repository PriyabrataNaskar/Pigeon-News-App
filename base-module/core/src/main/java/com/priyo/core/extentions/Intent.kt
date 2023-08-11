package com.priyo.core.extentions

import android.content.Intent

const val textPlain = "text/plain"
fun Intent.shareText(
    text: String,
    contentType: String = textPlain,
): Intent {
    action = Intent.ACTION_SEND
    type = contentType
    putExtra(
        Intent.EXTRA_TEXT,
        text,
    )
    return this
}
