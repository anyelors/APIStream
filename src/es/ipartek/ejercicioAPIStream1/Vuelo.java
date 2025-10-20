package es.ipartek.ejercicioAPIStream1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Vuelo implements Comparable<Vuelo>{

    private String codigoVuelo;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalTime horaLlegada;
    private int numeroPasajeros;

    public Vuelo(String codigoVuelo, String origen, String destino, LocalDate fechaSalida, LocalTime horaLlegada, int numeroPasajeros) {
        this.codigoVuelo = codigoVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaLlegada = horaLlegada;
        this.numeroPasajeros = numeroPasajeros;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    @Override
    public String toString() {
        return "Vuelo [" +
                "Código Vuelo = '" + codigoVuelo + '\'' +
                ", Origen = '" + origen + '\'' +
                ", Destino = '" + destino + '\'' +
                ", Fecha Salida = " + fechaSalida +
                ", Hora Llegada = " + horaLlegada +
                ", Numero Pasajeros = " + numeroPasajeros +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vuelo vuelo)) return false;
        return numeroPasajeros == vuelo.numeroPasajeros && Objects.equals(codigoVuelo, vuelo.codigoVuelo) && Objects.equals(origen, vuelo.origen) && Objects.equals(destino, vuelo.destino) && Objects.equals(fechaSalida, vuelo.fechaSalida) && Objects.equals(horaLlegada, vuelo.horaLlegada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVuelo, origen, destino, fechaSalida, horaLlegada, numeroPasajeros);
    }

    @Override
    public int compareTo(Vuelo o) {
        return Integer.compare(this.numeroPasajeros, o.numeroPasajeros);
    }
}
