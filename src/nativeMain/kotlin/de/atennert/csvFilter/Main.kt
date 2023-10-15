package de.atennert.csvFilter

fun main(args: Array<String>) {
    val firstCSVLine = readlnOrNull() ?: return

    val indices = mutableListOf<Int>()
    indices.addAll(readIndices(args))
    indices.addAll(readColumnNameIndices(args, firstCSVLine))

    var csvLine = firstCSVLine
    while (true) {
        val csvOutputLine = filterCSVLine(csvLine, indices)
        println(csvOutputLine)

        csvLine = readlnOrNull() ?: break
    }
}

fun readIndices(args: Array<String>): List<Int> {
    return args.indices
        .filter { index -> index % 2 == 0 }
        .filter { index -> args[index] == "-i" }
        .map { index -> args[index + 1].toInt() }
}

fun readColumnNameIndices(
    args: Array<String>, firstCSVLine: String
): List<Int> {
    val names = firstCSVLine.split(',')

    return args.indices
        .filter { index -> index % 2 == 0 }
        .filter { index -> args[index] == "-n" }
        .map { index -> args[index + 1] }
        .map { name -> names.indexOf(name) }
}

fun filterCSVLine(csvLine: String, indices: List<Int>): String {
    return if (indices.isEmpty()) {
        csvLine
    } else {
        csvLine.split(',')
            .filterIndexed { index, _ -> indices.contains(index) }
            .joinToString(",")
    }
}