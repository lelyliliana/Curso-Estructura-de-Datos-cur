import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolB b=new ArbolB(2);
        for(int k=1; k<=12; k++)b.insertar(k);
        for(int k:new int[]{
            2,6,9,1
        }){
            b.eliminar(k);
            b.verificar();
            System.out.println(b.orden());
        }
    }
}
