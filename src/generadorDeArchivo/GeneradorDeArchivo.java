package generadorDeArchivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import modelRecord.DatosConversion;

public class GeneradorDeArchivo {

    public static void guardarJson(String opcion, double resultado) {
        DatosConversion datos = new DatosConversion(opcion, resultado );
        Gson gson = new Gson();
        String json = gson.toJson(datos);

        // Carpeta raíz del proyecto
        String rutaProyecto = System.getProperty("user.dir");
        File carpetaData = new File(rutaProyecto + "/data");

        // Crear carpeta si no existe
        if (!carpetaData.exists()) {
            carpetaData.mkdirs();
        }

        // Archivo JSON dentro de data
        String rutaArchivo = rutaProyecto + "/data/datos.json";

        try (FileWriter escritura = new FileWriter(rutaArchivo, true)) {
            escritura.write(json + System.lineSeparator());
            System.out.println("Datos guardados exitosamente en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
