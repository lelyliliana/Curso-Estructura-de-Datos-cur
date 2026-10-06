import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Grafo.Paso> q=new PriorityQueue<>(Comparator.comparingLong(Grafo.Paso::costo).thenComparingInt(Grafo.Paso::vertice));
        q.add(new Grafo.Paso(1,10));
        q.add(new Grafo.Paso(1,3));
        while(!q.isEmpty())System.out.println(q.remove());
    }
}
