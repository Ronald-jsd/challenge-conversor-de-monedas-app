package consultaApi;

import com.google.gson.Gson;
import modelRecord.Moneda;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public Moneda buscaMoneda(String moneda) {
        String url = "https://v6.exchangerate-api.com/v6/4f05c9c14a871ba8166f42f9/latest/";
        URI direccion = URI.create( url + moneda.toUpperCase()  );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

                try {
                    HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
                    return new Gson().fromJson(response.body(), Moneda.class  );
                }catch (Exception e) {
                    throw new RuntimeException("No se encontro");
                }
    }
}
