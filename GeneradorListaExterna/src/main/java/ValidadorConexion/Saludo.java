package ValidadorConexion;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Saludo implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // Obtén los parámetros de la solicitud
    String query = exchange.getRequestURI().getQuery();
    String nombre = "Mundo"; // Valor predeterminado

    if (query != null && query.startsWith("nombre=")) {
      nombre = query.substring("nombre=".length());
    }

    // Responde con un saludo
    String respuesta = "Hola, " + nombre + "!";
    exchange.sendResponseHeaders(200, respuesta.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(respuesta.getBytes());
    os.close();
  }
}
