import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int[] s:new int[][]{
            {
                30,10,20
            },{
                10,30,20
            }
        }){
            AVL a=new AVL();
            for(int k:s)a.insertar(k);
            a.verificar();
            System.out.println(a.orden()+"; altura="+a.altura());
        }
    }
}
