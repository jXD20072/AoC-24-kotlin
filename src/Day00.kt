fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day00_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}