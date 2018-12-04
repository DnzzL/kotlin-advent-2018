package tech.thomaslegrand.advent2018

import org.junit.Assert
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day04Test {

    private val input = Resources.resourceAsList("Day04_Example.txt")
    private val day = Day04(input)

    @Test
    fun solvePartOne() {
        Assert.assertEquals(day.solvePartOne(), 240)
    }

    @Test
    fun solvePartTwo() {
    }
}