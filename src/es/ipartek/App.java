package es.ipartek;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Valeria", "Montes", 23, "Bilbao"));
        personas.add(new Persona("Ignacio", "Herrera", 17, "Madrid"));
        personas.add(new Persona("Camila", "Rios", 16, "Madrid"));
        personas.add(new Persona("Daniel", "Villalobos", 22, "Bilbao"));
        personas.add(new Persona("Mariana", "Duarte", 19, "Madrid"));
        personas.add(new Persona("David", "Cordero", 17, "Barcelona"));
        personas.add(new Persona("Sofia", "Lozano", 20, "Bilbao"));
        personas.add(new Persona("Ricardo", "Fuentes", 16, "Barcelona"));
        personas.add(new Persona("Paula", "Navarro", 19, "Barcelona"));
        personas.add(new Persona("Diego", "Salazar", 18, "Bilbao"));
        personas.add(new Persona("Lucia", "Campos", 21, "Bilbao"));
        personas.add(new Persona("Javier", "Molina", 23, "Madrid"));
        personas.add(new Persona("Diego", "Salazar", 18, "Bilbao"));

        //Obtención del stream ( flujo de datos )

        //Operaciones intermedias
        //FILTER -> obtener personas con edad mayor o igual a 18
        personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .forEach(System.out::println);

        System.out.println();

        //Obtener personas mayores de edad y que el nombre empiece por D
        personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .filter(p -> p.getNombre().startsWith("D"))
                .forEach(System.out::println);

        System.out.println();

        //DISTINCT -> elimina los registros duplicados
        personas.stream()
                .distinct()
                .filter(p -> p.getEdad() >= 18)
                .filter(p -> p.getNombre().startsWith("D"))
                .forEach(System.out::println);

        System.out.println();

        // MAP -> Trasformar datos
        //Mostrar nombre y apellido
        personas.stream()
                .distinct()
                .map(p -> p.getNombre().concat(" ").concat(p.getApellido()))
                .filter(s -> s.startsWith("D"))
                .forEach(System.out::println);

        System.out.println();

        //Mostrar las edades
        personas.stream()
                .distinct()
                .map(Persona::getEdad)
                .forEach(System.out::println);

        personas.stream()
                .distinct()
                .mapToInt(Persona::getEdad)
                .forEach(System.out::println);

        //Almacenar las edades en listas o Arrays
        //toList(), toArray(),
        List<Integer> edades =
                personas.stream()
                        .distinct()
                        .map(Persona::getEdad)
                        .toList();
        edades.forEach(System.out::println);

        int[] arrayEdades =
                personas.stream()
                        .distinct()
                        .mapToInt(Persona::getEdad)
                        .toArray();

        System.out.println();

        //FLATMAP -> aplanar la información
        List<Persona> grupop1 = new ArrayList<>();
        grupop1.add(new Persona("Valeria", "Montes", 23, "Bilbao"));
        grupop1.add(new Persona("Ignacio", "Herrera", 17, "Madrid"));
        grupop1.add(new Persona("Camila", "Rios", 16, "Madrid"));

        List<Persona> grupop2 = new ArrayList<>();
        grupop2.add(new Persona("Mariana", "Duarte", 19, "Madrid"));
        grupop2.add(new Persona("David", "Cordero", 17, "Barcelona"));
        grupop2.add(new Persona("Sofia", "Lozano", 20, "Bilbao"));

        List<List<Persona>> grupos = new ArrayList<>();
        grupos.add(grupop1);
        grupos.add(grupop2);

        List<Persona> todos =
                grupos.stream()
                        //.flatMap(l-> l.stream())
                        .flatMap(Collection::stream)
                        .toList();

        todos.forEach(System.out::println);

        System.out.println();

        //SORTED -> ordena los valores del stream
        //Obtener listado de personas ordenadas por su criterio predeterminado (implements Comparable -> compareTo)
        personas.stream()
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        //Obtener listado de personas ordenadas por su criterio predeterminado a la inversa
        personas.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();

        //Mostrar nombre de personas ordenadas por nombre A-Z
        personas.stream()
                .distinct()
                .map(Persona::getNombre)
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        List<Persona> listaPersonasOrdenada =
                personas.stream()
                        .distinct()
                        .sorted(Comparator.comparing(Persona::getNombre))
                        //.sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()))
                        /*.sorted(new Comparator<Persona>() {
                            @Override
                            public int compare(Persona o1, Persona o2) {
                                return o1.getNombre().compareTo(o2.getNombre());
                            }
                        })*/
                        .toList();

        listaPersonasOrdenada.forEach(System.out::println);

        System.out.println();

        //PAGINACIÓN (skip/limit)
        int numPagina = personas.size() / 5;
        for (int i = 0; i <= numPagina; i++) {
            System.out.println("Pagina: " + i);
            personas.stream()
                    .skip(i * 5)
                    .limit(5)
                    .forEach(System.out::println);
        }

        System.out.println();

        //OPERADORES FINALES ESCALARES ==========================================================
        //COUNT -> retorna el número de elementos del flujo
        //Obtener el número de personas mayores de edad no crea ninguna estructura de datos
        double numjAdultas = personas.stream()
                .distinct()
                .filter(p -> p.getEdad() >= 18)
                .count();

        System.out.println("numjAdultas = " + numjAdultas);

        //MAX / MIN -> Obtener maximo y mínimos funcionan con flujos de tipos primitivos
        //Obtener la edad mayor OptionalInt
        OptionalInt maxEdad = personas.stream()
                .mapToInt(Persona::getEdad)
                .max();

        maxEdad.ifPresentOrElse(System.out::println, () -> System.out.println("NO existe"));

        if (maxEdad.isEmpty()) {
            System.out.println("No existe");
        } else {
            System.out.println("La edad mayor es " + maxEdad);
        }

        System.out.println();

        //Obtener la persona mayor
        Persona p1 = personas.stream()
                .max(Comparator.comparing(Persona::getEdad)).orElse(null);

        System.out.println(p1);

        //SUM -> Sumatoria
        //AVERAGE -> Media

        System.out.println();

        //OPERADORES FINALES DE BUSQUEDAD
        //FindFrist -> primer valor del flujo
        //FindAny -> Cualquier valor del flujo

        //Obtener la persona más joven
        Persona per = personas.stream().sorted().findFirst().orElse(null);
        System.out.println("per = " + per);

        Persona pFindAny = personas.stream().findAny().orElse(null);

        System.out.println("pFindAny = " + pFindAny);

        //OPERADORES DE COINCIDENCIA
        //AllMatch -> retorna TRUE si todos los valores cumplen una condición dada
        //AnyMatch -> Retorna TRUE si al menos uno de los valores cumple la condición dada
        //NoneMatch -> Retorna TRUE si ninguno de los valores cumple la condición dada

        //Todos Adultos
        boolean todosAdultos = personas.stream()
                .allMatch(p-> p.getEdad() >= 18 );

        System.out.println("todosAdultos = " + todosAdultos);

        //Algunos adultos
        boolean algunoAdulto = personas.stream()
                .anyMatch(p-> p.getEdad() >= 18 );

        System.out.println("algunoAdulto = " + algunoAdulto);

        //Ningun adulto
        boolean ningunoAdulto = personas.stream()
                .noneMatch(p-> p.getEdad() >= 18 );

        System.out.println("ningunoAdulto = " + ningunoAdulto);

        //OPERADORES TERMINALES COLECTORES
        //Agrupadores
        //Agrupar personas por ciudad collect(Collectors)
        Map<String, List<Persona>> personasByCuidad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getCiudad));

        personasByCuidad.forEach((c,p) ->{
            System.out.println("Ciudad: " + c);
            p.forEach(System.out::println);
        });

        //Recuento de personas por ciudad
        Map<String, Long> recuentoByCiudad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getCiudad, Collectors.counting()));

        recuentoByCiudad.forEach((c, r) ->{
            System.out.println(c +" - "+ r);
        });

        //Particionado
        //Obtener particionados las personas mayores y menores
        Map<Boolean,List<Persona>> particionAdultos = personas.stream()
                .collect(Collectors.partitioningBy(p-> p.getEdad() >=18));

        particionAdultos.forEach((b,p) ->{
            System.out.println("Mayor de edad: " + b);
            p.forEach(System.out::println);
        });

        System.out.println();

        //Obtener lista
        List<Persona> personasBilbao = personas.stream()
                .filter(p-> p.getCiudad().equals("Bilbao")).toList();

        for (Persona pp: personasBilbao) {
            System.out.println(pp);
        }

        System.out.println();

        //LAZY MODE **********************************************************

        List<String> nombres = new ArrayList<>();
        nombres.add("Lupe");
        nombres.add("Maria");
        nombres.add("Ana");

        Stream<String> nom = nombres.stream().filter(n-> n.startsWith("A"));

        nombres.add("Ane");
        nombres.add("Anita");

        nom.forEach(System.out::println);

    }
}
