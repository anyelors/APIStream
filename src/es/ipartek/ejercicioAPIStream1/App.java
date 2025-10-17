package es.ipartek.ejercicioAPIStream1;

import es.ipartek.Persona;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;

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
                .map(v-> v.getCodigoVuelo()).toList();
    }

    public static OptionalInt obtenerVueloMaxPasajeros(List<Vuelo> vuelos) {
        return vuelos.stream()
                .mapToInt(Vuelo::getNumeroPasajeros)
                .max();
    }

    public static List<Vuelo> obtenerVuelosOrdenadoByNumPasajeros( List<Vuelo> vuelos ) {
        return vuelos.stream()
                .sorted(Comparator.reverseOrder()).toList();
    }

    public static double obtenerTotalPasajeros(List<Vuelo> vuelos) {
        return vuelos.stream()
                .mapToDouble(Vuelo::getNumeroPasajeros)
                .sum();
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
        List<Vuelo> vuelosOrdenadosByNumPasajeros = obtenerVuelosOrdenadoByNumPasajeros(vuelos);
        vuelosOrdenadosByNumPasajeros.forEach(System.out::println);

        System.out.println("5.- Método que retorna el total de pasajeros viajando entre todos los vuelos registrados");
        System.out.println(obtenerTotalPasajeros(vuelos));
    }
}
