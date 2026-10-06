package curso;
import java.util.*;
/** Árbol B de grado mínimo t: entre t-1 y 2t-1 claves por nodo no raíz. */
public final class ArbolB {
    private static final class Nodo {
        final ArrayList<Integer> claves=new ArrayList<>();
        final ArrayList<Nodo> hijos=new ArrayList<>();
        boolean hoja(){
            return hijos.isEmpty();
        }
    }
    private final int t;
    private Nodo raiz=new Nodo();
    public ArbolB(int t){
        if(t<2)throw new IllegalArgumentException("t debe ser al menos 2");
        this.t=t;
    }
    private static int posicion(Nodo n,int k){
        int i=0;
        while(i<n.claves.size()&&n.claves.get(i)<k)i++;
        return i;
    }
    public boolean contiene(int k){
        return contiene(raiz,k);
    }
    private static boolean contiene(Nodo n,int k){
        int i=posicion(n,k);
        return i<n.claves.size()&&n.claves.get(i)==k||!n.hoja()&&contiene(n.hijos.get(i),k);
    }
    public void insertar(int k){
        if(contiene(k))return;
        if(raiz.claves.size()==2*t-1){
            Nodo r=new Nodo();
            r.hijos.add(raiz);
            dividir(r,0);
            raiz=r;
        }
        insertarNoLleno(raiz,k);
    }
    private void dividir(Nodo p,int i){
        Nodo a=p.hijos.get(i),b=new Nodo();
        int medio=a.claves.get(t-1);
        b.claves.addAll(a.claves.subList(t,a.claves.size()));
        a.claves.subList(t-1,a.claves.size()).clear();
        if(!a.hoja()){
            b.hijos.addAll(a.hijos.subList(t,a.hijos.size()));
            a.hijos.subList(t,a.hijos.size()).clear();
        }
        p.claves.add(i,medio);
        p.hijos.add(i+1,b);
    }
    private void insertarNoLleno(Nodo n,int k){
        int i=posicion(n,k);
        if(n.hoja()){
            n.claves.add(i,k);
            return;
        }
        if(n.hijos.get(i).claves.size()==2*t-1){
            dividir(n,i);
            if(k>n.claves.get(i))i++;
        }
        insertarNoLleno(n.hijos.get(i),k);
    }
    public void eliminar(int k){
        eliminar(raiz,k);
        if(raiz.claves.isEmpty()&&!raiz.hoja())raiz=raiz.hijos.get(0);
    }
    private void eliminar(Nodo n,int k){
        int i=posicion(n,k);
        if(i<n.claves.size()&&n.claves.get(i)==k){
            if(n.hoja()){
                n.claves.remove(i);
                return;
            }
            Nodo a=n.hijos.get(i),b=n.hijos.get(i+1);
            if(a.claves.size()>=t){
                Nodo x=a;
                while(!x.hoja())x=x.hijos.get(x.hijos.size()-1);
                int pred=x.claves.get(x.claves.size()-1);
                n.claves.set(i,pred);
                eliminar(a,pred);
            }
            else if(b.claves.size()>=t){
                Nodo x=b;
                while(!x.hoja())x=x.hijos.get(0);
                int suc=x.claves.get(0);
                n.claves.set(i,suc);
                eliminar(b,suc);
            }
            else {
                fusionar(n,i);
                eliminar(a,k);
            }
            return;
        }
        if(n.hoja())return;
        if(n.hijos.get(i).claves.size()==t-1){
            if(i>0&&n.hijos.get(i-1).claves.size()>=t){
                Nodo a=n.hijos.get(i),b=n.hijos.get(i-1);
                a.claves.add(0,n.claves.get(i-1));
                n.claves.set(i-1,b.claves.remove(b.claves.size()-1));
                if(!b.hoja())a.hijos.add(0,b.hijos.remove(b.hijos.size()-1));
            }else if(i<n.claves.size()&&n.hijos.get(i+1).claves.size()>=t){
                Nodo a=n.hijos.get(i),b=n.hijos.get(i+1);
                a.claves.add(n.claves.get(i));
                n.claves.set(i,b.claves.remove(0));
                if(!b.hoja())a.hijos.add(b.hijos.remove(0));
            }else if(i<n.claves.size())fusionar(n,i);
            else {
                fusionar(n,i-1);
                i--;
            }
        }
        eliminar(n.hijos.get(i),k);
    }
    private static void fusionar(Nodo p,int i){
        Nodo a=p.hijos.get(i),b=p.hijos.remove(i+1);
        a.claves.add(p.claves.remove(i));
        a.claves.addAll(b.claves);
        a.hijos.addAll(b.hijos);
    }
    public List<Integer> orden(){
        List<Integer> r=new ArrayList<>();
        orden(raiz,r);
        return List.copyOf(r);
    }
    private static void orden(Nodo n,List<Integer> r){
        for(int i=0; i<n.claves.size(); i++){
            if(!n.hoja())orden(n.hijos.get(i),r);
            r.add(n.claves.get(i));
        }if(!n.hoja())orden(n.hijos.get(n.claves.size()),r);
    }
    public List<List<Integer>> niveles(){
        List<List<Integer>> r=new ArrayList<>();
        ArrayDeque<Nodo> q=new ArrayDeque<>();
        q.add(raiz);
        while(!q.isEmpty()){
            int s=q.size();
            List<Integer> nivel=new ArrayList<>();
            for(int j=0; j<s; j++){
                Nodo n=q.remove();
                nivel.addAll(n.claves);
                q.addAll(n.hijos);
            }r.add(List.copyOf(nivel));
        }return List.copyOf(r);
    }
    public void verificar(){
        verificar(raiz,true,Long.MIN_VALUE,Long.MAX_VALUE,0,new int[]{
            -1
        });
    }
    private void verificar(Nodo n,boolean esRaiz,long min,long max,int profundidad,int[] hojas){
        int s=n.claves.size();
        if(s>2*t-1||!esRaiz&&s<t-1||esRaiz&&!n.hoja()&&s==0)throw new IllegalStateException("Ocupación B");
        long anterior=min;
        for(int k:n.claves){
            if(k<=anterior||k>=max)throw new IllegalStateException("Orden B");
            anterior=k;
        }
        if(n.hoja()){
            if(hojas[0]<0)hojas[0]=profundidad;
            else if(hojas[0]!=profundidad)throw new IllegalStateException("Profundidad de hojas");
        }
        else {
            if(n.hijos.size()!=s+1)throw new IllegalStateException("Hijos B");
            for(int i=0; i<=s; i++)verificar(n.hijos.get(i),false,i==0?min:n.claves.get(i-1),i==s?max:n.claves.get(i),profundidad+1,hojas);
        }
    }
}
