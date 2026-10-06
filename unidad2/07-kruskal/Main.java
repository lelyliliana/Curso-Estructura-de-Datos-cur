import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(3,false);
        g.agregar(0,1,1);
        g.agregar(1,2,2);
        g.agregar(0,2,5);
        Grafo.Bosque b=g.kruskal();
        System.out.println(b.aristas());
        System.out.println("costo="+b.costo());
    }
}
