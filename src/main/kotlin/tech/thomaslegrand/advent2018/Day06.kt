package tech.thomaslegrand.advent2018

import kotlin.math.abs

/**
 * Day 6: Chronal Coordinates
 *
 * Problem Description: http://adventofcode.com/2018/day/6
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day06.txt")
    val day = Day06(input)

    val solvePartOne = day.solvePartOne()
    val solvePartTwo = day.solvePartTwo()
    println("Day06:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day06(val input: List<String>) {

    fun solvePartOne(): Int {
        val listCoordinates = input.map {
            Point(it.substringBefore(",").toInt(), it.substringAfter(" ").toInt())
        }
        return (listCoordinates.minBy { it.x }!!.x..listCoordinates.maxBy { it.x }!!.x).flatMap { x ->
            (listCoordinates.minBy { it.y }!!.y..listCoordinates.maxBy { it.y }!!.y).map { y ->
                Pair(x, y)
            }.toList()
        }.map {
            Point(it.first, it.second).closestPoint(listCoordinates)
        }.filter {
            it.isSurrounded(listCoordinates)
        }.groupingBy { it }.eachCount().also { println(it) }.maxBy { it.value }!!.value
    }

    fun solvePartTwo(): Int {
        return 0
    }

}

data class Point(val x: Int, val y: Int)

private fun Point.manhattanDistanceTo(coord2: Point): Int {
    return abs(this.x - coord2.x) + abs(this.y - coord2.y)
}

private fun Point.closestPoint(listPoints: List<Point>): Point {
    return listPoints.minBy { this.manhattanDistanceTo(it) }!!
}

private fun Point.isSurrounded(listPoints: List<Point>): Boolean {
    val xSurrounded = listPoints.any { it.x < this.x } and listPoints.any { it.x > this.x }
    val ySurrounded = listPoints.any { it.y < this.y } and listPoints.any { it.y > this.y }
    return xSurrounded and ySurrounded
}