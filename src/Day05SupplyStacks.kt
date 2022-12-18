fun main() {
    val input: List<String> = readInput("input/day05")
    val stacks = getStacks(input)
    rearrangeCrates(getRearrangementProcedure(input), stacks)
    println(getCratesOnTopOfStack(stacks))
}

private fun getStacks(input: List<String>): Map<String, MutableList<String>> {
    val stacks = mutableMapOf<String, MutableList<String>>()
    val numberOfStacks = 9
    for (number in 1..numberOfStacks) {
        stacks[number.toString()] = mutableListOf()
    }
    val startingStacksOfCrates = getStartingStacksOfCrates(input)
    startingStacksOfCrates.forEach { crates ->
        var count = 1
        for (character in 1 until crates.length step 4) {
            val crate = crates[character]
            if (crate != ' ') {
                stacks[count.toString()]!!.add(0, crate.toString())
            }
            count += 1
        }
    }
    return stacks
}

private fun getRearrangementProcedure(input: List<String>) = input.subList(10, input.size)

private fun getStartingStacksOfCrates(input: List<String>) = input.subList(0, 8)

private fun rearrangeCrates(
    procedure: List<String>,
    stacks: Map<String, MutableList<String>>
) {
    procedure.forEach {
        val originStack = getOriginStack(it)
        val numberOfMoves = getNumberOfCratesToMove(it)
        val originCrates = stacks[originStack]!!
        val destinationStack = getDestinationStack(it)
        val destinationCrates: MutableList<String> = stacks[destinationStack]!!
        for (count in numberOfMoves downTo 1) {
            destinationCrates.add(originCrates.removeLast())
        }
    }
}

private fun getCratesOnTopOfStack(stacks: Map<String, MutableList<String>>): String {
    var cratesOnTopOfStacks = ""
    stacks.values.map {
        cratesOnTopOfStacks += it.last().removePrefix("[").removeSuffix("]")
    }
    return cratesOnTopOfStacks
}

fun getNumberOfCratesToMove(procedure: String) =
    procedure.substringAfter(" ").substringBefore(" ").toInt()

fun getOriginStack(procedure: String) = procedure.substringAfter("from ").substringBefore(" to")

fun getDestinationStack(procedure: String): String = procedure.substringAfter("to ")