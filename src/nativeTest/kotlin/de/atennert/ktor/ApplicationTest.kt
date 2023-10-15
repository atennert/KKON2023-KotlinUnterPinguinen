package de.atennert.ktor

import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }

        val response = client.get("/")

        response.status shouldBe HttpStatusCode.OK
        response.bodyAsText() shouldBe "Hello KKON"
    }
}