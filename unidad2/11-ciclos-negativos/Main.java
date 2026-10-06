import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,2);
        g.agregar(1,2,-3);
        g.agregar(2,1,1);
        g.agregar(2,3,2);
        Grafo.Floyd f=g.floyd();
        System.out.println(f.cicloAfecta(0,3));
        System.out.println(f.cicloAfecta(3,0));
        try{
            f.distancia(0,3);
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }System.out.println(f.distancia(3,3));
    }
}
