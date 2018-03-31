import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.origin
import io.ktor.http.*
import io.ktor.request.host
import io.ktor.request.receiveChannel
import io.ktor.response.*
import io.ktor.server.engine.*
import java.io.File

// Used to explore the reverse proxy task
fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/image.jpg") {
                println(call.request.origin.remoteHost)
                call.request.headers.forEach { s, list ->
                    println("$s: $list")
                }
                call.respondFile(File("/tmp/pr0n.jpg"))
            }
        }
    }.start(wait = true)
}