fun main() {
    val input: List<String> = readInput("input/day07")
    println("sum of total sizes: ${getSumOfTotalSizes(input)}")
}

fun getSumOfTotalSizes(input: List<String>): Int {
    val root = Node("parent", "")
    val nodes = mutableListOf<Node>()
    val directories = mutableListOf(root)
    for (index in 1..input.lastIndex) {
        val commandOrOutput = input[index]
        if (commandOrOutput.isMoveInCommand()) {
            val directoryName = commandOrOutput.getDirectoryName()
            val tempCurrentDirectory = directories.last().children[directoryName]!!
            directories.add(tempCurrentDirectory)
        } else if (commandOrOutput.isMoveOutCommand()) {
            directories.removeLast()
        }
        else if (commandOrOutput.isDirectoryOutput()) {
            val directoryName = commandOrOutput.getDirectoryName()
            val directory = Node(directoryName, value = "")
            nodes.add(directory)
            directories.last().children[directoryName] = directory
        } else if (commandOrOutput.isFileOutput()) {
            val fileName = commandOrOutput.getFileName()
            val file = Node(fileName, commandOrOutput.getFileSize())
            nodes.add(file)
            directories.last().children[fileName] = file
        }
    }
    return 0
}

private fun String.isDirectoryOutput() = this.contains("dir")
private fun String.isFileOutput() = this.first().isDigit()
private fun String.isMoveOutCommand() = this == "\$ cd .."
private fun String.isMoveInCommand() = this.contains("\$ cd") && !this.contains("..")
private fun String.getDirectoryName(): String = this.split(" ").last()

fun String.getFileSize() = this.split(" ")[0]
fun String.getFileName() = this.split(" ")[1]

data class Node(
    val name: String,
    val value: String,
    var children: MutableMap<String, Node> = mutableMapOf()
)