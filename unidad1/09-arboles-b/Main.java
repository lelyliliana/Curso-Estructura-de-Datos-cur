import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolB b=new ArbolB(2);
        for(int k=1; k<=9; k++)b.insertar(k);
        b.verificar();
        System.out.println(b.niveles());
        System.out.println(b.orden());
    }
}
