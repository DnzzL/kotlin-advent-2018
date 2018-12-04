package tech.thomaslegrand.advent2018

import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter

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
        var guardsDuties = mutableMapOf<Int, Int?>()
        var currentGuard : Int? = null
        var sleepStart : Int? = null
        input
            .forEach { log ->
                when {
                    log.contains("Guard") -> currentGuard = extractId(log)
                    log.contains("asleep") -> sleepStart = extractMinutes(log)
                    log.contains("wakes up") -> {
                        val maxSleepTime = guardsDuties.getOrDefault(currentGuard!!, 0)
                        val wakeUpMinute = extractMinutes(log)
                        val sleepTime = wakeUpMinute - sleepStart!!
                        if (sleepTime > maxSleepTime!!) guardsDuties[currentGuard!!] = sleepStart
                    }
                }
            }
        val sortedGuardsDuties = guardsDuties.toList().sortedBy { it.second }
        println(sortedGuardsDuties)
        return sortedGuardsDuties.last().first * sortedGuardsDuties.last().second!!
    }

    fun solvePartTwo(): Int {
        return 0
    }

    private fun extractId(log:String): Int =
        log.substringAfter("#").substringBefore(" ").toInt()

    private fun extractMinutes(log: String): Int =
        log.substringAfter(":").substringBefore("]").toInt()
}
