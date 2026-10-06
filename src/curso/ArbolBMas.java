package curso;
import java.util.*;
/** Índice B+ en memoria: claves enteras únicas, registros de texto en hojas. */
public final class ArbolBMas {
    private static final class Nodo {
        final boolean hoja;
        final ArrayList<Integer> claves=new ArrayList<>();
        final ArrayList<String> valores=new ArrayList<>();
        final ArrayList<Nodo> hijos=new ArrayList<>();
        Nodo siguiente;
        int minimo;
        Nodo(boolean hoja){
            this.hoja=hoja;
        }
    }
    private final int max;
    private Nodo raiz=new Nodo(true);
    public ArbolBMas(int maxClaves){
        if(maxClaves<3)throw new IllegalArgumentException("Máximo al menos 3");
        max=maxClaves;
    }
    private static void actualizar(Nodo n){
        if(n.hoja){
            if(!n.claves.isEmpty())n.minimo=n.claves.get(0);
        }
        else {
            n.claves.clear();
            n.minimo=n.hijos.get(0).minimo;
            for(int i=1; i<n.hijos.size(); i++)n.claves.add(n.hijos.get(i).minimo);
        }
    }
    private static int hijo(Nodo n,int k){
        int i=0;
        while(i<n.claves.size()&&k>=n.claves.get(i))i++;
        return i;
    }
    private static int posicion(Nodo n,int k){
        int i=0;
        while(i<n.claves.size()&&n.claves.get(i)<k)i++;
        return i;
    }
    public String buscar(int k){
        Nodo n=raiz;
        while(!n.hoja)n=n.hijos.get(hijo(n,k));
        int i=posicion(n,k);
        return i<n.claves.size()&&n.claves.get(i)==k?n.valores.get(i):null;
    }
    public void poner(int k,String valor){
        Objects.requireNonNull(valor);
        Nodo b=poner(raiz,k,valor);
        if(b!=null){
            Nodo r=new Nodo(false);
            r.hijos.add(raiz);
            r.hijos.add(b);
            actualizar(r);
            raiz=r;
        }
    }
    private Nodo poner(Nodo n,int k,String valor){
        if(n.hoja){
            int i=posicion(n,k);
            if(i<n.claves.size()&&n.claves.get(i)==k)n.valores.set(i,valor);
            else {
                n.claves.add(i,k);
                n.valores.add(i,valor);
            }
        }
        else {
            int i=hijo(n,k);
            Nodo b=poner(n.hijos.get(i),k,valor);
            if(b!=null)n.hijos.add(i+1,b);
        }
        actualizar(n);
        if(n.claves.size()<=max)return null;
        Nodo b=new Nodo(n.hoja);
        if(n.hoja){
            int corte=(n.claves.size()+1)/2;
            b.claves.addAll(n.claves.subList(corte,n.claves.size()));
            b.valores.addAll(n.valores.subList(corte,n.valores.size()));
            n.claves.subList(corte,n.claves.size()).clear();
            n.valores.subList(corte,n.valores.size()).clear();
            b.siguiente=n.siguiente;
            n.siguiente=b;
        }
        else {
            int corte=n.hijos.size()/2;
            b.hijos.addAll(n.hijos.subList(corte,n.hijos.size()));
            n.hijos.subList(corte,n.hijos.size()).clear();
        }
        actualizar(n);
        actualizar(b);
        return b;
    }
    private int ocupacion(Nodo n){
        return n.hoja?n.claves.size():n.hijos.size();
    }
    private int minimoPermitido(Nodo n){
        return n.hoja?(max+1)/2:(max+2)/2;
    }
    public void eliminar(int k){
        eliminar(raiz,k);
        if(!raiz.hoja&&raiz.hijos.size()==1)raiz=raiz.hijos.get(0);
    }
    private void eliminar(Nodo n,int k){
        if(n.hoja){
            int i=posicion(n,k);
            if(i<n.claves.size()&&n.claves.get(i)==k){
                n.claves.remove(i);
                n.valores.remove(i);
            }actualizar(n);
            return;
        }
        int i=hijo(n,k);
        Nodo a=n.hijos.get(i);
        eliminar(a,k);
        if(ocupacion(a)<minimoPermitido(a)){
            Nodo izq=i>0?n.hijos.get(i-1):null,der=i+1<n.hijos.size()?n.hijos.get(i+1):null;
            if(izq!=null&&ocupacion(izq)>minimoPermitido(izq)){
                if(a.hoja){
                    int j=izq.claves.size()-1;
                    a.claves.add(0,izq.claves.remove(j));
                    a.valores.add(0,izq.valores.remove(j));
                }
                else a.hijos.add(0,izq.hijos.remove(izq.hijos.size()-1));
                actualizar(izq);
                actualizar(a);
            }else if(der!=null&&ocupacion(der)>minimoPermitido(der)){
                if(a.hoja){
                    a.claves.add(der.claves.remove(0));
                    a.valores.add(der.valores.remove(0));
                }
                else a.hijos.add(der.hijos.remove(0));
                actualizar(der);
                actualizar(a);
            }else if(izq!=null){
                fusionar(izq,a);
                n.hijos.remove(i);
            }
            else if(der!=null){
                fusionar(a,der);
                n.hijos.remove(i+1);
            }
        }
        actualizar(n);
    }
    private static void fusionar(Nodo a,Nodo b){
        if(a.hoja){
            a.claves.addAll(b.claves);
            a.valores.addAll(b.valores);
            a.siguiente=b.siguiente;
        }else a.hijos.addAll(b.hijos);
        actualizar(a);
    }
    public Map<Integer,String> rango(int desde,int hasta){
        Map<Integer,String> r=new LinkedHashMap<>();
        if(desde>hasta)return Collections.unmodifiableMap(r);
        Nodo n=raiz;
        while(!n.hoja)n=n.hijos.get(hijo(n,desde));
        int i=posicion(n,desde);
        while(n!=null){
            for(; i<n.claves.size(); i++){
                int k=n.claves.get(i);
                if(k>hasta)return Collections.unmodifiableMap(r);
                r.put(k,n.valores.get(i));
            }n=n.siguiente;
            i=0;
        }
        return Collections.unmodifiableMap(r);
    }
    public void verificar(){
        List<Nodo> hojas=new ArrayList<>();
        verificar(raiz,true,0,new int[]{
            -1
        },hojas);
        long anterior=Long.MIN_VALUE;
        for(int i=0; i<hojas.size(); i++){
            Nodo n=hojas.get(i);
            if(n.siguiente!=(i+1<hojas.size()?hojas.get(i+1):null))throw new IllegalStateException("Cadena de hojas");
            for(int k:n.claves){
                if(k<=anterior)throw new IllegalStateException("Orden global B+");
                anterior=k;
            }
        }
    }
    private void verificar(Nodo n,boolean esRaiz,int prof,int[] profundidad,List<Nodo> hojas){
        if(n.claves.size()>max||!esRaiz&&ocupacion(n)<minimoPermitido(n))throw new IllegalStateException("Ocupación B+");
        if(n.hoja){
            if(n.claves.size()!=n.valores.size())throw new IllegalStateException("Registros");
            if(profundidad[0]<0)profundidad[0]=prof;
            else if(profundidad[0]!=prof)throw new IllegalStateException("Altura B+");
            hojas.add(n);
        }
        else {
            if(n.hijos.size()!=n.claves.size()+1||esRaiz&&n.hijos.size()<2)throw new IllegalStateException("Hijos B+");
            for(int i=0; i<n.hijos.size(); i++){
                Nodo h=n.hijos.get(i);
                verificar(h,false,prof+1,profundidad,hojas);
                if(i>0&&n.claves.get(i-1)!=h.minimo)throw new IllegalStateException("Separador B+");
            }
        }
        if(!n.claves.isEmpty()&&n.minimo!=(n.hoja?n.claves.get(0):n.hijos.get(0).minimo))throw new IllegalStateException("Mínimo B+");
    }
}
