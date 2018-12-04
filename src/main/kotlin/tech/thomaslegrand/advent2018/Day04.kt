package tech.thomaslegrand.advent2018

/**
 * Day 4: Repose Record
 *
 * Problem Description: http://adventofcode.com/2018/day/4
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day04.txt")
    val dayFour = Day04(input)

    val solvePartOne = dayFour.solvePartOne()
    val solvePartTwo = dayFour.solvePartTwo()
    println("Day03:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day04(val input: List<String>) {

    fun solvePartOne(): Int {
        var mapGuardsSleptMinutes = mutableMapOf<Int, MutableList<Int>>()
        var currentGuard : Int? = null
        var sleepStart : Int? = null
        input
            .forEach { log ->
                when {
                    log.contains("Guard") -> currentGuard = extractId(log)
                    log.contains("asleep") -> sleepStart = extractMinutes(log)
                    log.contains("wakes up") -> {
                        val wakeUpMinute = extractMinutes(log)
                        val sleptMinutes = (sleepStart!! until wakeUpMinute).toMutableList()
                        val guardSleepTimes = mapGuardsSleptMinutes.getOrPut(currentGuard!!) { sleptMinutes }
                        mapGuardsSleptMinutes[currentGuard!!] = (sleptMinutes + guardSleepTimes).toMutableList()
                    }
                }
            }
        val sortedGuardsSleptMinutes = mapGuardsSleptMinutes.toList().sortedBy { (_, sleepTimes) -> sleepTimes.sum() }
        val mostAsleepGuardId = sortedGuardsSleptMinutes.first().first
        val mostAsleepMinute = sortedGuardsSleptMinutes.first().second.groupingBy { it }.eachCount().maxBy { it.value }!!.key
        return mostAsleepGuardId * mostAsleepMinute
    }

    fun solvePartTwo(): Int {
        return 0
    }

    private fun extractId(log:String): Int =
        log.substringAfter("#").substringBefore(" ").toInt()

    private fun extractMinutes(log: String): Int =
        log.substringAfter(":").substringBefore("]").toInt()
}
