fun main() {
    Day03().run(checkTest = true)
}

class Day03: Problem() {
    override val problemNumber: String
        get() = "03"
    override val testPart1Solution: Int
        get() = 198
    override val testPart2Solution: Int
        get() = 230

    override fun part1(input: List<String>): Int {
        val size = input[0].length

        val zeros = IntArray(size)
        val ones = IntArray(size)

        for (line in input) {
            for (index in line.indices) {
                when (line[index]) {
                    '0' -> zeros[index]++
                    '1' -> ones[index]++
                }
            }
        }

        var gamma = ""
        var epsilon = ""
        for (i in 0 until size) {
            if (zeros[i] > ones[i]) {
                gamma += '0'
                epsilon += '1'
            } else {
                gamma += '1'
                epsilon += '0'
            }
        }

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    override fun part2(input: List<String>): Int {
        return calculate(input, true).toInt(2) * calculate(input, false).toInt(2)
    }

    private fun calculate(input: List<String>, oxygen: Boolean): String {
        var rem = input

        val size = input[0].length
        var index = 0
        while (rem.size > 1 && index < size) {

            var zeros = 0
            var ones = 0

            for (line in rem) {
                when (line[index]) {
                    '0' -> zeros++
                    '1' -> ones++
                }
            }

            rem = if (oxygen) {
                if (ones >= zeros) {
                    rem.filter { it[index] == '1' }
                } else {
                    rem.filter { it[index] == '0' }
                }
            } else {
                if (zeros <= ones) {
                    rem.filter { it[index] == '0' }
                } else {
                    rem.filter { it[index] == '1' }
                }
            }

            index ++
        }

        return rem[0]
    }
}
