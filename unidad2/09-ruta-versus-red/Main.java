import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(3,false);
        g.agregar(0,1,2);
        g.agregar(1,2,2);
        g.agregar(0,2,3);
        System.out.println("ruta 0 a 2="+g.dijkstra(0).distancia(2));
        System.out.println("red mínima="+g.kruskal().costo());
    }
}
