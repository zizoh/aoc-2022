fun main() {
    val input: List<String> = readInput("input/day04")
    // less
    // more
    // equal
    val meh = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )
    val overlap = listOf(
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )
    println("total complete overlap: ${getCompleteOverlap(input)}")
    println("total partial overlap: ${getPartialOverlap(input)}")
}

private fun getCompleteOverlap(input: List<String>) = input.sumOf {
    val (firstAssignment: List<Int>, secondAssignment: List<Int>) = getReorderedList(it)
    val overlapCount = if (isCompleteOverlap(firstAssignment, secondAssignment)) {
        1
    } else 0
    overlapCount
}

private fun getPartialOverlap(input: List<String>) = input.sumOf {
    val (firstAssignment: List<Int>, secondAssignment: List<Int>) = getReorderedList(it)
    val overlapCount = if (isOverlap(firstAssignment, secondAssignment)) {
        1
    } else 0
    overlapCount
}

private fun getReorderedList(it: String): Pair<List<Int>, List<Int>> {
    val sections = it.split(",")
    val firstAssignment: List<Int>
    val secondAssignment: List<Int>
    val first = sections.first().split("-")
    val last: List<String> = sections.last().split("-")
    val index0 = first.first().toInt()
    val index1 = first.last().toInt()
    val index2 = last.first().toInt()
    val index3 = last.last().toInt()
    if (index0 < index2) {
        firstAssignment = listOf(index0, index1)
        secondAssignment = listOf(index2, index3)
    } else if (index0 == index2) {
        if (index1 <= index3) {
            firstAssignment = listOf(index2, index3)
            secondAssignment = listOf(index0, index1)
        } else {
            firstAssignment = listOf(index0, index1)
            secondAssignment = listOf(index2, index3)
        }
    } else {
        firstAssignment = listOf(index2, index3)
        secondAssignment = listOf(index0, index1)
    }
    return Pair(firstAssignment, secondAssignment)
}

fun isCompleteOverlap(
    firstAssignment: List<Int>,
    secondAssignment: List<Int>
) = secondAssignment.first() <= firstAssignment.last()
        && secondAssignment.last() <= firstAssignment.last()

fun isOverlap(
    firstAssignment: List<Int>,
    secondAssignment: List<Int>
) = isCompleteOverlap(firstAssignment, secondAssignment) || secondAssignment.first() <= firstAssignment.last()