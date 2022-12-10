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
        val currentDirectory = directories.last()
        if (commandOrOutput.isMoveInCommand()) {
            val directoryName = commandOrOutput.getDirectoryName()
            val tempCurrentDirectory = currentDirectory.children[directoryName]!!
            directories.add(tempCurrentDirectory)
        } else if (commandOrOutput.isMoveOutCommand()) {
            directories.removeLast()
        } else if (commandOrOutput.isDirectoryOutput()) {
            val directoryName = commandOrOutput.getDirectoryName()
            val directory = Node(directoryName, size = "0")
            nodes.add(directory)
            currentDirectory.children[directoryName] = directory
        } else if (commandOrOutput.isFileOutput()) {
            val fileName = commandOrOutput.getFileName()
            val file = Node(fileName, commandOrOutput.getFileSize())
            nodes.add(file)
            currentDirectory.children[fileName] = file
        }
    }
    return 0
}

private const val directoryName = "dir"
private fun String.isDirectoryOutput() = this.split(" ").first() == directoryName
private fun String.isFileOutput() = this.first().isDigit()
private fun String.isMoveOutCommand() = this == "\$ cd .."
private fun String.isMoveInCommand() = this.contains("\$ cd") && !this.contains("..")
private fun String.getDirectoryName(): String = this.split(" ").last()

fun String.getFileSize() = this.split(" ")[0]
fun String.getFileName() = this.split(" ")[1]

data class Node(
    val name: String,
    val size: String,
    var children: MutableMap<String, Node> = mutableMapOf()
)