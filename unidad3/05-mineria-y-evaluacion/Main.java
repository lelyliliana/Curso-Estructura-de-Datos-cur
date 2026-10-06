import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double[] x={
            1,2,5,6
        };
        int[] y={
            0,0,1,1
        };
        double mejor=0;
        int minimo=Integer.MAX_VALUE;
        for(int i=0; i<x.length-1; i++){
            double t=(x[i]+x[i+1])/2;
            int errores=0;
            for(int j=0; j<x.length; j++)if((x[j]>t?1:0)!=y[j])errores++;
            if(errores<minimo){
                minimo=errores;
                mejor=t;
            }
        }double[] prueba={
            1.5,5.5
        };
        int[] pred=new int[2];
        for(int i=0; i<2; i++)pred[i]=prueba[i]>mejor?1:0;
        System.out.println("umbral="+mejor);
        System.out.println("exactitud de prueba="+Modelos.exactitud(new int[]{
            0,1
        },pred));
    }
}
