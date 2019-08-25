import java.io.FileInputStream
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel

private const val Port = 31415

fun client(filename: String) {
    println("hi")
    val serverAddress = InetSocketAddress(Port)
    val fileChannel = FileInputStream(filename).channel
    val socketChannel = SocketChannel.open(serverAddress)
    println("TransferTo")
    fileChannel.transferTo(0, fileChannel.size(), socketChannel)
    println("DONE")
    socketChannel.close()
    fileChannel.close()
}

fun main(args: Array<String>) {
    val fileName = "/home/lunde/Downloads/Bitwarden-1.15.2-x86_64(1).AppImage"
    client(fileName)
}