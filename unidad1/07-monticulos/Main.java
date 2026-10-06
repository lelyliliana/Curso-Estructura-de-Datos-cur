import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Monticulo m=new Monticulo();
        for(int x:new int[]{
            7,2,9,1,2
        })m.agregar(x);
        m.verificar();
        System.out.println(m.arreglo());
        while(m.tamano()>0)System.out.println(m.extraer());
    }
}
