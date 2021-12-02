fun main() {
    data class Movement(val direction: String, val value: Int)

    fun part1(input: List<String>): Int {

        val x = input
            .map {
                val sp = it.split(" ")
                Movement(sp.first(), sp.last().toInt())
            }
            .groupBy { it.direction }
            .map { entry ->
                entry.key to entry.value.sumOf { it.value }
            }
            .toMap()

        val up = x["up"]!!
        val down = x["down"]!!
        val forward = x["forward"]!!

        return (down - up) * forward
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var horizontal = 0
        var depth = 0

        input.forEach {
            val sp = it.split(" ")

            when (sp.first()) {
                "forward" -> {
                    horizontal += (sp.last().toInt())
                    depth += (sp.last().toInt() * aim)
                }
                "down" -> aim += sp.last().toInt()
                "up" -> aim -= sp.last().toInt()
            }
        }

        return horizontal * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
