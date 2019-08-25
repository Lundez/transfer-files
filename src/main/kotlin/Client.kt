import java.io.File
import java.io.FileInputStream
import java.net.InetSocketAddress
import java.nio.channels.FileChannel
import java.nio.channels.SocketChannel
import java.nio.file.Path
import java.io.IOException
import java.nio.file.Files
import java.util.zip.ZipEntry
import java.nio.file.Paths
import java.util.zip.ZipOutputStream


private const val Port = 31415

fun getFiles(): List<File> {

    return listOf()
}

@Throws(IOException::class)
fun pack(sourceDirPath: String, zipFilePath: String) = try {
    println("Client::Compressing")
    val p = Files.createFile(Paths.get(zipFilePath))
    ZipOutputStream(Files.newOutputStream(p))
        .use { zs ->
            val pp = Paths.get(sourceDirPath)
            Files.walk(pp)
                .filter { path -> !Files.isDirectory(path) }
                .forEach { path ->
                    val zipEntry = ZipEntry(pp.relativize(path).toString())
                    try {
                        zs.putNextEntry(zipEntry)
                        Files.copy(path, zs)
                        zs.closeEntry()
                    } catch (e: IOException) {
                        System.err.println(e)
                    }
                }
        }
    println("Client::Compression done")
} catch (e: IOException) {
    println(e.message)
}

fun client(filename: String) {
    println("Client::init")
    val serverAddress = InetSocketAddress(Port)
    pack(filename, "$filename.zip")
    val fileChannel = FileInputStream("$filename.zip").channel

    println("Client::Connecting to Server")
    val socketChannel = SocketChannel.open(serverAddress)
    println("Client::Sending file $filename")
    fileChannel.transferTo(0, fileChannel.size(), socketChannel)

    println("Client::Finished, shutting done")
    socketChannel.close()
    fileChannel.close()
    println("Client::Closed")
}

fun main(args: Array<String>) {
    val fileName = "/home/londet/Downloads"
    client(fileName)
}