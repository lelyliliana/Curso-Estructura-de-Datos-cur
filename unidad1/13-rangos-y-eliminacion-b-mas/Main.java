import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolBMas b=new ArbolBMas(3);
        for(int k=1; k<=10; k++)b.poner(k,"v"+k);
        System.out.println(b.rango(3,7));
        for(int k:new int[]{
            3,4,5,6
        }){
            b.eliminar(k);
            b.verificar();
        }System.out.println(b.rango(3,7));
        System.out.println(b.rango(1,10));
    }
}
