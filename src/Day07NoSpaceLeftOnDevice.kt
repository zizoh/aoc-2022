fun main() {
    val input: List<String> = readInput("input/day07")
    println("sum of total sizes: ${getSumOfTotalSizes(input)}")
}

fun getSumOfTotalSizes(input: List<String>): Int {
    val root = Node("root", "0")
    val nodes = mutableListOf<Node>()
    val directories = mutableListOf(root)
    for (index in 1..input.lastIndex) {
        val commandOrOutput = input[index]
        val currentDirectory = directories.last()
        if (commandOrOutput.isMoveInCommand()) {
            val directoryName = commandOrOutput.getDirectoryName()
            val tempCurrentDirectory = currentDirectory.children["$DIR $directoryName"]!!
            directories.add(tempCurrentDirectory)
        } else if (commandOrOutput.isMoveOutCommand()) {
            directories.removeLast()
        } else if (commandOrOutput.isDirectoryOutput()) {
            val directory = Node(commandOrOutput, size = "0")
            nodes.add(directory)
            currentDirectory.children[commandOrOutput] = directory
        } else if (commandOrOutput.isFileOutput()) {
            val fileName = commandOrOutput.getFileName()
            val file = Node(fileName, commandOrOutput.getFileSize())
            nodes.add(file)
            currentDirectory.children[fileName] = file
        }
    }
    return 0
}

private const val DIR = "dir"
private fun String.isDirectoryOutput() = this.split(" ").first() == DIR
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