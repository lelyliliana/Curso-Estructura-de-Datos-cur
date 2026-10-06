import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Disjuntos d=new Disjuntos(5);
        System.out.println(d.unir(0,1));
        d.unir(1,2);
        System.out.println(d.unir(0,2));
        d.unir(3,4);
        System.out.println("componentes="+d.componentes());
        System.out.println(d.representante(0)==d.representante(2));
    }
}
