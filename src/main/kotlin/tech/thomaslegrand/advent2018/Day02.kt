package tech.thomaslegrand.advent2018

/**
 * Day 2: Inventory Management System
 *
 * Problem Description: http://adventofcode.com/2018/day/2
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day02.txt")
    val dayOne = Day02(input)

    val solvePartOne = dayOne.solvePartOne()
    // val solvePartTwo = dayOne.solvePartTwo()
    println("Day02:")
    println("  Part I:  $solvePartOne")
    // println("  Part II:  $solvePartTwo")
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

    fun solvePartTwo(): Int {
        input.map {
            it.groupingBy { it }
        }
        return 0
    }
}