import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Red r=Red.ejemplo();
        System.out.println(r.ruta("a","d"));
        System.out.println("costo de red mínima="+r.bosque().costo());
        System.out.println("componentes="+r.bosque().componentes());
    }
}
