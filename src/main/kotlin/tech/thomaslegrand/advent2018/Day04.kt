package tech.thomaslegrand.advent2018

/**
 * Day 4: Repose Record
 *
 * Problem Description: http://adventofcode.com/2018/day/4
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day04.txt")
    val day = Day04(input)

    val solvePartOne = day.solvePartOne()
    val solvePartTwo = day.solvePartTwo()
    println("Day04:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day04(val input: List<String>) {

    fun solvePartOne(): Int {
        val mapGuardsSleptMinutes = getSleptMinutesPerGuard()
        return mapGuardsSleptMinutes.maxBy { it.value.size }
            .run { this!!.key * value.groupBy { it }.maxBy { it.value.size }?.key!! }
    }

    fun solvePartTwo(): Int {
        val mapGuardsSleptMinutes = getSleptMinutesPerGuard()
        return mapGuardsSleptMinutes
            .flatMap { el ->
                el.value.map { minute ->
                    el.key to minute
                }
            }
            .groupBy { it }.maxBy { it.value.size }?.key!!
            .run { first * second }
    }

    private fun getSleptMinutesPerGuard(): MutableMap<Int, List<Int>> {
        val mapGuardsSleptMinutes = mutableMapOf<Int, List<Int>>()
        var currentGuard = 0
        var sleepStart = 0
        input
            .sorted()
            .forEach { log ->
                when {
                    log.contains("Guard") -> currentGuard = extractId(log)
                    log.contains("asleep") -> sleepStart = extractMinutes(log)
                    else -> {
                        val wakeUpMinute = extractMinutes(log)
                        val sleptMinutes = (sleepStart until wakeUpMinute).toMutableList()
                        mapGuardsSleptMinutes.merge(currentGuard, sleptMinutes) { a, b -> a + b }
                    }
                }
            }
        return mapGuardsSleptMinutes
    }

    private fun extractId(log: String): Int =
        log.substringAfter("#").substringBefore(" ").toInt()

    private fun extractMinutes(log: String): Int =
        log.substringAfter(":").substringBefore("]").toInt()
}
