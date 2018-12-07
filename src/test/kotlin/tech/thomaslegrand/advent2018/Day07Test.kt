package tech.thomaslegrand.advent2018

import org.junit.Assert
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day07Test {

    private val input = Resources.resourceAsList("Day07_Example.txt")
    private val day = Day07(input)

    @Test
    fun solvePartOne() {
        Assert.assertEquals(day.solvePartOne(), "HFGABCDE")
    }

    @Test
    fun solvePartTwo() {
    }
}