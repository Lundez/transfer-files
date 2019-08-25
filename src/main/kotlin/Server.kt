import java.io.FileOutputStream
import java.net.InetSocketAddress
import java.nio.channels.FileChannel
import java.nio.channels.ServerSocketChannel

private const val Port = 31415

fun server(filename: String) {
    println("Hi")
    val inetAddress = InetSocketAddress(Port)
    val serverSocketChannel = ServerSocketChannel.open()

    serverSocketChannel.bind(inetAddress)
    println("binded")
    val socketChannel = serverSocketChannel.accept()
    println("accedp")
    val fileChannel: FileChannel = FileOutputStream("$filename.copy").channel
    println("TransferFrom")
    fileChannel.transferFrom(socketChannel, 0, Long.MAX_VALUE) // Create for-loop
    println("TransferFrom done")
    socketChannel.close()
    serverSocketChannel.close()
}

fun main(args: Array<String>) {
    val fileName = "/home/lunde/Downloads/Bitwarden-1.15.2-x86_64(1).AppImage"
    println(getLanIP())
    println(getExternalIP())
    server(fileName)
}
