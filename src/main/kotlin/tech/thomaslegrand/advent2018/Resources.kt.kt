/**
 * Copyright (c) 2018 by Todd Ginsberg
 */

package tech.thomaslegrand.advent2018

import java.io.File

internal object `Resources.kt` {
    fun resourceAsString(fileName: String, delimiter: String = ""): String =
        File(`Resources.kt`.javaClass.classLoader.getResource(fileName).toURI())
            .readLines()
            .reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsList(fileName: String): List<String> =
        File(`Resources.kt`.javaClass.classLoader.getResource(fileName).toURI())
            .readLines()
}
