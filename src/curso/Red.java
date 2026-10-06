package curso;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
/** Red vial ficticia de costos no negativos; los costos no se deducen del mapa. */
public final class Red {
    private final List<GeoJSON.Lugar> lugares;
    private final List<GeoJSON.Conexion> conexiones;
    private final Map<String,Integer> indice=new TreeMap<>();
    private final Grafo grafo;
    public Red(List<GeoJSON.Lugar> lugares,List<GeoJSON.Conexion> conexiones){
        if(lugares.size()>500)throw new IllegalArgumentException("Hasta 500 lugares");
        this.lugares=List.copyOf(lugares);
        this.conexiones=List.copyOf(conexiones);
        grafo=new Grafo(lugares.size(),false);
        for(int i=0; i<lugares.size(); i++){
            String id=lugares.get(i).id();
            if(!id.matches("[a-z0-9_-]{1,20}")||indice.putIfAbsent(id,i)!=null)throw new IllegalArgumentException("Código inválido o duplicado");
        }
        Set<String> vistas=new HashSet<>();
        for(GeoJSON.Conexion c:conexiones){
            Integer a=indice.get(c.origen()),b=indice.get(c.destino());
            if(a==null||b==null||a.equals(b)||c.costo()<0)throw new IllegalArgumentException("Conexión inválida");
            String k=Math.min(a,b)+":"+Math.max(a,b);
            if(!vistas.add(k))throw new IllegalArgumentException("Conexión duplicada");
            grafo.agregar(a,b,c.costo());
        }
    }
    public Set<String> codigos(){
        return Set.copyOf(indice.keySet());
    }
    public String ruta(String a,String b){
        Integer i=indice.get(a),j=indice.get(b);
        if(i==null||j==null)throw new IllegalArgumentException("Lugar desconocido");
        Grafo.Rutas r=grafo.dijkstra(i);
        if(r.distancia(j)==Grafo.INF)return "Sin ruta";
        List<String> nombres=r.camino(j).stream().map(v->lugares.get(v).id()).toList();
        return nombres+"; costo="+r.distancia(j);
    }
    public Grafo.Bosque bosque(){
        return grafo.kruskal();
    }
    public String geojson(){
        return GeoJSON.exportar(lugares,conexiones);
    }
    public void exportar(Path archivo)throws IOException{
        Files.writeString(archivo,geojson()+"\n",StandardCharsets.UTF_8);
    }
    public Bot bot(){
        return new Bot(codigos(),this::ruta);
    }
    public static Red cargar(Path archivoLugares,Path archivoConexiones)throws IOException{
        List<GeoJSON.Lugar> l=new ArrayList<>();
        List<GeoJSON.Conexion> c=new ArrayList<>();
        try(BufferedReader r=Files.newBufferedReader(archivoLugares,StandardCharsets.UTF_8)){
            String linea;
            while((linea=r.readLine())!=null){
                if(l.size()>=500)throw new IllegalArgumentException("Demasiados lugares");
                String[] p=linea.split(";",-1);
                if(p.length!=3)throw new IllegalArgumentException("Formato de lugar");
                l.add(new GeoJSON.Lugar(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2])));
            }
        }
        try(BufferedReader r=Files.newBufferedReader(archivoConexiones,StandardCharsets.UTF_8)){
            String linea;
            while((linea=r.readLine())!=null){
                if(c.size()>=124750)throw new IllegalArgumentException("Demasiadas conexiones");
                String[] p=linea.split(";",-1);
                if(p.length!=3)throw new IllegalArgumentException("Formato de conexión");
                c.add(new GeoJSON.Conexion(p[0],p[1],Long.parseLong(p[2])));
            }
        }
        return new Red(l,c);
    }
    public static Red ejemplo(){
        return new Red(List.of(new GeoJSON.Lugar("a",-75.6,6.2),new GeoJSON.Lugar("b",-75.59,6.21),new GeoJSON.Lugar("c",-75.58,6.22),new GeoJSON.Lugar("d",-75.57,6.23)),List.of(new GeoJSON.Conexion("a","b",3),new GeoJSON.Conexion("b","c",2),new GeoJSON.Conexion("a","c",10),new GeoJSON.Conexion("c","d",4)));
    }
}
