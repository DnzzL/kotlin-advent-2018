package tech.thomaslegrand.advent2018

import org.junit.Assert
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day05Test {

    private val input = "dabAcCaCBAcCcaDA"
    private val day = Day05(input)

    @Test
    fun solvePartOne() {
        Assert.assertEquals(day.solvePartOne(), "dabCBAcaDA".length)
    }

    @Test
    fun solvePartTwo() {
        Assert.assertEquals(day.solvePartTwo(), 4)
    }
}