import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double[][] x={
            {
                0,0
            },{
                0,1
            },{
                1,0
            },{
                1,1
            }
        };
        int[] y={
            0,1,1,1
        };
        Modelos.Perceptron p=new Modelos.Perceptron(2);
        p.entrenar(x,y,1,30);
        int[] pred=new int[x.length];
        for(int i=0; i<x.length; i++){
            pred[i]=p.predecir(x[i]);
            System.out.println(Arrays.toString(x[i])+" -> "+pred[i]);
        }System.out.println("exactitud de entrenamiento="+Modelos.exactitud(y,pred));
    }
}
