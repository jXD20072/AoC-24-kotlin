import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.count { line ->
            val numbers = line.split(" ").map { it.toInt() }
            val ascending = numbers[1] - numbers[0] > 0
            
            numbers.forEachIndexed { index, num ->
                if (index > 0) {
                    val difference = (num - numbers[index - 1])
                    if (difference > 0 != ascending) return@count false
                    if (abs(difference) < 1  || abs(difference) > 3) return@count false
                }
            }
            true
        }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 0)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
