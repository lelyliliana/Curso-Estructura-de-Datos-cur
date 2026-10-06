package curso;
import java.io.*;
import java.util.*;
/** Lectura incremental de grupo;valor, sin encabezado ni campos entrecomillados. */
public final class Flujo {
    private Flujo(){
    }
    public static final class Estadistica {
        private long n;
        private double media,m2;
        private void agregar(double x){
            n=Math.incrementExact(n);
            double delta=x-media;
            media+=delta/n;
            m2+=delta*(x-media);
        }
        public long cantidad(){
            return n;
        }
        public double media(){
            return media;
        }
        public double varianzaPoblacional(){
            return n==0?0:Math.max(0,m2/n);
        }
        @Override public String toString(){
            return "n="+n+", media="+media;
        }
    }
    public static Map<String,Estadistica> resumir(Reader entrada)throws IOException{
        BufferedReader r=entrada instanceof BufferedReader b?b:new BufferedReader(entrada);
        Map<String,Estadistica> resultado=new TreeMap<>();
        String linea;
        int numero=0;
        while((linea=r.readLine())!=null){
            numero++;
            String[] p=linea.split(";",-1);
            if(p.length!=2||p[0].isBlank()||p[0].length()>80)throw new IllegalArgumentException("Registro inválido en línea "+numero);
            double x;
            try{
                x=Double.parseDouble(p[1]);
            }catch(NumberFormatException e){
                throw new IllegalArgumentException("Valor inválido en línea "+numero,e);
            }Modelos.finito(x);
            if(Math.abs(x)>1e6)throw new IllegalArgumentException("Valor fuera de límites");
            resultado.computeIfAbsent(p[0],k->new Estadistica()).agregar(x);
        }
        return Collections.unmodifiableMap(resultado);
    }
}
