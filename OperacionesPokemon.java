import java.io.BufferedReader;
import java.util.*;


public class OperacionesPokemon {
    private Map<String, Map<String, String>> MapGlobal;// map global con todos los datos del csv
    private Map<String, Map<String, String>> ColeccionUsuario; // Map solo con los pokemones del usuario

    public OperacionesPokemon(Map<String, Map<String, String>> MapGlobal){
        this.MapGlobal = MapGlobal;
        this.ColeccionUsuario = new HashMap<>(); // Elegi que la coleccion del usuario sea un hasmap
    }
    //Leerr CSV y cargar archivos al programa
    public void caergarArchivoCSV(String filepath){
        try (BufferedReader br = new BufferedReader (new FileReader (filePath))){
            String line;
            boolean isFirstLine = true;// esta variable verifica si la primera linea es la de encabezados para saltarla

            while((line= br.readLine()) !=null){
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                //Definir como se van a dividir las columnas del CSV
                String[] data= line.split(",");
                
                //Al leer el csv, decidi ignorar todas los atirbutos que el programa no pide, para que sea mas rapido y simpmle
                String nombre = data[0];
                Map< String, String> dataPokemon = new HashMap<>();
                // solo usamos la columna 0, 2, 3 y 7 
                dataPokemon.put ("Tipo1", data[2]);
                dataPokemon.put("tipo2", datap[3]);
                dataPokemon.put("Habilidades", data[7]);

                MapGlobal.put(nombre, dataPokemon);
            }
        }
    }
    //Agregar POkemon a Coleccion de usuario
    public void agregarPokemon( String nombre){
        if(MapGlobal.containsKey(nombre)){
            if (!ColeccionUsuario.containsKey(name)){
                ColeccionUsuario.put(nombre, MapGlobal.get(nombre));
                System.out.println(nombre + "Se agrego a la coleccion");     
            }
            else{
                System.out.println(nombre + "ya se encuentra en la coleccion");
            }
            else{
                System.out.println("No se encontro al pokemon en los datos, asegurate de escirbir el nombre correctamente");
            }

        }
    }
    //Mostra datos de POkemon
    public void mostrarDatos(String nombre){
        if (MapGlobal.containsKey(nombre)){
            Map<String, String> dataPokemon = MapGlobal.get(nombre);
            System.out.println("Nombre: " + nombre);
            System.out.println("Tipo1: " + dataPokemon.get("Tipo1"));
            System.out.println("Tipo2: " + dataPokemon.get("Tipo2"));
            System.out.println("Habilidades: " + dataPokemon.get("Habilidades"));
        } else{
            System.out.println("No se encontro al pokemon en los datos, asegurate de escribir el nombre correctamente");
        }
    }
    // Mostrar nombre y tipo 1 de los pokmones de la coleccion del usuario
    public void mostrarColeccionOrdenada(){
        List<Map.Entry<String, Map<String, String>>> coleccionOrdenada = new ArrayList<>(ColeccionUsuario.entrySet());
        coleccionOrdenada.sort(Comparator.comparing(entry -> entry.getValue().get('Tipo1'))); 

        for (Map.Entry<String, Map<String, String>> entry: coleccionOrdenada){
            System.out.println("Nombre: " + entry.getKey() + ", Tipo1: " + entry.getValue().get("Tipo1"));
        }
    }
    // Mostrar nombre y tipo 1 de toda la lista de pokemones 
    public void mostrarTodosLosPokemonesOrdenados(){
        List<Map.Entry<String, Map<String, String>>> todosPokemon = new ArrayList<>(MapGlobal.entrySet());
        todosPokemon.sort(Comparator.comparing(entry -> entry.getValue().get("Tipo1"))); 
        for (Map.Entry<String, Map<String, String>> entry: todosPokemon){
            System.out.println("Nombre: " + entry.getKey() + ", Tipo1: " + entry.getValue().get("Tipo1"));
        }
    }
 // Mostrar Habilidad Especifica de Pokemon
  public void mostrarHabilidadPokemon(String Habilidad){
    for (Map.Entry<String, Map<String, String>> entry: MapGlobal.entrySet()){
        if (entry.getValue().get("Habilidades").toLowerCase().containg(Habilidad.toLowerCase())){
            System.out.println(entry.getKey());
        }
    }

  }
}
