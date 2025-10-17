package es.ipartek.ejercicioAPIStream1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static List<Vuelo> obtenerVuelos() {
        return List.of(
                new Vuelo("IB100", "Madrid", "París", LocalDate.of(2025, 10, 7), LocalTime.of(9, 45), 180),
                new Vuelo("IB200", "Madrid", "Londres", LocalDate.of(2025, 10, 7), LocalTime.of(11, 30), 200),
                new Vuelo("IB300", "Barcelona", "Roma", LocalDate.of(2025, 10, 8), LocalTime.of(14, 15), 150),
                new Vuelo("IB400", "Madrid", "Nueva York", LocalDate.of(2025, 10, 9), LocalTime.of(17, 0), 350),
                new Vuelo("IB500", "Lisboa", "Madrid", LocalDate.of(2025, 10, 7), LocalTime.of(13, 10), 120),
                new Vuelo("IB600", "París", "Madrid", LocalDate.of(2025, 10, 8), LocalTime.of(10, 0), 140),
                new Vuelo("IB700", "Madrid", "Roma", LocalDate.of(2025, 10, 8), LocalTime.of(15, 45), 160),
                new Vuelo("IB800", "Barcelona", "Londres", LocalDate.of(2025, 10, 9), LocalTime.of(12, 0), 175)
        );
    }

    public static List<Vuelo> obtenerVuelosByOrigen( List<Vuelo> vuelos, String origen ) {
        return vuelos.stream()
                .filter(v -> v.getOrigen().equals(origen))
                .toList();
    }

    public static List<String> obtenerCodVuelosByOrigen( List<Vuelo> vuelos, String origen ) {
        return vuelos.stream()
                .filter(v -> v.getOrigen().equals(origen))
                .map(Vuelo::getCodigoVuelo).toList();
    }

    public static OptionalInt obtenerVueloMaxPasajeros(List<Vuelo> vuelos) {
        return vuelos.stream()
                .mapToInt(Vuelo::getNumeroPasajeros)
                .max();
    }

    public static List<Vuelo> obtenerVuelosOrderByNumPasajeros(List<Vuelo> vuelos ) {
        return vuelos.stream()
                .sorted(Comparator.reverseOrder()).toList();
    }

    public static double obtenerTotalPasajeros(List<Vuelo> vuelos) {
        return vuelos.stream()
                .mapToDouble(Vuelo::getNumeroPasajeros)
                .sum();
    }

    public static List<Vuelo> obtenerVuelosByFechaOrderHoraLLegada( List<Vuelo> vuelos, LocalDate fecha ) {
        return vuelos.stream()
                .filter(v -> v.getFechaSalida().equals(fecha))
                .sorted(Comparator.comparing(Vuelo::getHoraLlegada))
                .toList();
    }

    public static String obtenerCodVueloByVueloMaxPasajeros ( List<Vuelo> vuelos ) {
        return vuelos.stream()
                .max(Comparator.naturalOrder())
                .map(Vuelo::getCodigoVuelo)
                .orElse("No existe");
    }
    
    public static Map<Boolean, List<Vuelo>> obtenerVuelosByNumPasajerosLimit ( List<Vuelo> vuelos, int limitIni, int limitFin ) {
        return vuelos.stream()
                .collect(Collectors.partitioningBy(v -> v.getNumeroPasajeros() >= limitIni && v.getNumeroPasajeros() <= limitFin));
    }

    public static Map<String, List<Vuelo>> obtenerVuelosGroupDestino ( List<Vuelo> vuelos ) {
        return vuelos.stream()
                .collect(Collectors.groupingBy(Vuelo::getDestino));
    }

    public static boolean obtenerVueloMasNumPasajeros ( List<Vuelo> vuelos, int maxPasajeros ) {
        return vuelos.stream()
                .anyMatch(v -> v.getNumeroPasajeros() >= maxPasajeros);
    }

    public static Vuelo obtenerVueloByOrigenMinHoraLlegada( List<Vuelo> vuelos, String destino ) {
        return vuelos.stream()
                .filter(v -> v.getDestino().equals(destino))
                .min(Comparator.comparing(Vuelo::getHoraLlegada))
                .orElse(null);
    }

    public static double obtenerMediaPasajerosByDestino( List<Vuelo> vuelos, String destino ) {
        return vuelos.stream()
                .filter(v -> v.getDestino().equals(destino))
                .mapToInt(Vuelo::getNumeroPasajeros)
                .average()
                .orElse(0);
    }

    public static Map<String, Long> obtenerTotalVuelosByDestino ( List<Vuelo> vuelos ) {
        return vuelos.stream()
                .collect(Collectors.groupingBy(Vuelo::getDestino, Collectors.counting()));
    }

    public static Map<String, Long> obtenerTotalNumPasajerosByDestino ( List<Vuelo> vuelos ) {
        return vuelos.stream()
                .collect(Collectors.groupingBy(Vuelo::getDestino, Collectors.summingLong(Vuelo::getNumeroPasajeros)));
    }

    public static void main(String[] args) {

        List<Vuelo> vuelos =  List.of(
                    new Vuelo("IB100", "Madrid", "París", LocalDate.of(2025, 10, 7), LocalTime.of(9, 45), 180),
                    new Vuelo("IB200", "Madrid", "Londres", LocalDate.of(2025, 10, 7), LocalTime.of(11, 30), 200),
                    new Vuelo("IB300", "Barcelona", "Roma", LocalDate.of(2025, 10, 8), LocalTime.of(14, 15), 150),
                    new Vuelo("IB400", "Madrid", "Nueva York", LocalDate.of(2025, 10, 9), LocalTime.of(17, 0), 350),
                    new Vuelo("IB500", "Lisboa", "Madrid", LocalDate.of(2025, 10, 7), LocalTime.of(13, 10), 120),
                    new Vuelo("IB600", "París", "Madrid", LocalDate.of(2025, 10, 8), LocalTime.of(10, 0), 140),
                    new Vuelo("IB700", "Madrid", "Roma", LocalDate.of(2025, 10, 8), LocalTime.of(15, 45), 160),
                    new Vuelo("IB800", "Barcelona", "Londres", LocalDate.of(2025, 10, 9), LocalTime.of(12, 0), 175)
            );

        System.out.println("1.- Método que retorne una lista con todos los vuelos que parten de un origen");
        List<Vuelo> vuelosByOrigen = obtenerVuelosByOrigen( vuelos, "Barcelona");
        vuelosByOrigen.forEach(System.out::println);

        System.out.println("2.- Método que retorne una lista con los códigos de todos los vuelos que parten de un origen.");
        List<String> codVuelosByOrigen = obtenerCodVuelosByOrigen( vuelos, "Barcelona");
        codVuelosByOrigen.forEach(System.out::println);

        System.out.println("3.- Método que devuelve el vuelo con más pasajeros");
        OptionalInt maxPasajeros = obtenerVueloMaxPasajeros(vuelos);
        System.out.println( maxPasajeros.isPresent() ? "Vuelo con el mayor numero de pasajeros = " + maxPasajeros.getAsInt() : "No existe");

        System.out.println("4.- Método que obtiene una lista con todos los vuelos ordenados por número de pasajeros en orden descendente. ");
        List<Vuelo> vuelosOrdenadosByNumPasajeros = obtenerVuelosOrderByNumPasajeros(vuelos);
        vuelosOrdenadosByNumPasajeros.forEach(System.out::println);

        System.out.println("5.- Método que retorna el total de pasajeros viajando entre todos los vuelos registrados");
        System.out.println(obtenerTotalPasajeros(vuelos));

        System.out.println("6.- Método que retorna todos los vuelos de una determinada fecha indicada ordenados por hora de llegada");
        obtenerVuelosByFechaOrderHoraLLegada(vuelos, LocalDate.of(2025, 10, 8)).forEach(System.out::println);

        System.out.println("7.- Método que retorne el código del vuelo con más pasajeros registrado");
        System.out.println(obtenerCodVueloByVueloMaxPasajeros(vuelos));

        System.out.println("8.- Método que retorna un mapa map<Boolean, List<Vuelo>> conteniendo los vuelos con un número de pasajeros por encima y por debajo del indicado. ( emplea Collectors.Partitioning ) )");
        Map<Boolean, List<Vuelo>> vuelosByNumPasajerosLimit = obtenerVuelosByNumPasajerosLimit(vuelos, 150, 200);
        System.out.println("Vuelos con numero de pasajeros entre 150 y 200:");
        vuelosByNumPasajerosLimit.get(true).forEach(System.out::println);
        System.out.println("Vuelos con numero de pasajeros fuera de 150 y 200:");
        vuelosByNumPasajerosLimit.get(false).forEach(System.out::println);

        System.out.println("9.- Método que retorna un mapa map<String, List<Vuelo>> conteniendo todos los vuelos agrupados por ciudad de destino");
        Map<String, List<Vuelo>> vuelosGroupByDestino = obtenerVuelosGroupDestino( vuelos );
        vuelosGroupByDestino.forEach((c,v) -> {
            System.out.println("Ciudad Destino: " + c);
            v.forEach(System.out::println);
        });

        System.out.println("10.- Método que retorna un valor lógico indicando si hay algún vuelo con más de una determinada cantidad de pasajeros");
        boolean vMaxPasajeros = obtenerVueloMasNumPasajeros(vuelos, 300);
        System.out.println( vMaxPasajeros ? "Existen vuelos con más de 300 Pasajeros" : "No Existen vuelos con mas de 500 Pasajeros");

        System.out.println("11.- Método que retorna el primer vuelo que llega a una determinada ciudad de destino");
        Vuelo vueloByOrigenMinHoraLlegada = obtenerVueloByOrigenMinHoraLlegada( vuelos, "Londres");
        System.out.println(vueloByOrigenMinHoraLlegada);

        System.out.println("12.- Método que retorna la media de pasajeros que viajan a una determinada ciudad de destino.");
        double mediaPasajerosByDestino = obtenerMediaPasajerosByDestino( vuelos, "Roma");
        System.out.println("Media de pasajeros a Roma: " + mediaPasajerosByDestino );

        System.out.println("13.- Método que retorna un mapa map<String, Long> que retorna el total de vuelos por cada ciudad de destino.");
        Map<String, Long> totalVuelosByDestino = obtenerTotalVuelosByDestino( vuelos );
        totalVuelosByDestino.forEach((c,v) -> System.out.println("Ciudad Destino: " + c + " - Total Vuelos: " + v));

        System.out.println("14.- Método que retorna un mapa map<String, Long> que retorna el total de pasajeros que viajan en los vuelos a cada ciudad de destino");
        Map<String, Long> totalNumPasajerosByDestino = obtenerTotalNumPasajerosByDestino( vuelos );
        totalNumPasajerosByDestino.forEach((c,p) -> System.out.println("Ciudad Destino: " + c + " - Total Pasajeros: " + p));

    }
}
