package curso;
import java.util.*;
/** Montículo mínimo sobre un arreglo dinámico. */
public final class Monticulo {
    private final ArrayList<Integer> datos=new ArrayList<>();
    public int tamano(){
        return datos.size();
    }
    public void agregar(int valor){
        datos.add(valor);
        int i=datos.size()-1;
        while(i>0){
            int p=(i-1)/2;
            if(datos.get(p)<=datos.get(i))break;
            intercambiar(i,p);
            i=p;
        }
    }
    public int minimo(){
        if(datos.isEmpty())throw new NoSuchElementException("Montículo vacío");
        return datos.get(0);
    }
    public int extraer(){
        int min=minimo(),ultimo=datos.remove(datos.size()-1);
        if(!datos.isEmpty()){
            datos.set(0,ultimo);
            int i=0;
            while(2*i+1<datos.size()){
                int j=2*i+1;
                if(j+1<datos.size()&&datos.get(j+1)<datos.get(j))j++;
                if(datos.get(i)<=datos.get(j))break;
                intercambiar(i,j);
                i=j;
            }
        }
        return min;
    }
    private void intercambiar(int i,int j){
        int x=datos.get(i);
        datos.set(i,datos.get(j));
        datos.set(j,x);
    }
    public List<Integer> arreglo(){
        return List.copyOf(datos);
    }
    public void verificar(){
        for(int i=1; i<datos.size(); i++)if(datos.get((i-1)/2)>datos.get(i))throw new IllegalStateException("Orden de montículo");
    }
}
