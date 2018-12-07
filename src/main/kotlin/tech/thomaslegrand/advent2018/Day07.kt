package tech.thomaslegrand.advent2018

import java.util.*
import kotlin.math.abs


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
        val nodes = setOf(tree.keys).plus(setOf(tree.values))
        // Create a map of node, children
        input
            .map { parse(it) }
            .map {
                tree.merge(it.first, setOf(it.second)) { a, b -> a + b }
            }
        //toposort(graph).reversed().flatten().joinToString("")

        var correctOrder = "C"
        // Count dependencies for each letter and sort by dependency and alphabetical
        val grouped = tree.values
            .flatten()
            .groupingBy { it }.eachCount().toMutableMap()
        tree.keys.minus(tree.values.flatten()).map { grouped.put(it, 0) }
        val sortedTree = tree.toList().sortedWith(compareBy({ it.second.size }, { it.first })).toMap()
        val start = "C"
        val currentNode = tree.get(start)!!.toMutableSet()
        while (correctOrder.length < nodes.size) {
            correctOrder = correctOrder.plus(currentNode?.find { !it.hasOtherDepencies(tree) })
            tree.
        }
        return correctOrder
    }

    fun solvePartTwo(): Int {
        return 0
    }

    private fun String.hasOtherDepencies(tree: MutableMap<String, Set<String>>): Boolean {
        return tree.keys.any { it.contains(this) }
    }

    private fun parse(step: String): Pair<String, String> {
        val before = step.substringAfter("Step ").substringBefore(" ").toCharArray().first()
        val after = step.substringAfter("step ").substringBefore(" ").toCharArray().first()
        return Pair(before.toString(), after.toString())
    }

}

// Comes from Rosetta Code
fun toposort(graph: Map<String, Set<String>>): List<List<String>> {
    var data = graph.map { (k, v) -> k to v.toMutableSet() }.toMap().toMutableMap()

    // ignore self dependancies
    data = data.map { (k, v) -> v.remove(k); k to v }.toMap().toMutableMap()

    val extraItemsInDeps = data.values.reduce { a, b -> a.union(b).toMutableSet() } - data.keys.toSet()

    data.putAll(extraItemsInDeps.map { it to mutableSetOf<String>() }.toMap())

    val res = mutableListOf<List<String>>()
    mainloop@ while (true) {
        innerloop@ while (true) {
            val ordered = data.filter { (_, v) -> v.isEmpty() }.map { (k, _) -> k }.sorted()
            if (ordered.isEmpty())
                break@innerloop

            res.add(ordered.sorted())
            data = data.filter { (k, _) -> !ordered.contains(k) }.map { (k, v) -> v.removeAll(ordered); k to v }.toMap()
                .toMutableMap()
        }

        if (data.isNotEmpty())
            throw Exception("A cyclic dependency exists amongst: ${data.toList().joinToString { "," }}")
        else
            break@mainloop
    }

    return res
}