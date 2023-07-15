package com.priyo.core.extentions

val String?.toIntOrZero: Int get() = this?.toIntOrNull() ?: 0
