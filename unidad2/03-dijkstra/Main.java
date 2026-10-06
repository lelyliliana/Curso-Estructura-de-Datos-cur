import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,10);
        g.agregar(0,2,1);
        g.agregar(2,1,2);
        g.agregar(1,3,1);
        Grafo.Rutas r=g.dijkstra(0);
        for(int v=0; v<4; v++)System.out.println(v+": "+r.distancia(v)+" "+r.camino(v));
    }
}
