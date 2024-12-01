import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val list1 = input.map { s -> s.substringBefore(' ').toInt() }.sorted()
        val list2 = input.map { s -> s.substringAfterLast(' ').toInt() }.sorted()
        return list1.zip(list2) { a, b -> abs(a - b) }.sum()
    }

    fun part2(input: List<String>): Int {
        val list1 = input.map { s -> s.substringBefore(' ').toInt() }
        val list2 = input.map { s -> s.substringAfterLast(' ').toInt() }

        return list1.sumOf { n -> list2.count { it == n } * n }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
