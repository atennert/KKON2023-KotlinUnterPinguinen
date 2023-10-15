// https://kotlinlang.org/docs/native-dynamic-libraries.html#create-a-kotlin-library

object Object {
    val field = "A"
}

class Clazz {
    fun memberFunction(p: Int): ULong = 42UL
}

fun forIntegers(b: Byte, s: Short, i: UInt, l: Long) { }
fun forFloats(f: Float, d: Double) { }

fun strings(str: String) : String? {
    return "That is '$str' from C"
}

/**
 * Yay, a comment
 */
val globalString = "A global String"
