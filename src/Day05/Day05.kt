package Day05

import println
import readInput

fun main() {
    fun getRules(input: List<String>): List<Pair<Int, Int>> {
        return input.subList(0, input.indexOf("")).map { l ->
            val (page1, page2) = l.split("|").map { it.toInt() }
            Pair(page1, page2)
        }
    }

    fun getUpdates(input: List<String>): List<List<Int>> {
        return input.subList(input.indexOf("") + 1, input.size).map { l ->
            l.split(",").map { it.toInt() }
        }
    }

    fun isUpdateValid(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        return rules.all { rule ->
            !update.contains(rule.second) || update.indexOf(rule.first) < update.indexOf(rule.second)
        }
    }

    fun part1(input: List<String>): Int {
        val rules = getRules(input)
        val updates = getUpdates(input)

        return updates.fold(0) { acc, update ->
            acc + if (isUpdateValid(update, rules)) update[update.size/2] else 0
        }
    }

    fun part2(input: List<String>): Int {
        val rules = getRules(input)
        val updates = getUpdates(input)

        return updates.fold(0) { acc, update ->
            if (isUpdateValid(update, rules)) return@fold acc
            val newUpdate = update.toMutableList()

            // Don't ask
            while (!isUpdateValid(newUpdate, rules)) {
                rules.forEach { rule ->
                    if (newUpdate.contains(rule.second) && newUpdate.indexOf(rule.first) > newUpdate.indexOf(rule.second)) {
                        newUpdate.removeAt(newUpdate.indexOf(rule.second))
                        newUpdate.add(newUpdate.indexOf(rule.first) + 1, rule.second)
                    }
                }
            }
            acc + newUpdate[newUpdate.size/2]
        }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}