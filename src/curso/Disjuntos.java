package curso;
/** Unión por tamaño y compresión de caminos. */
public final class Disjuntos {
    private final int[] padre,tamano;
    private int componentes;
    public Disjuntos(int n){
        if(n<0)throw new IllegalArgumentException("Tamaño negativo");
        padre=new int[n];
        tamano=new int[n];
        componentes=n;
        for(int i=0; i<n; i++){
            padre[i]=i;
            tamano[i]=1;
        }
    }
    public int representante(int x){
        if(x<0||x>=padre.length)throw new IllegalArgumentException("Vértice inválido");
        int r=x;
        while(r!=padre[r])r=padre[r];
        while(x!=r){
            int p=padre[x];
            padre[x]=r;
            x=p;
        }return r;
    }
    public boolean unir(int a,int b){
        int x=representante(a),y=representante(b);
        if(x==y)return false;
        if(tamano[x]<tamano[y]){
            int z=x;
            x=y;
            y=z;
        }padre[y]=x;
        tamano[x]+=tamano[y];
        componentes--;
        return true;
    }
    public int componentes(){
        return componentes;
    }
}
