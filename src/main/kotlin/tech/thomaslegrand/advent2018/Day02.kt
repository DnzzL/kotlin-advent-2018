package tech.thomaslegrand.advent2018


/**
 * Day 2: Inventory Management System
 *
 * Problem Description: http://adventofcode.com/2018/day/2
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day02.txt")
    val dayTwo = Day02(input)

    val solvePartOne = dayTwo.solvePartOne()
    val solvePartTwo = dayTwo.solvePartTwo()
    println("Day02:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day02(val input: List<String>) {

    fun solvePartOne(): Int {
        val counts = input.map {
            it.groupingBy { it }.eachCount()
        }.map {
            Pair(
                it.any { it.value == 2 },
                it.any { it.value == 3 }
            )
        }
        return counts.count { it.first } * counts.count { it.second }
    }

    fun solvePartTwo(): String {
        val pairs = input.map { i ->
            Pair(i,
                input.find { j ->
                    isMatchingPair(i, j)
                }
            )
        }.first { it.second != null }
        val commonLetters = pairs.first.zip(pairs.second!!).filter { it.first == it.second }
        return commonLetters.map { it.first }.joinToString(separator = "")
    }

    private fun isMatchingPair(i: String, j: String): Boolean {
        val diff = i.zip(j).filter { it.first != it.second }
        return diff.size == 1
    }
}