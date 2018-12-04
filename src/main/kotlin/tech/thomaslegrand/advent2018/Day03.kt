package tech.thomaslegrand.advent2018

/**
 * Day 3: No Matter How You Slice It
 *
 * Problem Description: http://adventofcode.com/2018/day/3
 */

fun main(args: Array<String>) {
    val input = Resources.resourceAsList("Day03.txt")
    val dayThree = Day03(input)

    val solvePartOne = dayThree.solvePartOne()
    val solvePartTwo = dayThree.solvePartTwo()
    println("Day03:")
    println("  Part I:  $solvePartOne")
    println("  Part II:  $solvePartTwo")
}


class Day03(val input: List<String>) {

    fun solvePartOne(): Int =
        input
            .map { Claim(it) }
            .flatMap { it.area() }
            .groupingBy { it }
            .eachCount()
            .count{ it.value > 1}

    fun solvePartTwo(): Int {
        val claims = input.map { Claim(it) }
        return claims
            .find {claim1 ->
                claims.all { claim2 ->
                    !areOverlapping(claim1, claim2)
                }
            }!!.id
    }

    private fun areOverlapping(claim1: Claim, claim2: Claim): Boolean {
        return if (claim1.equals(claim2)) false
        else claim1.area().intersect(claim2.area()).isNotEmpty()
    }

}

class Claim(val claim: String) {
    val id = claim.substringAfter("#").substringBefore(" @").toInt()
    val fromLeft = claim.substringAfter("@ ").substringBefore(",").toInt()
    val fromTop = claim.substringAfter(",").substringBefore(":").toInt()
    val width = claim.substringAfter(": ").substringBefore("x").toInt()
    val height = claim.substringAfter("x").toInt()

    fun area(): List<Pair<Int, Int>> {
        val area = (fromLeft until fromLeft + width).flatMap { x ->
            (fromTop until fromTop + height).map { y ->
                Pair(x, y)
            }.toList()
        }
        return area
    }
}
