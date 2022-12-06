// A, X  -> Rock (1)
// B, Y -> Paper (2)
// C, Z -> Scissors (3)

// lost (0)
// draw (3)
// win (6)

// rock > scissors
// scissors > paper
// paper > rock

// Part 2
// X lose
// Y draw
// Z win

fun main() {
    val input: List<String> = readInput("input/day02")
    println("round one score: ${input.sumOf { roundOneScore(it) }}")
    println("round two score: ${input.sumOf { roundTwoScore(it) }}")
}

fun Char.selectForLoss(): String {
    return when (this) {
        'A' -> "Z"
        'B' -> "X"
        else -> "Y"
    }
}

fun Char.selectForDraw(): String {
    return when (this) {
        'A' -> "X"
        'B' -> "Y"
        else -> "Z"
    }
}

fun Char.selectForWin(): String {
    return when (this) {
        'A' -> "Y"
        'B' -> "Z"
        else -> "X"
    }
}

fun isDraw(selection: String) =
    selection == "A X" || selection == "B Y" || selection == "C Z"

fun isLoss(selection: String) =
    selection == "A Z" || selection == "B X" || selection == "C Y"

fun shapeScore(selection: String) = when (selection.last()) {
    'X' -> 1
    'Y' -> 2
    else -> 3
}

fun outcomeScore(selection: String) = when {
    isLoss(selection) -> 0
    isDraw(selection) -> 3
    else -> 6
}

fun roundOneScore(selection: String) = shapeScore(selection) + outcomeScore(selection)

fun roundTwoScore(selection: String): Int {
    val firstColumn = selection.first()
    return when {
        isToLose(selection.last()) -> 0 + shapeScore(firstColumn.selectForLoss())
        isToDraw(selection.last()) -> 3 + shapeScore(firstColumn.selectForDraw())
        else -> 6 + shapeScore(firstColumn.selectForWin())
    }
}

fun isToLose(endRule: Char) = endRule == 'X'
fun isToDraw(endRule: Char) = endRule == 'Y'
