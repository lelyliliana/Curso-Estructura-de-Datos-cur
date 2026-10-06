import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            20,10,30,5,15
        })a.insertar(k);
        System.out.println("altura="+a.altura());
        a.verificar();
        System.out.println(a.orden());
    }
}
