import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            30,10,20,20
        })a.insertar(k);
        a.verificar();
        System.out.println(a.orden());
        System.out.println(a.contiene(20));
    }
}
