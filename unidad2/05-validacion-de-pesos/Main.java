import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(2,true);
        g.agregar(0,1,-1);
        try{
            g.dijkstra(0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
