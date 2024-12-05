package Day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val mulMatches = Regex("""mul\(\d+,\d+\)""")
        val numbers = Regex("""\d+""")

        return mulMatches.findAll(input.joinToString("")).fold(0) { acc, match ->
            val (num1, num2) = numbers.findAll(match.value).toList().map { s -> s.value.toInt() }
            acc + num1 * num2
        }
    }

    fun part2(input: List<String>): Int {
        val matches = Regex("""mul\(\d+,\d+\)|do\(\)|don't\(\)""")
        val numbers = Regex("""\d+""")
        var enabled = true

        return matches.findAll(input.joinToString("")).fold(0) { acc, match ->
            when (match.value) {
                "do()" -> {
                    enabled = true
                    acc
                }
                "don't()" -> {
                    enabled = false
                    acc
                }
                else -> {
                    if (!enabled) {
                        acc
                    } else {
                        val (num1, num2) = numbers.findAll(match.value).toList().map { s -> s.value.toInt() }
                        acc + num1 * num2
                    }
                }
            }
        }
    }

    check(part1(readInput("Day03_test1")) == 161)
    check(part2(readInput("Day03_test2")) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
