package curso;
import java.util.*;
/** Operaciones pequeñas de álgebra lineal y aprendizaje supervisado. */
public final class Modelos {
    private Modelos(){
    }
    public static void finito(double x){
        if(!Double.isFinite(x))throw new IllegalArgumentException("Número no finito");
    }
    public static double producto(double[] x,double[] w,double sesgo){
        if(x.length!=w.length)throw new IllegalArgumentException("Dimensiones diferentes");
        finito(sesgo);
        double z=sesgo;
        for(int i=0; i<x.length; i++){
            finito(x[i]);
            finito(w[i]);
            z+=x[i]*w[i];
        }finito(z);
        return z;
    }
    public static double sigmoide(double z){
        finito(z);
        if(z>=0)return 1/(1+Math.exp(-z));
        double e=Math.exp(z);
        return e/(1+e);
    }
    public static double[] capa(double[] x,double[][] pesos,double[] sesgos,boolean relu){
        if(pesos.length!=sesgos.length)throw new IllegalArgumentException("Sesgos incompatibles");
        double[] y=new double[pesos.length];
        for(int j=0; j<y.length; j++){
            double z=producto(x,pesos[j],sesgos[j]);
            y[j]=relu?Math.max(0,z):sigmoide(z);
        }return y;
    }
    public static final class Perceptron {
        private final double[] w;
        private double b;
        public Perceptron(int dimensiones){
            if(dimensiones<1)throw new IllegalArgumentException("Dimensiones positivas");
            w=new double[dimensiones];
        }
        public int predecir(double[] x){
            return producto(x,w,b)>=0?1:0;
        }
        public int entrenar(double[][] x,int[] y,double tasa,int epocas){
            finito(tasa);
            if(x.length==0||x.length!=y.length||tasa<=0||epocas<1)throw new IllegalArgumentException("Entrenamiento inválido");
            for(int i=0; i<x.length; i++){
                if(y[i]!=0&&y[i]!=1)throw new IllegalArgumentException("Etiqueta binaria");
                producto(x[i],w,b);
            }
            int errores=0;
            for(int e=0; e<epocas; e++){
                errores=0;
                for(int i=0; i<x.length; i++){
                    int delta=y[i]-predecir(x[i]);
                    if(delta!=0){
                        errores++;
                        for(int j=0; j<w.length; j++){
                            w[j]+=tasa*delta*x[i][j];
                            finito(w[j]);
                        }b+=tasa*delta;
                        finito(b);
                    }
                }if(errores==0)break;
            }
            return errores;
        }
    }
    public static double exactitud(int[] real,int[] predicha){
        if(real.length==0||real.length!=predicha.length)throw new IllegalArgumentException("Muestras incompatibles");
        int correctas=0;
        for(int i=0; i<real.length; i++)if(real[i]==predicha[i])correctas++;
        return (double)correctas/real.length;
    }
}
