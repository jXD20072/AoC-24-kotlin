package Day02

import println
import readInput
import kotlin.math.abs

fun main() {
    fun isReportSafe(report: List<Int>): Boolean {
        val isAllIncreasing = report.sorted() == report
        val isAllDecreasing = report.sortedDescending() == report

        return ((isAllIncreasing || isAllDecreasing)
                && report.zipWithNext().all { abs(it.first - it.second) in 1..3 })
    }

    fun part1(input: List<String>): Int {
        return input.count { l -> isReportSafe(l.split(" ").map { n -> n.toInt() }) }
    }

    fun part2(input: List<String>): Int {
        return input.count { l ->
            val report = l.split(" ").map { n -> n.toInt() }

            isReportSafe(report) || report.indices.any { i -> isReportSafe(report
                .toMutableList()
                .run {
                    this.removeAt(i)
                    this
                })
            }
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
