import java.util.*

public class PokemonFactory{
    public static Map<String, Pokemon> createMap (int opcion) {
        switch (opcion) {
            case 1:
                return new HashMap<>();
            case 2:
                return new LinkedHashMap<>();

            case 3:
                return new TreeMap<>();

            default:
                throw new IllegalArgumentException( "Ingrese una opcion valida. ");

        }
    }
}