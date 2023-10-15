plugins {
    kotlin("multiplatform") version "1.9.0"
    id("io.kotest.multiplatform") version "5.7.2"
}

group = "de.atennert"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs == "Linux" && isArm64 -> linuxArm64("native")
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    // this attribute is used to differentiate between the executable and the library builds ... prevents build errors
    val myAttribute = Attribute.of("de.atennert.ref", String::class.java)

    nativeTarget.apply {
        attributes.attribute(myAttribute, "executable")
        binaries {
            executable("main") {
                entryPoint = "main"
            }
            executable("log") {
                entryPoint = "de.atennert.log.main"
            }
            executable("gtk-app") {
                entryPoint = "de.atennert.gtk.main"
            }
            executable("csv-filter") {
                entryPoint = "de.atennert.csvFilter.main"
            }
            executable("cpu") {
                entryPoint = "de.atennert.cpu.main"
            }
            executable("ktor") {
                entryPoint = "de.atennert.ktor.main"
            }
            compilations.getByName("main") {
                val log4c by cinterops.creating {}
                val gtk by cinterops.creating {}
            }
        }
    }

    val libTarget = linuxX64("lib")
    libTarget.apply {
        attributes.attribute(myAttribute, "library")
        binaries {
            sharedLib("mylib") {}
            staticLib("mylib2") {}
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.ktor:ktor-server-core:2.3.4")
                implementation("io.ktor:ktor-server-cio:2.3.4")
                implementation("ch.qos.logback:logback-classic:1.4.11")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
        commonTest {
            dependencies {
                implementation("io.kotest:kotest-assertions-core:5.7.2")
                implementation("io.kotest:kotest-framework-engine:5.7.2")
                implementation("io.ktor:ktor-server-tests:2.3.4")
            }
        }
        val nativeMain by getting
        val nativeTest by getting

        val libMain by getting
        val libTest by getting
    }
}
