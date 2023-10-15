package de.atennert.log

import kotlinx.cinterop.ExperimentalForeignApi
import log4c.log4c_category_debug
import log4c.log4c_category_get
import log4c.log4c_fini
import log4c.log4c_init

@OptIn(ExperimentalForeignApi::class)
fun main() {
    log4c_init()

    val logCat = log4c_category_get("de.atennert.log")
    log4c_category_debug(logCat, "Hello KKON")

    log4c_fini()
}