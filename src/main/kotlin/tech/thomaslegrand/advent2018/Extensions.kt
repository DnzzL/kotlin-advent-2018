package tech.thomaslegrand.advent2018

/**
 * Infinitely repeats T elements in a Sequence<T>
 */
fun <T> List<T>.repeat(): Sequence<T> = sequence {
    if (this@repeat.isEmpty()) return@sequence
    while (true) yieldAll(this@repeat)
}
