package de.atennert.cpu

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

const val RESOURCE_PATH = "./src/nativeTest/resources"

class MainTest {
    private var output = ""

    @BeforeTest
    fun setup() {
        output = ""
        wrapPrintln = { output += it.toString() + "\n" }
    }


    @AfterTest()
    fun teardown() {
        wrapPrintln = ::println
    }

    @Test
    fun `print accumulated CPUutilization` ( ) {
        main(arrayOf("-f", "$RESOURCE_PATH/testStat"))

        // 100 * (1 - 420 / 640) = 100 - 65.625 = 34.375
        assertEquals("34.375%\n", output)
    }
}
