package tech.thomaslegrand.advent2018

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.DisplayName

@DisplayName("Day03")
internal class Day03Test {

    private val input = Resources.resourceAsList("Day03_Example.txt")
    private val day = Day03(input)

    @org.junit.jupiter.api.Test
    fun solvePartOne() {
        assertEquals(day.solvePartOne(), 4)
    }

    @org.junit.jupiter.api.Test
    fun solvePartTwo() {
        assertEquals(day.solvePartTwo(), 3)
    }
}