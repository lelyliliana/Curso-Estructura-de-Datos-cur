import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int[] secuencia:new int[][]{
            {
                30,20,10
            },{
                10,20,30
            }
        }){
            AVL a=new AVL();
            for(int k:secuencia)a.insertar(k);
            a.verificar();
            System.out.println(a.orden()+"; altura="+a.altura());
        }
    }
}
