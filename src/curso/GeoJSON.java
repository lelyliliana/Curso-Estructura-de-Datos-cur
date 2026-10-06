package curso;
import java.util.*;
/** Exportación de Point y LineString en una FeatureCollection (RFC 7946). */
public final class GeoJSON {
    private GeoJSON(){
    }
    public record Lugar(String id,double longitud,double latitud){
        public Lugar{
            validarTexto(id);
            Modelos.finito(longitud);
            Modelos.finito(latitud);
            if(longitud< -180||longitud>180||latitud< -90||latitud>90)throw new IllegalArgumentException("Coordenadas geográficas inválidas");
        }
    }
    public record Conexion(String origen,String destino,long costo){
    }
    private static void validarTexto(String s){
        Objects.requireNonNull(s);
        if(s.isBlank()||s.length()>80||s.codePoints().anyMatch(c->c>=0xd800&&c<=0xdfff))throw new IllegalArgumentException("Identificador inválido");
    }
    public static String texto(String s){
        Objects.requireNonNull(s);
        StringBuilder r=new StringBuilder("\"");
        for(int i=0; i<s.length(); i++){
            char c=s.charAt(i);
            switch(c){
                case '"'->r.append("\\\"");
                case '\\'->r.append("\\\\");
                case '\n'->r.append("\\n");
                case '\r'->r.append("\\r");
                case '\t'->r.append("\\t");
                default->{
                    if(c<32)r.append(String.format(Locale.ROOT,"\\u%04x",(int)c));
                    else r.append(c);
                }
            }
        }return r.append('"').toString();
    }
    private static String coordenadas(Lugar p){
        return "["+p.longitud+","+p.latitud+"]";
    }
    public static String exportar(List<Lugar> lugares,List<Conexion> conexiones){
        Map<String,Lugar> indice=new LinkedHashMap<>();
        for(Lugar p:lugares)if(indice.putIfAbsent(p.id,p)!=null)throw new IllegalArgumentException("Lugar duplicado");
        List<String> features=new ArrayList<>();
        for(Lugar p:lugares)features.add("{\"type\":\"Feature\",\"properties\":{\"id\":"+texto(p.id)+"},\"geometry\":{\"type\":\"Point\",\"coordinates\":"+coordenadas(p)+"}}");
        for(Conexion e:conexiones){
            Lugar a=indice.get(e.origen),b=indice.get(e.destino);
            if(a==null||b==null)throw new IllegalArgumentException("Extremo de conexión desconocido");
            features.add("{\"type\":\"Feature\",\"properties\":{\"origen\":"+texto(e.origen)+",\"destino\":"+texto(e.destino)+",\"costo\":"+e.costo+"},\"geometry\":{\"type\":\"LineString\",\"coordinates\":["+coordenadas(a)+","+coordenadas(b)+"]}}");
        }
        return "{\"type\":\"FeatureCollection\",\"features\":["+String.join(",",features)+"]}";
    }
}
