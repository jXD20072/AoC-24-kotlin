package Day06

import println
import readInput

fun main() {
    fun isInsideMap(mapSize: Pair<Int, Int>, guardPosition: Pair<Int, Int>): Boolean {
        return guardPosition.first > 0 && guardPosition.first < mapSize.first
                && guardPosition.second > 0 && guardPosition.second < mapSize.second
    }

    fun getAdjacentCell(guardPosition: Pair<Int, Int>, guardDirection: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(guardPosition.first + guardDirection.first, guardPosition.second - guardDirection.second)
    }

    fun getRoute(input: List<String>): Pair<Boolean, Set<Pair<Int, Int>>> {
        val map = input.map { l -> l.toCharArray().toList() }
        val mapSize = Pair(map[0].size, map.size)

        val guardInitialY = input.indexOf(input.find { s -> s.contains('^') })
        val guardInitialX = input[guardInitialY].indexOf('^')
        var guardPosition = Pair(guardInitialX, guardInitialY)
        var guardDirection = Pair(0, 1)

        val visitedCells = mutableSetOf(Pair(guardPosition, guardDirection))

        while (isInsideMap(mapSize, guardPosition)) {
            var nextCell = getAdjacentCell(guardPosition, guardDirection)
            while (map.getOrNull(nextCell.second)?.getOrNull(nextCell.first) == '#') {
                guardDirection = when(guardDirection) {
                    Pair(0, 1) -> Pair(1, 0)
                    Pair(1, 0) -> Pair(0, -1)
                    Pair(0, -1) -> Pair(-1, 0)
                    else -> Pair(0, 1)
                }
                nextCell = getAdjacentCell(guardPosition, guardDirection)
            }
            guardPosition = nextCell

            if (visitedCells.contains(Pair(guardPosition, guardDirection))) {
                return Pair(false, visitedCells.map { it.first }.toSet())
            } else if (isInsideMap(mapSize, guardPosition)) {
                visitedCells.add(Pair(guardPosition, guardDirection))
            }
        }

        return Pair(true, visitedCells.map { it.first }.toSet())
    }

    fun part1(input: List<String>): Int {
        return getRoute(input).second.size
    }

    fun part2(input: List<String>): Int {
        val originalRoute = getRoute(input).second.toList()
        var possibleObstructions = 0

        originalRoute.forEachIndexed { index, route ->
            if (route == originalRoute.first() || route == originalRoute.last()) return@forEachIndexed

            val newInput = input.toMutableList()
            val nextPos = originalRoute[index + 1]

            newInput[nextPos.second] = newInput[nextPos.second].replaceRange(nextPos.first, nextPos.first+1, "#")

            if (!getRoute(newInput).first) {
                possibleObstructions++
            }
        }

        return possibleObstructions
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}