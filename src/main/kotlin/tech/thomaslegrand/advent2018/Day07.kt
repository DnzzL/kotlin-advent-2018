package tech.thomaslegrand.advent2018

import kotlin.math.abs
import java.util.ArrayList


/**
 * Day 7: The Sum of Its Parts
 *
 * Problem Description: http://adventofcode.com/2018/day/7
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day07.txt")
    val day = Day07(input)

    val solvePartOne = day.solvePartOne()
    val solvePartTwo = day.solvePartTwo()
    println("Day07:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day07(val input: List<String>) {

    fun solvePartOne(): String {
        val tree = mutableMapOf<Char, List<Char>>()
        // Create a map of node, children
        input
            .map { parse(it) }
            .map {
                tree.merge(it.first, listOf(it.second)) { a, b -> a + b }
            }
        var correctOrder = tree.keys.minus(tree.values.flatten()).sorted().joinToString("")
        // Count dependencies for each letter and sort by dependency and alphabetical
        tree.also { println(it) }.values
            .flatten()
            .groupingBy { it }.eachCount()
            .toList().sortedWith(compareBy({ it.second }, { it.first }))
            .map { correctOrder = correctOrder.plus(it.first) }
        return correctOrder
    }

    fun solvePartTwo(): Int {
        return 0
    }

    private fun parse(step: String): Pair<Char, Char> {
        val before = step.substringAfter("Step ").substringBefore(" ").toCharArray().first()
        val after = step.substringAfter("step ").substringBefore(" ").toCharArray().first()
        return Pair(before, after)
    }

}