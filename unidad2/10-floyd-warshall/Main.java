import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,4);
        g.agregar(1,2,-2);
        g.agregar(2,3,3);
        g.agregar(0,3,10);
        Grafo.Floyd f=g.floyd();
        System.out.println(f.distancia(0,3));
        System.out.println(f.camino(0,3));
        System.out.println(f.camino(3,0));
    }
}
