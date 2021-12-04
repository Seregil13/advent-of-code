import java.io.File

abstract class Problem {
    abstract val problemNumber: String
    abstract val testPart1Solution: Int
    abstract val testPart2Solution: Int

    private val input
        get() = File("src", "Day${problemNumber}.txt").readLines()

    private val testInput
        get() = File("src", "Day${problemNumber}_test.txt").readLines()


    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    fun run(checkTest: Boolean) {
        if (checkTest) {
            check(part1(testInput) == testPart1Solution)
            check(part2(testInput) == testPart2Solution)
        }

        println(part1(input))
        println(part2(input))
    }
}
