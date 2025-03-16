import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Selecciones que tipo de Map quiere Utilizar(1.HashMap, 2.LinkedHasMap, 3.TreeMap): ");
        int opcion = scanner.nextInt();
        Map<String, Map<String, String>> mapaSeleccionado = PokemonFactory.createMap(opcion);


        OperacionesPokemon manager = new OperacionesPokemon(mapaSeleccionado);
        manager.cargarArchivoCSV("pokemon_data_pokeapi.csv");

        //Menu Principal

        while (true) {
            System.out.println("\n Opciones:");
            System.out.println("1. Agregar POkemon a la coleccion");
            System.out.println("2.Mostrar Datos de un Pokemon");
            System.out.println("3.Ordenar Coleccion por tipo1");
            System.out.println("4.MOstrar Todos los pokemones ordenados por tipo1");
            System.out.println("5.Mostrar Pokemon por habilidad especifica");
            System.out.println("6.Salir");
            System.out.print("Ingresa la Opcion deseada:");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine();
            switch (opcionMenu) {
                case 1:
                    System.out.print("Ingrese el POkemon: ");
                    String nombre = scanner.nextLine();
                    manager.agregarPokemon(nombre);
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del Pokemon: ");
                    String nombrePokemon = scanner.nextLine();
                    manager.mostrarDatos(nombrePokemon);
                    break;
                case 3:
                    manager.mostrarColeccionOrdenada();
                    break;
                case 4:
                    manager.mostrarTodosLosPokemonesOrdenados();
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad especifica: ");
                    String habilidad = scanner.nextLine();
                    manager.mostrarHabilidadPokemon(habilidad);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opcion invalida");


            }

        }
       
    }
    
}
