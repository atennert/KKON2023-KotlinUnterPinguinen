package de.atennert.ktor

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.cinterop.nativeHeap

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello KKON")
        }
    }
}
