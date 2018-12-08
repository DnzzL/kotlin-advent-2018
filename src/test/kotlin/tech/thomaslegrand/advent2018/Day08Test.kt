package tech.thomaslegrand.advent2018

import org.junit.Assert
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day08Test {

    private val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
    private val numbers = input.split(' ').map { it.toInt() }
    private val tree = parse(numbers.iterator())
    private val day = Day08(tree)

    @Test
    fun solvePartOne() {
        Assert.assertEquals(day.solvePartOne(), 138)
    }

    @Test
    fun solvePartTwo() {
        Assert.assertEquals(day.solvePartTwo(), 66)
    }
}