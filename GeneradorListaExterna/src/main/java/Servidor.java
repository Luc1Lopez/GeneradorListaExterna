import ValidadorConexion.Saludar;
import ValidadorConexion.Saludo;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Servidor {
  public static void main(String[] args) throws IOException {
    // Crea el servidor en el puerto 8080
    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);
    // Define los manejadores de las rutas
    servidor.createContext("/saludo", new Saludo());
    servidor.createContext("/saludar", new Saludar());
    // Inicia el servidor
    servidor.start();
    System.out.println("Servidor en ejecución en http://localhost:8080/");
  }
}
