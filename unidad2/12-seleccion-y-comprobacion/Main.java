import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,false);
        g.agregar(0,1,3);
        g.agregar(1,2,2);
        g.agregar(0,2,10);
        g.agregar(2,3,4);
        Grafo.Rutas r=g.dijkstra(0);
        Grafo.Floyd f=g.floyd();
        for(int v=0; v<4; v++)System.out.println(v+": "+r.distancia(v)+" = "+f.distancia(0,v));
        System.out.println("bosque="+g.kruskal().costo());
    }
}
