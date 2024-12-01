import kotlin.math.abs

val regex = Regex("""\d+""")

fun main() {
    fun getLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.forEach { line ->
            val matches = regex.findAll(line)
            leftList.add(matches.first().value.toInt())
            rightList.add(matches.last().value.toInt())
        }

        return Pair(leftList, rightList)
    }

    fun part1(input: List<String>): Int {
        val lists = getLists(input)
        val leftList = lists.first.sorted()
        val rightList = lists.second.sorted()

        return leftList.foldIndexed(0) { index, acc, _ ->
            acc + abs(rightList[index] - leftList[index])
        }
    }

    fun part2(input: List<String>): Int {
        val lists = getLists(input)
        val leftList = lists.first
        val rightList = lists.second

        return leftList.foldIndexed(0) {index, acc, _ ->
            val atLeft = leftList[index]
            acc + atLeft * rightList.count { item -> item == atLeft }
        }
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
