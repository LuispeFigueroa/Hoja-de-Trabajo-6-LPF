import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class OperacionesPokemon {
    private Map<String, Map<String, String>> MapGlobal; // map global con todos los datos del csv
    private Map<String, Map<String, String>> ColeccionUsuario; // Map solo con los pokemones del usuario

    public OperacionesPokemon(Map<String, Map<String, String>> MapGlobal) {
        this.MapGlobal = MapGlobal;
        this.ColeccionUsuario = new HashMap<>(); // Elegi que la coleccion del usuario sea un hasmap
    }

    // Leer CSV y cargar archivos al programa
    public void cargarArchivoCSV(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirstLine = true; // esta variable verifica si la primera linea es la de encabezados para saltarla

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // Definir como se van a dividir las columnas del CSV
                String[] data = line.split(",");

                // Al leer el csv, decidi ignorar todas los atributos que el programa no pide, para que sea mas rapido y simple
                String nombre = data[0];
                Map<String, String> dataPokemon = new HashMap<>();
                // solo usamos la columna 0, 2, 3 y 7
                dataPokemon.put("Tipo1", data[2]);
                dataPokemon.put("Tipo2", data[3]);
                dataPokemon.put("Habilidades", data[7]);

                MapGlobal.put(nombre, dataPokemon);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Agregar Pokemon a Coleccion de usuario
    public void agregarPokemon(String nombre) {
        if (MapGlobal.containsKey(nombre)) {
            if (!ColeccionUsuario.containsKey(nombre)) {
                ColeccionUsuario.put(nombre, MapGlobal.get(nombre));
                System.out.println(nombre + " se agregó a la colección");
            } else {
                System.out.println(nombre + " ya se encuentra en la colección");
            }
        } else {
            System.out.println("No se encontró al pokemon en los datos, asegúrate de escribir el nombre correctamente");
        }
    }

    // Mostrar datos de Pokemon
    public void mostrarDatos(String nombre) {
        if (MapGlobal.containsKey(nombre)) {
            Map<String, String> dataPokemon = MapGlobal.get(nombre);
            System.out.println("Nombre: " + nombre);
            System.out.println("Tipo1: " + dataPokemon.get("Tipo1"));
            System.out.println("Tipo2: " + dataPokemon.get("Tipo2"));
            System.out.println("Habilidades: " + dataPokemon.get("Habilidades"));
        } else {
            System.out.println("No se encontró al pokemon en los datos, asegúrate de escribir el nombre correctamente");
        }
    }

    // Mostrar nombre y tipo 1 de los pokemones de la coleccion del usuario
    public void mostrarColeccionOrdenada() {
        mostrarPokemonesOrdenados(ColeccionUsuario);
    }

    // Mostrar nombre y tipo 1 de toda la lista de pokemones
    public void mostrarTodosLosPokemonesOrdenados() {
        mostrarPokemonesOrdenados(MapGlobal);
    }

    // Método privado para evitar duplicación de código
    private void mostrarPokemonesOrdenados(Map<String, Map<String, String>> pokemones) {
        List<Map.Entry<String, Map<String, String>>> pokemonesOrdenados = new ArrayList<>(pokemones.entrySet());
        pokemonesOrdenados.sort(Comparator.comparing(entry -> entry.getValue().get("Tipo1")));

        for (Map.Entry<String, Map<String, String>> entry : pokemonesOrdenados) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo1: " + entry.getValue().get("Tipo1"));
        }
    }

    // Mostrar Habilidad Especifica de Pokemon
    public void mostrarHabilidadPokemon(String habilidad) {
        for (Map.Entry<String, Map<String, String>> entry : MapGlobal.entrySet()) {
            if (entry.getValue().get("Habilidades").toLowerCase().contains(habilidad.toLowerCase())) {
                System.out.println(entry.getKey());
            }
        }
    }
}