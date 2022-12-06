fun main() {
    val input: List<String> = readInput("input/day04")
    println("total complete overlap: ${getCompleteOverlap(input)}")
    println("total partial overlap: ${getPartialOverlap(input)}")
}

private fun getCompleteOverlap(input: List<String>) = input.sumOf {
    val reorderedList: List<Int> = getReorderedList(it)
    val overlapCount = if (isCompleteOverlap(reorderedList)) 1 else 0
    overlapCount
}

private fun getPartialOverlap(input: List<String>) = input.sumOf {
    val reorderedList: List<Int> = getReorderedList(it)
    val overlapCount = if (isOverlap(reorderedList)) 1 else 0
    overlapCount
}

private fun getReorderedList(it: String): List<Int> {
    val sections = it.split("-", ",").map { it.toInt() }.toMutableList()
    val startOfFirstPair = sections[0]
    val endOfFirstPair = sections[1]
    val startOfSecondPair = sections[2]
    val endOfSecondPair = sections[3]
    if (isStartOfFirstSectionSameAsStartOfSecondSection(startOfFirstPair, startOfSecondPair)
        && isEndOfFirstSectionOverlappingStartOfSecondSection(endOfFirstPair, endOfSecondPair)
        || isFirstSectionAfterSecondSection(startOfFirstPair, startOfSecondPair)
    ) {
        sections[0] = startOfSecondPair
        sections[1] = endOfSecondPair
        sections[2] = startOfFirstPair
        sections[3] = endOfFirstPair
    }
    return sections
}

private fun isFirstSectionAfterSecondSection(
    startOfFirstPair: Int,
    startOfSecondPair: Int
) = startOfFirstPair > startOfSecondPair

private fun isStartOfFirstSectionSameAsStartOfSecondSection(
    startOfFirstPair: Int,
    startOfSecondPair: Int
) = startOfFirstPair == startOfSecondPair

fun isCompleteOverlap(sections: List<Int>) =
    isEndOfFirstSectionOverlappingStartOfSecondSection(
        sections[2], sections[1]
    ) && isEndOfFirstSectionOverlappingStartOfSecondSection(sections[3], sections[1])

fun isOverlap(sections: List<Int>) = isCompleteOverlap(sections) ||
        isEndOfFirstSectionOverlappingStartOfSecondSection(sections[2], sections[1])

private fun isEndOfFirstSectionOverlappingStartOfSecondSection(
    endOfFirstPair: Int,
    endOfSecondPair: Int
) = endOfFirstPair <= endOfSecondPair