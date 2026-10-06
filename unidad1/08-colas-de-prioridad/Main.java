import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        record Tarea(String nombre,int prioridad){
        }
        PriorityQueue<Tarea> q=new PriorityQueue<>(Comparator.comparingInt(Tarea::prioridad).thenComparing(Tarea::nombre));
        q.add(new Tarea("respaldo",3));
        q.add(new Tarea("consulta",1));
        q.add(new Tarea("archivo",1));
        while(!q.isEmpty())System.out.println(q.remove());
        int[] datos={
            7,-1,3,3,0
        };
        OrdenMonticulo.ordenar(datos);
        System.out.println(Arrays.toString(datos));
    }
}
