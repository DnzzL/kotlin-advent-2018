package tech.thomaslegrand.advent2018


/**
 * Day 8: Memory Maneuver
 *
 * Problem Description: http://adventofcode.com/2018/day/8
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsString("Day08.txt")
    val numbers = input.split(' ').map { it.toInt() }
    val tree = parse(numbers.iterator())
    val day = Day08(tree)

    val solvePartOne = day.solvePartOne()
    val solvePartTwo = day.solvePartTwo()
    println("Day08:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}

class Day08(private val tree: Node) {

    fun solvePartOne(): Int {
        return tree.metadataSum()
    }

    fun solvePartTwo(): Int {
        return tree.value()
    }

}

fun parse(numbers: Iterator<Int>): Node {
    val numberChildren = numbers.next()
    val numberMetadata = numbers.next()
    return Node(List(numberChildren) { parse(numbers) }, List(numberMetadata) {numbers.next()})
}

class Node(private val childNodes: List<Node>, private val metadata: List<Int>) {
    fun metadataSum(): Int = metadata.sum() + childNodes.sumBy { it.metadataSum() }

    fun value(): Int =
        if (childNodes.isEmpty()) metadata.sum()
        else metadata.sumBy { childNodes.getOrNull(it - 1)?.value() ?: 0 }
}