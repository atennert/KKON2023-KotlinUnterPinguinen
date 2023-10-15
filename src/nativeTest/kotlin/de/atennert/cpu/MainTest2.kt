package de.atennert.cpu

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTest2 : StringSpec({
    afterTest { wrapPrintln = ::println }

    "check for the right output" {
        var output = ""
        wrapPrintln = { output += it.toString() }

        main(arrayOf("-f", "$RESOURCE_PATH/testStat"))

        // 100 * (1 - 420 / 640) = 100 - 65.625 = 34.375
        output shouldBe "34.375%"
    }
})