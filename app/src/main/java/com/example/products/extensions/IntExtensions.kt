package com.example.products.extensions

import java.text.NumberFormat

// fun Int?.toMoneyFormat(): String {
//    val format = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
//    format.maximumFractionDigits = 0
//    return if (this == null) format.format(0.0) else format.format(this.toDouble())
//}
fun Int.toMoneyFormat(): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(this.toDouble())
}