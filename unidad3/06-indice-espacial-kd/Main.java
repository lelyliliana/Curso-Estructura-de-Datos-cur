import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        KD k=new KD(List.of(new KD.Punto("a",0,0),new KD.Punto("b",4,0),new KD.Punto("c",0,4)));
        System.out.println(k.cercano(new KD.Punto("q",3,0)).id());
        System.out.println(k.rectangulo(0,0,1,4));
    }
}
