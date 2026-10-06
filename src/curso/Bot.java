package curso;
import java.text.Normalizer;
import java.util.*;
import java.util.function.BiFunction;
/** Conversación determinista por estados y entradas controladas. */
public final class Bot {
    public enum Estado {
        INICIO,ORIGEN,DESTINO
    }
    private Estado estado=Estado.INICIO;
    private String origen;
    private final Set<String> lugares;
    private final BiFunction<String,String,String> ruta;
    public Bot(Set<String> lugares,BiFunction<String,String,String> ruta){
        Set<String> copia=new HashSet<>();
        for(String s:lugares){
            String n=normalizar(s);
            if(n.isBlank()||!copia.add(n))throw new IllegalArgumentException("Código vacío o ambiguo");
        }this.lugares=Set.copyOf(copia);
        this.ruta=Objects.requireNonNull(ruta);
    }
    public static String normalizar(String s){
        Objects.requireNonNull(s);
        if(s.length()>80)throw new IllegalArgumentException("Entrada demasiado larga");
        return Normalizer.normalize(s,Normalizer.Form.NFKC).trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
    }
    public Estado estado(){
        return estado;
    }
    public String responder(String mensaje){
        String m=normalizar(mensaje);
        if(m.equals("cancelar")){
            estado=Estado.INICIO;
            origen=null;
            return "Consulta cancelada";
        }
        if(estado==Estado.INICIO){
            if(m.equals("ruta")){
                estado=Estado.ORIGEN;
                return "Escribe el código de origen";
            }return "Comandos: ruta, cancelar";
        }
        if(!lugares.contains(m))return "Código desconocido";
        if(estado==Estado.ORIGEN){
            origen=m;
            estado=Estado.DESTINO;
            return "Escribe el código de destino";
        }
        String respuesta=ruta.apply(origen,m);
        estado=Estado.INICIO;
        origen=null;
        return respuesta;
    }
}
