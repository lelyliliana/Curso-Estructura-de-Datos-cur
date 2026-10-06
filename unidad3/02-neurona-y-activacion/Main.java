import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(double z:new double[]{
            0,1000,-1000
        })System.out.println(z+" -> "+Modelos.sigmoide(z));
        System.out.println(Modelos.sigmoide(Modelos.producto(new double[]{
            1,2
        },new double[]{
            2,-1
        },0)));
    }
}
