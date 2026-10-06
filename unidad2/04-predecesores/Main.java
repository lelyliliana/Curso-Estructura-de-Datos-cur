import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,0);
        g.agregar(1,2,4);
        Grafo.Rutas r=g.dijkstra(0);
        System.out.println(r.camino(0));
        System.out.println(r.camino(2));
        System.out.println(r.distancia(3)==Grafo.INF?"inaccesible":"alcanzable");
        System.out.println(r.camino(3));
    }
}
