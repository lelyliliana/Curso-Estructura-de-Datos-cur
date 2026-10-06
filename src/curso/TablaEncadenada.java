package curso;
import java.util.*;
/** Mapa de capacidad fija para estudiar cubetas; admite carga mayor que uno. */
public final class TablaEncadenada {
    private static final class Entrada {
        final int clave;
        String valor;
        Entrada(int clave,String valor){
            this.clave=clave;
            this.valor=valor;
        }
    }
    private final List<List<Entrada>> cubetas=new ArrayList<>();
    private int tamano;
    public TablaEncadenada(int capacidad){
        if(capacidad<1)throw new IllegalArgumentException("Capacidad positiva");
        for(int i=0; i<capacidad; i++)cubetas.add(new ArrayList<>());
    }
    private List<Entrada> cubeta(int k){
        return cubetas.get(Math.floorMod(k,cubetas.size()));
    }
    public void poner(int k,String v){
        Objects.requireNonNull(v);
        List<Entrada> c=cubeta(k);
        for(Entrada e:c)if(e.clave==k){
            e.valor=v;
            return;
        }
        c.add(new Entrada(k,v));
        tamano++;
    }
    public String buscar(int k){
        for(Entrada e:cubeta(k))if(e.clave==k)return e.valor;
        return null;
    }
    public void eliminar(int k){
        List<Entrada> c=cubeta(k);
        for(int i=0; i<c.size(); i++)if(c.get(i).clave==k){
            c.remove(i);
            tamano--;
            return;
        }
    }
    public int tamano(){
        return tamano;
    }
    public double carga(){
        return (double)tamano/cubetas.size();
    }
}
