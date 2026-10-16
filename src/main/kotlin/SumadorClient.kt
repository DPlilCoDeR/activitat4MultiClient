import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner


fun main() {
    val host = "127.0.0.1" // localhost
    val port = 9000
    println("[Client] Intentant connectar a $host:$port...")

    Socket(host, port).use { socket ->
        println("[Client] Connexió establerta amb èxit!")
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val writer = PrintWriter(socket.getOutputStream(), true)
        val scanner = Scanner(System.`in`)
        while (true) {
            print("\nEscriu un nombre ('final' per acabar): ")
            val textUsuari = scanner.nextLine()
            // Enviar missatge al servidor
            writer.println(textUsuari)
            writer.flush()
            if (textUsuari.equals("final", ignoreCase = true)) {
                println("[Client] Desconnectant del servidor...")
                break
            }

        }
        // Llegir la resposta del servidor
        val resposta = reader.readLine()
        if (resposta == null) {
            println("[Client] El servidor ha tancat la connexió.")
        }
        println("[Client <- Servidor] $resposta")
    } }