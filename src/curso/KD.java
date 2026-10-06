package curso;
import java.util.*;
/** Árbol k-d estático para puntos cartesianos 2D, no distancias geodésicas. */
public final class KD {
    public record Punto(String id,double x,double y){
        public Punto{
            Objects.requireNonNull(id);
            Modelos.finito(x);
            Modelos.finito(y);
            if(Math.abs(x)>1e6||Math.abs(y)>1e6)throw new IllegalArgumentException("Coordenada cartesiana fuera del laboratorio");
        }
    }
    private static final class Nodo {
        Punto p;
        int eje;
        Nodo a,b;
    }
    private final Nodo raiz;
    public KD(List<Punto> puntos){
        Set<String> ids=new HashSet<>();
        for(Punto p:puntos)if(!ids.add(p.id))throw new IllegalArgumentException("Identificador duplicado");
        raiz=construir(new ArrayList<>(puntos),0);
    }
    private static Nodo construir(List<Punto> p,int profundidad){
        if(p.isEmpty())return null;
        int eje=profundidad%2;
        p.sort(Comparator.comparingDouble((Punto x)->eje==0?x.x:x.y).thenComparing(Punto::id));
        int m=p.size()/2;
        Nodo n=new Nodo();
        n.p=p.get(m);
        n.eje=eje;
        n.a=construir(new ArrayList<>(p.subList(0,m)),profundidad+1);
        n.b=construir(new ArrayList<>(p.subList(m+1,p.size())),profundidad+1);
        return n;
    }
    public static double distancia2(Punto a,Punto b){
        double x=a.x-b.x,y=a.y-b.y;
        return x*x+y*y;
    }
    public Punto cercano(Punto consulta){
        return cercano(raiz,consulta,null);
    }
    private static Punto mejor(Punto a,Punto b,Punto q){
        if(a==null)return b;
        if(b==null)return a;
        int c=Double.compare(distancia2(a,q),distancia2(b,q));
        return c<0||c==0&&a.id.compareTo(b.id)<=0?a:b;
    }
    private static Punto cercano(Nodo n,Punto q,Punto mejor){
        if(n==null)return mejor;
        mejor=mejor(mejor,n.p,q);
        double delta=n.eje==0?q.x-n.p.x:q.y-n.p.y;
        Nodo primero=delta<0?n.a:n.b,segundo=delta<0?n.b:n.a;
        mejor=cercano(primero,q,mejor);
        if(delta*delta<=distancia2(mejor,q))mejor=cercano(segundo,q,mejor);
        return mejor;
    }
    public List<String> rectangulo(double minX,double minY,double maxX,double maxY){
        Modelos.finito(minX);
        Modelos.finito(minY);
        Modelos.finito(maxX);
        Modelos.finito(maxY);
        if(minX>maxX||minY>maxY)throw new IllegalArgumentException("Rectángulo invertido");
        List<String> r=new ArrayList<>();
        rectangulo(raiz,minX,minY,maxX,maxY,r);
        Collections.sort(r);
        return List.copyOf(r);
    }
    private static void rectangulo(Nodo n,double x1,double y1,double x2,double y2,List<String> r){
        if(n==null)return;
        Punto p=n.p;
        if(p.x>=x1&&p.x<=x2&&p.y>=y1&&p.y<=y2)r.add(p.id);
        double min=n.eje==0?x1:y1,max=n.eje==0?x2:y2,v=n.eje==0?p.x:p.y;
        if(min<=v)rectangulo(n.a,x1,y1,x2,y2,r);
        if(max>=v)rectangulo(n.b,x1,y1,x2,y2,r);
    }
}
