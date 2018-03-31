import okhttp3.OkHttpClient
import okhttp3.Request
import java.nio.charset.Charset

// WIP, didn't get anything to work here
fun main(args: Array<String>) {
    // 7d2250c3093c7ec2821739f45cd3f972048899dbf268d4608ccc090b71f6e735
    val tokenShorties = arrayOf<Short>(0x7d, 0x22, 0x50, 0xc3, 0x09, 0x3c, 0x7e, 0xc2, 0x82, 0x17, 0x39, 0xf4, 0x5c, 0xd3, 0xf9,
            0x72, 0x04, 0x88, 0x99, 0xdb, 0xf2, 0x68, 0xd4, 0x60, 0x8c, 0xcc, 0x09, 0x0b, 0x71, 0xf6, 0xe7, 0x35)
    val token = tokenShorties.map { it.toByte() }.toByteArray()

    Charset.availableCharsets().map { String(token, it.value) }.forEach { println(it) }
    println(token.size )

    val client = OkHttpClient()

    val request = Request.Builder()
            .url("http://paddington.tghack.no/api/")
            .header("Cookie", "User-Auth=757365723d61646d696e266578706972793d31353231373138353339-7d2250c3093c7ec2821739f45cd3f972048899dbf268d4608ccc090b71f6e735")
            .get()
            .build()

    val response = client.newCall(request).execute()

    response.headers().toMultimap().forEach {
        println("${it.key}=${it.value}")
    }
    println(response.body()?.string() ?: "No body received")
}