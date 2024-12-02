import kotlin.math.abs

fun main() {
    fun getUnsafeLevels(input: List<Int>): List<Int> {
        val distances = input.mapIndexed { index, i -> input.getOrElse(index + 1) {i} - i }.dropLast(1)
        val ascending = distances.map { d -> if (d == 0) 0 else d / abs(d) }.average() > 0

        val unsafeLevels = mutableListOf<Int>()
        input.forEachIndexed { index, num ->
            if (index == input.size - 1) return@forEachIndexed
            val difference = (input[index + 1] - num)

            if (difference > 0 != ascending || abs(difference) < 1 || abs(difference) > 3) {
                unsafeLevels.addLast(index)
                unsafeLevels.addLast(index + 1)
            }
        }

        return unsafeLevels
    }

    fun part1(input: List<String>): Int {
        return input.count { line ->
            val numbers = line.split(" ").map { it.toInt() }

            getUnsafeLevels(numbers).isEmpty()
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val numbers = line.split(" ").map { it.toInt() }

            val unsafeLevels = getUnsafeLevels(numbers)
            if (unsafeLevels.isEmpty()) {
                true
            } else {
                unsafeLevels.forEach { unsafe ->
                    val newNumbers = numbers.toMutableList()
                    newNumbers.removeAt(unsafe)

                    if (getUnsafeLevels(newNumbers).isEmpty()) {
                        return@count true
                    }
                }
                false
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
