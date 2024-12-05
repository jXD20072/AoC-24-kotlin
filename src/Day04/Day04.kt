package Day04

import println
import readInput

fun main() {
    fun getVertical(input: List<String>): List<String> {
        return input
            .asSequence()
            .map { it.withIndex() }
            .flatten()
            .groupBy({ s -> s.index}, {it.value})
            .toList()
            .map { it.second.joinToString("") }
            .toList()
    }

    fun getDiagonalReverse(input: List<String>): List<String> {
        return getVertical(input
            .mapIndexed { index, s -> " ".repeat(index) + s }).map { it.reversed() }
    }

    fun getDiagonal(input: List<String>): List<String> {
        return getDiagonalReverse(input.reversed())
    }

    fun part1(input: List<String>): Int {
        val lists = input + getVertical(input) + getDiagonal(input) + getDiagonalReverse(input)
        return lists.fold(0) { acc, s ->
            acc + Regex("XMAS").findAll(s).count() + Regex("SAMX").findAll(s).count()}
    }

    fun part2(input: List<String>): Int {
        return input.foldIndexed(0) { i, acc, s ->
            acc + Regex("A").findAll(s).count { match ->
                val corners = listOf(
                    input.getOrNull(i - 1)?.getOrNull(match.range.first - 1),
                    input.getOrNull(i - 1)?.getOrNull(match.range.first + 1),
                    input.getOrNull(i + 1)?.getOrNull(match.range.first + 1),
                    input.getOrNull(i + 1)?.getOrNull(match.range.first - 1)
                )

                (corners.count { it == 'M' } == 2 && corners.count { it == 'S' } == 2 && corners[0] != corners[2])
            }
        }
    }

    check(part1(readInput("Day04_test1")) == 18)
    check(part2(readInput("Day04_test2")) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
// 2053 too high