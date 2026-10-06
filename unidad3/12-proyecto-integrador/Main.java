import curso.*;
import java.util.*;
import java.nio.file.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Red r=Red.ejemplo();
        if(args.length==3 && args[0].equals("--menu"))r=Red.cargar(Path.of(args[1]),Path.of(args[2]));
        else if(args.length>0 && !(args.length==1 && args[0].equals("--menu")))throw new IllegalArgumentException("Uso: --menu [lugares conexiones]");
        if(args.length==0){
            System.out.println(r.ruta("a","d"));
            System.out.println("bosque="+r.bosque().costo()+", componentes="+r.bosque().componentes());
            Bot b=r.bot();
            for(String m:new String[]{
                "ruta","a","c"
            })System.out.println(b.responder(m));
            return;
        }
        Scanner entrada=new Scanner(System.in, java.nio.charset.StandardCharsets.UTF_8);
        while(true){
            System.out.println("1 Ruta | 2 Bosque | 3 Exportar | 4 Bot | 0 Salir");
            if(!entrada.hasNextLine())return;
            String opcion=entrada.nextLine().trim();
            try {
                switch(opcion){
                    case "0" -> {
                        System.out.println("Fin");
                        return;
                    }
                    case "1" -> {
                        System.out.println("Origen:");
                        if(!entrada.hasNextLine())return;
                        String a=entrada.nextLine().trim();
                        System.out.println("Destino:");
                        if(!entrada.hasNextLine())return;
                        String b=entrada.nextLine().trim();
                        System.out.println(r.ruta(a,b));
                    }
                    case "2" -> {
                        Grafo.Bosque b=r.bosque();
                        System.out.println("costo="+b.costo()+", componentes="+b.componentes());
                        System.out.println(b.aristas());
                    }
                    case "3" -> {
                        System.out.println("Archivo de salida (puede reemplazarlo):");
                        if(!entrada.hasNextLine())return;
                        r.exportar(Path.of(entrada.nextLine()));
                        System.out.println("Mapa exportado");
                    }
                    case "4" -> {
                        Bot b=r.bot();
                        System.out.println("Comandos: ruta, cancelar. Escribe salir para volver al menú.");
                        while(entrada.hasNextLine()){
                            String m=entrada.nextLine();
                            if(m.trim().equalsIgnoreCase("salir"))break;
                            System.out.println(b.responder(m));
                        }
                    }
                    default -> System.out.println("Opción inválida");
                }
            }catch(IllegalArgumentException | java.io.IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
