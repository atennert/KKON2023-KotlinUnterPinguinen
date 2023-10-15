# Kotlin unter Pinguinen (Kotlin among penguins)

This repository contains the code examples for the presentation *Kotlin unter Pinguinen* from Rheinwerk KKON 2023.

Build the examples using `./gradlew build`.

## Examples

### **Main.kt**

Not really a special example ... just the result of generating the project.

### de.atennert.csvFilter

A Kotlin/Native application that doesn't use native code. Command line application to filter CSV files.

See *filter.sh* for using the application.

### de.atennert.cpu

A Kotlin/Native application that uses functions from the POSIX API to read files. It's a basic `CPointer` example.

Has also tests in the test folder.

### de.atennert.log

A Kotlin/Native application that demonstrates how to use external native libraries.

See *log4c.def*

Requires log4c to be installed.

### de.atennert.gtk

A Kotlin/Native application that shows how to use GTK with Kotlin/Native. This uses some more complex `CPointer` things.

See *gtk.def*

Requires GTK to be installed.

### de.atennert.ktor

Simple web server application. Shows that Kotlin libraries like Ktor can work with Kotlin/Native.

It has also a test.

### MyLib.kt

Example for building a library. This is the example from https://kotlinlang.org/docs/native-dynamic-libraries.html.

### compile.sh

Example for compiling an executable manually. Needs the kotlin compiler from https://github.com/JetBrains/kotlin/releases.
