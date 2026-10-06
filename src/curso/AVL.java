package curso;
import java.util.*;
/** Conjunto de enteros: no admite duplicados. */
public final class AVL {
    private static final class Nodo {
        int clave, altura=1;
        Nodo izq, der;
        Nodo(int clave){
            this.clave=clave;
        }
    }
    private Nodo raiz;
    private static int h(Nodo n){
        return n==null?0:n.altura;
    }
    private static void actualizar(Nodo n){
        n.altura=1+Math.max(h(n.izq),h(n.der));
    }
    private static Nodo derecha(Nodo p){
        Nodo q=p.izq;
        p.izq=q.der;
        q.der=p;
        actualizar(p);
        actualizar(q);
        return q;
    }
    private static Nodo izquierda(Nodo p){
        Nodo q=p.der;
        p.der=q.izq;
        q.izq=p;
        actualizar(p);
        actualizar(q);
        return q;
    }
    private static Nodo balancear(Nodo n){
        if(n==null)return null;
        actualizar(n);
        int f=h(n.izq)-h(n.der);
        if(f>1){
            if(h(n.izq.izq)<h(n.izq.der))n.izq=izquierda(n.izq);
            return derecha(n);
        }
        if(f< -1){
            if(h(n.der.der)<h(n.der.izq))n.der=derecha(n.der);
            return izquierda(n);
        }
        return n;
    }
    public void insertar(int k){
        raiz=insertar(raiz,k);
    }
    private static Nodo insertar(Nodo n,int k){
        if(n==null)return new Nodo(k);
        if(k<n.clave)n.izq=insertar(n.izq,k);
        else if(k>n.clave)n.der=insertar(n.der,k);
        else return n;
        return balancear(n);
    }
    public void eliminar(int k){
        raiz=eliminar(raiz,k);
    }
    private static Nodo eliminar(Nodo n,int k){
        if(n==null)return null;
        if(k<n.clave)n.izq=eliminar(n.izq,k);
        else if(k>n.clave)n.der=eliminar(n.der,k);
        else {
            if(n.izq==null)return n.der;
            if(n.der==null)return n.izq;
            Nodo s=n.der;
            while(s.izq!=null)s=s.izq;
            n.clave=s.clave;
            n.der=eliminar(n.der,s.clave);
        }
        return balancear(n);
    }
    public boolean contiene(int k){
        Nodo n=raiz;
        while(n!=null){
            if(k==n.clave)return true;
            n=k<n.clave?n.izq:n.der;
        }
        return false;
    }
    public int altura(){
        return h(raiz);
    }
    public List<Integer> orden(){
        List<Integer> r=new ArrayList<>();
        orden(raiz,r);
        return List.copyOf(r);
    }
    private static void orden(Nodo n,List<Integer> r){
        if(n!=null){
            orden(n.izq,r);
            r.add(n.clave);
            orden(n.der,r);
        }
    }
    public void verificar(){
        verificar(raiz,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    private static int verificar(Nodo n,long min,long max){
        if(n==null)return 0;
        if(n.clave<=min||n.clave>=max)throw new IllegalStateException("Orden BST");
        int a=verificar(n.izq,min,n.clave),b=verificar(n.der,n.clave,max);
        if(Math.abs(a-b)>1||n.altura!=1+Math.max(a,b))throw new IllegalStateException("Balance AVL");
        return n.altura;
    }
}
