import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(3,true);
        g.agregar(0,1,0);
        g.agregar(1,2,5);
        System.out.println(g.vecinos(0));
        System.out.println(g.vecinos(1));
    }
}
