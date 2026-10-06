import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        TablaHash h=new TablaHash();
        h.poner(1,"uno");
        h.poner(9,"nueve");
        h.poner(17,"diecisiete");
        h.eliminar(9);
        h.verificar();
        System.out.println(h.buscar(17));
        System.out.println(h.buscar(9));
        System.out.println("tamaño="+h.tamano());
        TablaEncadenada c=new TablaEncadenada(2);
        c.poner(1,"uno");
        c.poner(3,"tres");
        c.poner(5,"cinco");
        c.eliminar(3);
        System.out.println(c.buscar(5)+"; carga="+c.carga());
    }
}
