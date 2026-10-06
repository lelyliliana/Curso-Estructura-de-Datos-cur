import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolBMas b=new ArbolBMas(3);
        for(int k:new int[]{
            8,2,6,4,10,12
        })b.poner(k,"v"+k);
        b.poner(6,"seis");
        b.verificar();
        System.out.println(b.buscar(6));
        System.out.println(b.buscar(5));
        System.out.println(b.rango(0,20));
    }
}
