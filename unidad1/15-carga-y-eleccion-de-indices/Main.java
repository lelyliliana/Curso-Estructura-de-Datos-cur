import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        TablaHash h=new TablaHash();
        for(int k=0; k<20; k++)h.poner(k,"v"+k);
        h.poner(7,"nuevo");
        h.eliminar(3);
        h.verificar();
        System.out.println("tamaño="+h.tamano()+", capacidad="+h.capacidad());
        System.out.println(h.buscar(7));
        System.out.println(h.buscar(19));
    }
}
