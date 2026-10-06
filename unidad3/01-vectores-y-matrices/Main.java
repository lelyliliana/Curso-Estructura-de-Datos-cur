import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double[] x={
            2,3
        };
        System.out.println(Modelos.producto(x,new double[]{
            4,-1
        },1));
        System.out.println(Arrays.toString(Modelos.capa(x,new double[][]{
            {
                4,-1
            },{
                -2,5
            }
        },new double[]{
            1,0
        },true)));
    }
}
