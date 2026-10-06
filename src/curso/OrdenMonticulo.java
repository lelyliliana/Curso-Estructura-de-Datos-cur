package curso;
/** Heapsort ascendente: montículo máximo sobre una región activa del arreglo. */
public final class OrdenMonticulo {
    private OrdenMonticulo(){
    }
    private static void intercambiar(int[] a,int i,int j){
        int x=a[i];
        a[i]=a[j];
        a[j]=x;
    }
    private static void descender(int[] a,int i,int limite){
        while(i<limite/2){
            int hijo=2*i+1;
            if(hijo+1<limite&&a[hijo+1]>a[hijo])hijo++;
            if(a[i]>=a[hijo])return;
            intercambiar(a,i,hijo);
            i=hijo;
        }
    }
    public static void ordenar(int[] a){
        for(int i=a.length/2-1; i>=0; i--)descender(a,i,a.length);
        for(int fin=a.length-1; fin>0; fin--){
            intercambiar(a,0,fin);
            descender(a,0,fin);
        }
    }
}
