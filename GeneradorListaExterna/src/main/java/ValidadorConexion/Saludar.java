package ValidadorConexion;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Saludar implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if ("POST".equals(exchange.getRequestMethod())) {
      // Lee el cuerpo de la solicitud
      InputStream is = exchange.getRequestBody();
      byte[] requestBody = is.readAllBytes();
      String cuerpo = new String(requestBody, StandardCharsets.UTF_8);

      // Extrae el nombre del cuerpo
      String nombre = "Desconocido";
      if (cuerpo.contains("\"nombre\":\"")) {
        nombre = cuerpo.split("\"nombre\":\"")[1].split("\"")[0];
      }
      // Responde con un saludo
      String respuesta = "Hola, " + nombre + "!";
      exchange.sendResponseHeaders(200, respuesta.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(respuesta.getBytes());
      os.close();
    } else {
      exchange.sendResponseHeaders(405, -1); // Method Not Allowed
    }
  }
}