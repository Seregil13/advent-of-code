fun main() {
    fun part1(input: List<String>): Int {
        return input
            .filterIndexed { index, s ->
                if (index == 0) return@filterIndexed false

                val current = s.toInt()
                val previous = input[index - 1].toInt()

                current > previous
            }
            .count()
    }

    fun part2(input: List<String>): Int {

        val x: List<Int> = input
            .dropLast(2)
            .mapIndexed { index, s ->
                s.toInt() + input[index + 1].toInt() + input[index + 2].toInt()
            }

        return x
            .filterIndexed { index, i ->
                if (index == 0) return@filterIndexed false

                val previous = x[index - 1]

                i > previous
            }
            .count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
