fun main() {
    val priorities = mapOf(
        'a' to 1,
        'b' to 2,
        'c' to 3,
        'd' to 4,
        'e' to 5,
        'f' to 6,
        'g' to 7,
        'h' to 8,
        'i' to 9,
        'j' to 10,
        'k' to 11,
        'l' to 12,
        'm' to 13,
        'n' to 14,
        'o' to 15,
        'p' to 16,
        'q' to 17,
        'r' to 18,
        's' to 19,
        't' to 20,
        'u' to 21,
        'v' to 22,
        'w' to 23,
        'x' to 24,
        'y' to 25,
        'z' to 26,
        'A' to 27,
        'B' to 28,
        'C' to 29,
        'D' to 30,
        'E' to 31,
        'F' to 32,
        'G' to 33,
        'H' to 34,
        'I' to 35,
        'J' to 36,
        'K' to 37,
        'L' to 38,
        'M' to 39,
        'N' to 40,
        'O' to 41,
        'P' to 42,
        'Q' to 43,
        'R' to 44,
        'S' to 45,
        'T' to 46,
        'U' to 47,
        'V' to 48,
        'W' to 49,
        'X' to 50,
        'Y' to 51,
        'Z' to 52
    )
    println('a'.code)
    println('c'.code)
    println('z'.code)
    println('A'.code)
    val input: List<String> = readInput("input/day03")
    println("part one: ${partOneSum(input, priorities)}")
    println("part two: ${partTwoSum(input, priorities)}")
}

private fun partOneSum(
    input: List<String>, priorities: Map<Char, Int>
) = input.sumOf { items ->
    val length = items.length
    val firstCompartment = arrayOfNulls<Unit>(52)
    val secondCompartment = arrayOfNulls<Unit>(52)
    items.forEachIndexed { index, item ->
        val currentItemPriority = priorities[item]!!
        if (index < length / 2) {
            firstCompartment[currentItemPriority - 1] = Unit
        } else secondCompartment[currentItemPriority - 1] = Unit
    }
    var selectedItemPriority = 0
    for (index in 0..51) {
        if (firstCompartment[index] != null && secondCompartment[index] != null) {
            selectedItemPriority = index + 1
            break
        }
    }
    selectedItemPriority
}

fun partTwoSum(input: List<String>, priorities: Map<Char, Int>) =
    input.windowed(3, 3).map { group ->
        val firstElfSack = arrayOfNulls<Unit>(52)
        group[0].forEach { item ->
            val currentItemPriority = priorities[item]!!
            firstElfSack[currentItemPriority - 1] = Unit
        }
        val secondElfSack = arrayOfNulls<Unit>(52)
        group[1].forEach { item ->
            val currentItemPriority = priorities[item]!!
            secondElfSack[currentItemPriority - 1] = Unit
        }
        val thirdElfSack = arrayOfNulls<Unit>(52)
        group[2].forEach { item ->
            val currentItemPriority = priorities[item]!!
            thirdElfSack[currentItemPriority - 1] = Unit
        }
        var selectedItemPriority = 0
        for (index in 0..51) {
            if (firstElfSack[index] != null && secondElfSack[index] != null && thirdElfSack[index] != null) {
                selectedItemPriority = index + 1
                break
            }
        }
        selectedItemPriority
    }.sumOf { it }