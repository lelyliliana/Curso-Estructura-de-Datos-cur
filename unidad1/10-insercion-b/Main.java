import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolB b=new ArbolB(2);
        for(int k:new int[]{
            10,20,30,40,5,15,25,35
        }){
            b.insertar(k);
            b.verificar();
        }System.out.println(b.niveles());
        System.out.println(b.contiene(25));
        System.out.println(b.contiene(99));
    }
}
