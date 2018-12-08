package tech.thomaslegrand.advent2018

import java.util.*
import kotlin.math.abs
import javax.swing.UIManager.put




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
        val tree = mutableMapOf<String, Set<String>>()
        var dependencies = mutableMapOf<String, Int>()
        // Create a map of node, children
        input
            .map { parse(it) }
            .map {
                tree.merge(it.first, setOf(it.second)) { a, b -> a + b }
                dependencies.merge(it.second, 1) { a, b -> a + b }
            }
        // Add letters with no dependencies
        tree.keys.minus(tree.values.flatten()).sorted().map { dependencies.put(it, 0) }

        var correctOrder = ""
        while (dependencies.isNotEmpty()) {
            // Part 1
            val letter = dependencies.filter {
                !it.key.hasDependencies(dependencies)
            }.toSortedMap().keys.first()
            correctOrder = correctOrder.plus(letter)
            dependencies.remove(letter)
            tree[letter]?.map {
                val count = if (dependencies.containsKey(it)) dependencies.get(it) else 0
                dependencies.put(it, count!! - 1)
            }
        }
        return correctOrder
    }

    fun solvePartTwo(): Int {
        return 0
    }

    private fun String.hasDependencies(dependencies: MutableMap<String, Int>): Boolean {
        println(dependencies[this])
        return dependencies[this]!! > 0
    }

    private fun parse(step: String): Pair<String, String> {
        val before = step.substringAfter("Step ").substringBefore(" ").toCharArray().first()
        val after = step.substringAfter("step ").substringBefore(" ").toCharArray().first()
        return Pair(before.toString(), after.toString())
    }

}