import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            20,10,30,5,15,25,35
        })a.insertar(k);
        for(int k:new int[]{
            20,5,35
        }){
            a.eliminar(k);
            a.verificar();
            System.out.println(a.orden());
        }
    }
}
