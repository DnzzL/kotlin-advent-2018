package tech.thomaslegrand.advent2018

/**
 * Day 1: Chronal Calibration
 *
 * Problem Description: http://adventofcode.com/2018/day/1
 */

fun main(args: Array<String>) {
    val rawInput = Resources.resourceAsList("Day01.txt")
    val input = toListOfInt(rawInput)
    val dayOne = Day01(input)

    val solvePartOne = dayOne.solvePartOne()
    val solvePartTwo = dayOne.solvePartTwo()
    println("Day01.txt:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}

private fun toListOfInt(rawInput: List<String>): List<Int> {
    return rawInput
        .map {it.toInt()}
}

class Day01(val input: List<Int>) {

    fun solvePartOne(): Int =
        input.sum()

    fun solvePartTwo(): Int {
        var frequencies = mutableSetOf<Int>()
        var sum = 0
        val finiteIntSequence = input.asSequence()
        val infiniteIntSequence = finiteIntSequence.repeat()
        infiniteIntSequence
            .map { sum += it
                sum
            }
            .first { !frequencies.add(it) }
        return sum
    }
}