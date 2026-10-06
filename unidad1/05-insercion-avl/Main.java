import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            10,20,30,40,50,25
        }){
            a.insertar(k);
            a.verificar();
            System.out.println(k+" -> "+a.orden());
        }System.out.println("altura="+a.altura());
    }
}
