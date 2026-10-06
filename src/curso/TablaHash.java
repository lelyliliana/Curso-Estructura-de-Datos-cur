package curso;
import java.util.*;
/** Sondeo lineal con tumbas y reconstrucción antes de superar 0.5 de ocupación usada. */
public final class TablaHash {
    private int[] claves=new int[8];
    private String[] valores=new String[8];
    private byte[] estados=new byte[8];
    // 0 libre, 1 activo, 2 tumba
    private int tamano,usados;
    public int tamano(){
        return tamano;
    }
    public int capacidad(){
        return claves.length;
    }
    private int indice(int k){
        return Math.floorMod(Integer.hashCode(k),claves.length);
    }
    private int localizar(int k){
        int i=indice(k);
        for(int j=0; j<claves.length; j++,i=(i+1)%claves.length){
            if(estados[i]==0)return -1;
            if(estados[i]==1&&claves[i]==k)return i;
        }return -1;
    }
    public String buscar(int k){
        int i=localizar(k);
        return i<0?null:valores[i];
    }
    public void poner(int k,String v){
        Objects.requireNonNull(v);
        int actual=localizar(k);
        if(actual>=0){
            valores[actual]=v;
            return;
        }
        if((usados+1)*2>claves.length)reconstruir(tamano*4<claves.length?claves.length:Math.multiplyExact(claves.length,2));
        int i=indice(k),tumba=-1;
        while(estados[i]!=0){
            if(estados[i]==2&&tumba<0)tumba=i;
            i=(i+1)%claves.length;
        }
        if(tumba>=0)i=tumba;
        else usados++;
        estados[i]=1;
        claves[i]=k;
        valores[i]=v;
        tamano++;
    }
    public void eliminar(int k){
        int i=localizar(k);
        if(i>=0){
            estados[i]=2;
            valores[i]=null;
            tamano--;
        }
    }
    private void reconstruir(int capacidad){
        int[] antiguas=claves;
        String[] v=valores;
        byte[] e=estados;
        claves=new int[capacidad];
        valores=new String[capacidad];
        estados=new byte[capacidad];
        tamano=usados=0;
        for(int i=0; i<e.length; i++)if(e[i]==1)poner(antiguas[i],v[i]);
    }
    public void verificar(){
        int n=0,u=0;
        Set<Integer> vistas=new HashSet<>();
        for(int i=0; i<estados.length; i++){
            if(estados[i]!=0)u++;
            if(estados[i]==1){
                n++;
                if(!vistas.add(claves[i])||localizar(claves[i])!=i||valores[i]==null)throw new IllegalStateException("Tabla hash");
            }
        }if(n!=tamano||u!=usados)throw new IllegalStateException("Contadores hash");
    }
}
