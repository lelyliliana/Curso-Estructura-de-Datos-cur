import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(6,false);
        g.agregar(0,1,2);
        g.agregar(1,2,3);
        g.agregar(3,4,1);
        Grafo.Bosque b=g.kruskal();
        System.out.println("aristas="+b.aristas().size());
        System.out.println("costo="+b.costo()+", componentes="+b.componentes());
    }
}
