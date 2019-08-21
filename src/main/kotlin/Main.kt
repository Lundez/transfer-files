import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.FileChannel
import java.nio.channels.SocketChannel

fun server() {
    val inetAddress = InetSocketAddress(940531)
    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.bind(inetAddress)
    val socketChannel = serverSocketChannel.accept()
    val fileChannel: FileChannel = FileOutputStream("hello").channel
    fileChannel.transferFrom(socketChannel, 0, 32) // Create for-loop
    socketChannel.close()
    serverSocketChannel.close()
}

fun client() {
    val serverAddress = InetSocketAddress("localhost", 940531)
    val fileChannel = FileInputStream("fileName").channel
    val socketChannel = SocketChannel.open(serverAddress)
    fileChannel.transferTo(0, fileChannel.size(), socketChannel)
    socketChannel.close()
    fileChannel.close()
}

fun main(args: Array<String>) {

}