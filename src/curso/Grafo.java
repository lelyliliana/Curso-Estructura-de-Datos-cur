package curso;
import java.util.*;
/** Grafo ponderado con vértices 0..n-1; acepta aristas paralelas y lazos. */
public final class Grafo {
    public static final long INF=Long.MAX_VALUE/4;
    public record Arista(int origen,int destino,long peso){
    }
    public record Paso(int vertice,long costo){
    }
    public record Bosque(List<Arista> aristas,long costo,int componentes){
        public Bosque{
            aristas=List.copyOf(aristas);
        }
    }
    private final List<List<Arista>> ady=new ArrayList<>();
    private final List<Arista> aristas=new ArrayList<>();
    private final boolean dirigido;
    public Grafo(int n,boolean dirigido){
        if(n<0||n>10000)throw new IllegalArgumentException("Entre 0 y 10000 vértices");
        this.dirigido=dirigido;
        for(int i=0; i<n; i++)ady.add(new ArrayList<>());
    }
    public int vertices(){
        return ady.size();
    }
    private void validar(int v){
        if(v<0||v>=vertices())throw new IllegalArgumentException("Vértice inválido");
    }
    public void agregar(int a,int b,long peso){
        validar(a);
        validar(b);
        if(peso< -1000000000L||peso>1000000000L)throw new IllegalArgumentException("Peso fuera de límites");
        Arista e=new Arista(a,b,peso);
        aristas.add(e);
        ady.get(a).add(e);
        if(!dirigido&&a!=b)ady.get(b).add(new Arista(b,a,peso));
    }
    public List<Arista> aristas(){
        return List.copyOf(aristas);
    }
    public List<Arista> vecinos(int v){
        validar(v);
        return List.copyOf(ady.get(v));
    }
    public static final class Rutas {
        private final int origen;
        private final long[] d;
        private final int[] previo;
        private Rutas(int origen,long[] d,int[] previo){
            this.origen=origen;
            this.d=d;
            this.previo=previo;
        }
        public long distancia(int v){
            return d[v];
        }
        public List<Integer> camino(int v){
            if(d[v]==INF)return List.of();
            ArrayList<Integer> r=new ArrayList<>();
            for(int x=v; x!=-1; x=previo[x])r.add(x);
            Collections.reverse(r);
            if(r.get(0)!=origen)throw new IllegalStateException("Predecesores");
            return List.copyOf(r);
        }
    }
    public Rutas dijkstra(int origen){
        validar(origen);
        for(Arista e:aristas)if(e.peso<0)throw new IllegalArgumentException("Dijkstra requiere pesos no negativos");
        long[] d=new long[vertices()];
        Arrays.fill(d,INF);
        int[] p=new int[vertices()];
        Arrays.fill(p,-1);
        d[origen]=0;
        PriorityQueue<Paso> q=new PriorityQueue<>(Comparator.comparingLong(Paso::costo).thenComparingInt(Paso::vertice));
        q.add(new Paso(origen,0));
        while(!q.isEmpty()){
            Paso paso=q.remove();
            int u=paso.vertice;
            if(paso.costo!=d[u])continue;
            for(Arista e:ady.get(u)){
                long nueva=d[u]+e.peso;
                if(nueva<d[e.destino]){
                    d[e.destino]=nueva;
                    p[e.destino]=u;
                    q.add(new Paso(e.destino,nueva));
                }
            }
        }
        return new Rutas(origen,d,p);
    }
    public Bosque kruskal(){
        if(dirigido)throw new IllegalArgumentException("Kruskal requiere grafo no dirigido");
        List<Arista> orden=new ArrayList<>(aristas);
        orden.sort(Comparator.comparingLong(Arista::peso).thenComparingInt(Arista::origen).thenComparingInt(Arista::destino));
        Disjuntos d=new Disjuntos(vertices());
        List<Arista> elegidas=new ArrayList<>();
        long total=0;
        for(Arista e:orden)if(d.unir(e.origen,e.destino)){
            elegidas.add(e);
            total+=e.peso;
        }
        return new Bosque(elegidas,total,d.componentes());
    }
    public static final class Floyd {
        private final long[][] d;
        private final int[][] siguiente;
        private final boolean[][] afectada;
        private Floyd(long[][] d,int[][] siguiente,boolean[][] afectada){
            this.d=d;
            this.siguiente=siguiente;
            this.afectada=afectada;
        }
        public long distancia(int a,int b){
            if(afectada[a][b])throw new IllegalStateException("Ruta afectada por ciclo negativo");
            return d[a][b];
        }
        public boolean cicloAfecta(int a,int b){
            return afectada[a][b];
        }
        public List<Integer> camino(int a,int b){
            if(afectada[a][b])throw new IllegalStateException("Ruta afectada por ciclo negativo");
            if(siguiente[a][b]<0)return List.of();
            List<Integer> r=new ArrayList<>();
            r.add(a);
            while(a!=b){
                a=siguiente[a][b];
                r.add(a);
                if(r.size()>d.length+1)throw new IllegalStateException("Reconstrucción de ruta");
            }return List.copyOf(r);
        }
    }
    public Floyd floyd(){
        int n=vertices();
        if(n>500)throw new IllegalArgumentException("Floyd admite hasta 500 vértices en este laboratorio");
        long[][] d=new long[n][n];
        int[][] s=new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(d[i],INF);
            Arrays.fill(s[i],-1);
            d[i][i]=0;
            s[i][i]=i;
        }
        for(int i=0; i<n; i++)for(Arista e:ady.get(i))if(e.peso<d[i][e.destino]){
            d[i][e.destino]=e.peso;
            s[i][e.destino]=e.destino;
        }
        for(int k=0; k<n; k++)for(int i=0; i<n; i++)for(int j=0; j<n; j++){
            if(d[i][k]==INF||d[k][j]==INF)continue;
            long candidata=Math.max(-INF,Math.min(INF,d[i][k]+d[k][j]));
            if(candidata<d[i][j]){
                d[i][j]=candidata;
                s[i][j]=s[i][k];
            }
        }
        boolean[][] afectada=new boolean[n][n];
        for(int k=0; k<n; k++)if(d[k][k]<0)for(int i=0; i<n; i++)for(int j=0; j<n; j++)if(d[i][k]!=INF&&d[k][j]!=INF)afectada[i][j]=true;
        return new Floyd(d,s,afectada);
    }
}
