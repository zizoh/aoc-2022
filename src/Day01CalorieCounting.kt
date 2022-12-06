fun main() {
    val input: List<String> = readInput("input/day01")
    println("largest calorie: ${largestCalorie(input)}")
    println("sum of largest calories: ${sumOfLargestCalories(input, 3)}")
}

fun largestCalorie(input: List<String>): Int {
    var largest = 0
    var currentSum = 0
    input.forEach { calorie ->
        if (calorie.isBlank()) {
            largest = maxOf(largest, currentSum)
            currentSum = 0
        } else {
            currentSum += calorie.toInt()
        }
    }
    return largest
}

fun sumOfLargestCalories(input: List<String>, numberOfElves: Int): Int {
    var currentSum = 0
    val largestCalories = mutableListOf<Int>()
    for (index in 0..input.lastIndex) {
        if (index < numberOfElves) {
            largestCalories.add(0)
        } else {
            val calorie = input[index]
            if (calorie.isBlank()) {
                val smallest = largestCalories.min()
                largestCalories.remove(smallest)
                largestCalories.add(maxOf(currentSum, smallest))
                currentSum = 0
            } else {
                currentSum += calorie.toInt()
            }
        }
    }
    val smallest = largestCalories.min()
    largestCalories.remove(smallest)
    largestCalories.add(maxOf(currentSum, smallest))
    return largestCalories.sum()
}
