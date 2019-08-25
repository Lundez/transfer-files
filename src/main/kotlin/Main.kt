
fun main(args: Array<String>) {
    val fileName = "/home/lunde/Downloads/Bitwarden-1.15.2-x86_64(1).AppImage"
    println(getLanIP())
    println(getExternalIP())
    server(fileName)
}

fun main() {
    val fileName = "/home/lunde/Downloads/Bitwarden-1.15.2-x86_64(1).AppImage"
    client(fileName)
}