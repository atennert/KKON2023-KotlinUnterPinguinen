package de.atennert.cpu

import kotlinx.cinterop.*
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.getline

fun main(args: Array<String>) {
    val statFile = readFilenameOrDefault(args, "/proc/stat")

    readLineFromFileWhile(statFile) { lineData ->
        if (lineData?.startsWith("cpu ") != true) {
            return@readLineFromFileWhile true
        }

        printCpuUtilizationFromLine(lineData)
        false
    }
}

fun readFilenameOrDefault(args: Array<String>, defaultPath: String): String {
    val filenameIndex = args.indexOfFirst { it == "-f" }
    return if (filenameIndex > -1) {
        args[filenameIndex + 1]
    } else {
        defaultPath
    }
}

fun computeCpuUtilization(lineData: String): Double {
    val cpuData = lineData.trim()
        .split(' ')
        .drop(1)
        .map(String::toFloat)

    val idleTime = cpuData[3] + cpuData[4]
    val totalTime = cpuData.sum()
    val cpuUtilizationPercent = 100 * (1.0 - idleTime / totalTime)
    return cpuUtilizationPercent
}

@OptIn(ExperimentalForeignApi::class)
fun readLineFromFileWhile(filePath: String, useLine: (lineData: String?) -> Boolean) {
    val file = fopen(filePath, "r")

    val line = nativeHeap.allocPointerTo<ByteVar>()
    val n = nativeHeap.alloc(0.toULong())
    while (getline(line.ptr, n.ptr, file) != -1L) {
        val lineData = line.value?.toKString()?.trim()
        if (!useLine(lineData)) {
            break
        }
    }
    nativeHeap.free(line)
    nativeHeap.free(n)

    fclose(file)
}

fun printCpuUtilizationFromLine(lineData: String) {
    val cpuUtilizationPercent = computeCpuUtilization(lineData)

    wrapPrintln("$cpuUtilizationPercent%")
}
