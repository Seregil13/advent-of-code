import java.lang.Exception

fun main() {
    Day04().run(true)
}

class Day04 : Problem() {
    override val problemNumber: String
        get() = "04"
    override val testPart1Solution: Int
        get() = 4512
    override val testPart2Solution: Int
        get() = 1924


    override fun part1(input: String): Int {

        val bingoInput = BingoInput.parse(input)

        bingoInput.numbers.forEach { number ->
            bingoInput.boards.forEach { board ->
                board.mark(number)

                if (board.isBingo()){
                    return board.score()
                }
            }
        }

        return 0
    }

    override fun part2(input: String): Int {
        val bingoInput = BingoInput.parse(input)
        var boards = bingoInput.boards

        bingoInput.numbers.forEach { number ->
            boards.forEach { board ->
                board.mark(number)
            }

            if (boards.size == 1 && boards.single().isBingo()) {
                return boards.single().score()
            }

            boards = boards.filter { !it.isBingo() }
        }
        return 0
    }

    data class BingoInput(
        val numbers: List<Int>,
        val boards: List<BingoBoard>
    ) {
        companion object {
            fun parse(input: String): BingoInput {
                val spl = input.split("\n\n")

                val numbers = spl.take(1).single().split(",").map { it.toInt() }
                val boards = spl.drop(1).map {
                    BingoBoard.parse(it)
                }

                return BingoInput(numbers, boards)
            }
        }
    }

    data class BingoBoard(
        val board: List<List<Int>>
    ) {
        private val marked: MutableList<MutableList<Boolean>> = board.map {
            it.map { false }.toMutableList()
        }.toMutableList()

        private var lastNumberCalled: Int = 0

        init {
            require(board.size == 5)
            board.forEachIndexed { _, ints ->
                require(ints.size == 5)
            }
        }

        fun mark(number: Int) {
            board.forEachIndexed { iIndex, ints ->
                ints.forEachIndexed { jIndex, i ->
                    if (i == number) {
                        marked[iIndex][jIndex] = true
                        lastNumberCalled = number
                    }
                }
            }
        }

        fun isBingo(): Boolean {
            for (row in marked) {
                if (!row.contains(false)) return true
            }

            for (index in marked[0].indices) {
                val column = marked.getColumn(index)
                if (!column.contains(false)) return true
            }

            return false
        }

        fun score(): Int {
            var sum = 0

            board.forEachIndexed { iIndex, ints ->
                ints.forEachIndexed { jIndex, i ->
                    if (!marked[iIndex][jIndex]) sum += i
                }
            }

            return sum * lastNumberCalled
        }

        private fun List<List<Boolean>>.getColumn(index: Int): List<Boolean> = this.map { it[index] }

        companion object {
            private val rowRegex = """(\d+)\s*(\d+)\s*(\d+)\s*(\d+)\s*(\d+)""".toRegex()

            fun parse(input: String): BingoBoard {
                val lines = input.lines()

                val board = lines.filter { it.isNotBlank() }.map { line ->
                    val (on, tw, th, fo, fi) = rowRegex.matchEntire(line.trim())?.destructured ?: throw Exception("Unable to match regex")

                    listOf(on.toInt(), tw.toInt(), th.toInt(), fo.toInt(), fi.toInt())
                }

                return BingoBoard(board)
            }
        }
    }
}
