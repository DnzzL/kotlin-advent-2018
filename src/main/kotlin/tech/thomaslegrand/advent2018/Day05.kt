package tech.thomaslegrand.advent2018

/**
 * Day 5: Alchemical Reduction
 *
 * Problem Description: http://adventofcode.com/2018/day/5
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsString("Day05.txt")
    val day = Day05(input)

    val solvePartOne = day.solvePartOne()
    val solvePartTwo = day.solvePartTwo()
    println("Day05:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day05(val input: String) {

    fun solvePartOne(): Int {
        var remainingPolymer = input
        while (isRemainingReaction(remainingPolymer)) {
            remainingPolymer = doReactions(remainingPolymer)
        }
        return remainingPolymer.length
    }

    fun solvePartTwo(): Int {
        val polymerSizes = mutableListOf<Int>()
        ('A'..'Z')
            .map {
                var remainingPolymer = input
                while (isRemainingReaction(remainingPolymer)) {
                    remainingPolymer = doReactions(remainingPolymer, it)
                }
                polymerSizes.add(remainingPolymer.length)
            }
        return polymerSizes.min()!!
    }

    private fun isReaction(a: Char, b: Char): Boolean =
        when {
            a.isLowerCase() and b.isUpperCase() -> a.toLowerCase() == b.toLowerCase()
            a.isUpperCase() and b.isLowerCase() -> a.toLowerCase() == b.toLowerCase()
            else -> false
        }


    private fun isRemainingReaction(polymer: String): Boolean =
        polymer.asSequence().zipWithNext().any {
            isReaction(it.first, it.second)
        }

    private fun doReactions(polymer: String, ignoring: Char? = null): String {
        var remainingPolymer =
            polymer.replace(ignoring?.toLowerCase().toString(), "").replace(ignoring?.toUpperCase().toString(), "")
        val letters = input.toCharArray().map { it.toLowerCase() }.toSet()
        letters.map {
            remainingPolymer = remainingPolymer.replace(it.toString().plus(it.toUpperCase()), "")
            remainingPolymer = remainingPolymer.replace(it.toUpperCase().plus(it.toString()), "")
        }

        return remainingPolymer
    }
}
