#!/usr/bin/env bash

# https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core/1.7.3/kotlinx-coroutines-core-1.7.3.module
# https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-linuxx64/1.7.3/
# https://repo1.maven.org/maven2/org/jetbrains/kotlinx/atomicfu/0.21.0/atomicfu-0.21.0.module
# https://repo1.maven.org/maven2/org/jetbrains/kotlinx/atomicfu-linuxx64/0.21.0/

~/Downloads/kotlin-native-linux-x86_64-1.9.0/bin/cinterop \
  -def src/nativeInterop/cinterop/log4c.def \
  -o log4c

find ./src/nativeMain/kotlin -name '*.kt' | xargs \
  ~/Downloads/kotlin-native-linux-x86_64-1.9.0/bin/kotlinc-native \
  -l log4c \
#  -l ~/Downloads/kotlinx-coroutines-core-linuxx64-1.7.3.klib \
#  -l ~/Downloads/atomicfu-linuxx64-0.21.0.klib \
#  -l ~/Downloads/atomicfu-linuxx64-0.21.0-cinterop-interop.klib \
  -o log-example \
  -e de.atennert.log.main
#  -linker-options "-L/usr/lib -L/usr/lib/x86_64-linux-gnu -llog4c" \
