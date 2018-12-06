package tech.thomaslegrand.advent2018

import org.junit.Assert
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day06Test {

    private val input = Resources.resourceAsList("Day06_Example.txt")
    private val day = Day06(input)

    @Test
    fun solvePartOne() {
        Assert.assertEquals(day.solvePartOne(), 17)
    }

    @Test
    fun solvePartTwo() {
    }
}