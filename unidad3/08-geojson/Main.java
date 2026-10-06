import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(GeoJSON.exportar(List.of(new GeoJSON.Lugar("a",-75.6,6.2),new GeoJSON.Lugar("b",-75.59,6.21)),List.of(new GeoJSON.Conexion("a","b",3))));
    }
}
