import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(double[] x:new double[][]{
            {
                0,0
            },{
                0,1
            },{
                1,0
            },{
                1,1
            }
        }){
            double[] h=Modelos.capa(x,new double[][]{
                {
                    1,1
                },{
                    1,1
                }
            },new double[]{
                0,-1
            },true);
            double p=Modelos.sigmoide(Modelos.producto(h,new double[]{
                2,-4
            },-1));
            System.out.println(Arrays.toString(x)+" -> "+(p>=0.5?1:0));
        }
    }
}
