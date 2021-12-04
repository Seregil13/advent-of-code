fun main() {
    Day03().run(checkTest = true)
}

class Day03 : Problem() {
    override val problemNumber: String
        get() = "03"
    override val testPart1Solution: Int
        get() = 198
    override val testPart2Solution: Int
        get() = 230

    private fun List<String>.getColumn(i: Int): String = this.map { it[i] }.joinToString("")
    private fun String.hasMoreChar(char: Char): Boolean {
        val count = this.count { it == char }
        return count > this.length - count
    }

    private fun String.hasFewerChar(char: Char): Boolean {
        val count = this.count { it == char }
        return count < this.length - count
    }

    private fun gamma(input: List<String>) = buildString {
        for (index in input[0].indices) {
            val hasMoreZeros = input.getColumn(index).hasMoreChar('0')
            if (hasMoreZeros) append('0') else append('1')
        }
    }.toInt(2)

    private fun epsilon(input: List<String>) = buildString {
        for (index in input[0].indices) {
            if (input.getColumn(index).hasMoreChar('1')) {
                append('0')
            } else {
                append('1')
            }
        }
    }.toInt(2)

    override fun part1(input: String): Int {
        val lines = input.lines().filter { it.isNotBlank() }

        return gamma(lines) * epsilon(lines)
    }

    override fun part2(input: String): Int {
        val lines = input.lines().filter { it.isNotBlank() }
        return oxygen(lines) * co2(lines)
    }

    private fun oxygen(input: List<String>): Int {
        var mutableInput = input

        for (index in mutableInput[0].indices) {
            val hasMoreZeros = mutableInput.getColumn(index).hasMoreChar('0')

            mutableInput = mutableInput.filter {
                if (hasMoreZeros) it[index] == '0' else it[index] == '1'
            }

            if (mutableInput.size == 1) break
        }

        return mutableInput.single().toInt(2)
    }

    private fun co2(input: List<String>): Int {
        var mutableInput = input

        for (index in mutableInput[0].indices) {
            val hasFewerOnes = mutableInput.getColumn(index).hasFewerChar('1')

            mutableInput = mutableInput.filter {
                if (hasFewerOnes) it[index] == '1' else it[index] == '0'
            }

            if (mutableInput.size == 1) break
        }

        return mutableInput.single().toInt(2)
    }
}
