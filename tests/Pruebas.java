import curso.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public final class Pruebas {
    static int grupos;
    static void exigir(boolean ok,String mensaje){
        if(!ok)throw new AssertionError(mensaje);
    }
    interface Accion {
        void ejecutar()throws Exception;
    }
    static void rechaza(Class<? extends Throwable> tipo,Accion a)throws Exception{
        try{
            a.ejecutar();
        }catch(Throwable e){
            if(tipo.isInstance(e))return;
            throw e;
        }throw new AssertionError("No rechazó entrada: "+tipo);
    }
    static void grupo(String nombre){
        grupos++;
        System.out.println("OK "+nombre);
    }
    static void avl(){
        AVL a=new AVL();
        TreeSet<Integer> ref=new TreeSet<>();
        Random r=new Random(1201);
        for(int k:new int[]{
            Integer.MIN_VALUE,Integer.MAX_VALUE,0
        }){
            a.insertar(k);
            ref.add(k);
        }
        for(int i=0; i<12000; i++){
            int k=r.nextInt(1000)-500;
            if(r.nextBoolean()){
                a.insertar(k);
                ref.add(k);
            }else {
                a.eliminar(k);
                ref.remove(k);
            }a.verificar();
            exigir(a.orden().equals(new ArrayList<>(ref)),"Contenido AVL");
            exigir(a.contiene(k)==ref.contains(k),"Consulta AVL");
        }
        for(int k:new ArrayList<>(ref)){
            a.eliminar(k);
            a.verificar();
        }exigir(a.altura()==0&&a.orden().isEmpty(),"AVL vacío");
        for(int i=0; i<4000; i++)a.insertar(i);
        a.verificar();
        exigir(a.altura()<20,"Altura AVL creciente");
        grupo("AVL: 12000 operaciones y extremos");
    }
    static void monticulo()throws Exception{
        Monticulo m=new Monticulo();
        PriorityQueue<Integer> ref=new PriorityQueue<>();
        Random r=new Random(1202);
        for(int i=0; i<8000; i++){
            if(ref.isEmpty()||r.nextBoolean()){
                int k=r.nextInt(100);
                m.agregar(k);
                ref.add(k);
            }else exigir(m.extraer()==ref.remove(),"Extraer montículo");
            m.verificar();
            exigir(m.tamano()==ref.size(),"Tamaño montículo");
            if(!ref.isEmpty())exigir(m.minimo()==ref.peek(),"Mínimo");
        }
        while(!ref.isEmpty())exigir(m.extraer()==ref.remove(),"Vaciar montículo");
        rechaza(NoSuchElementException.class,m::extraer);
        grupo("Montículo: comparación con PriorityQueue");
    }
    static void arbolB()throws Exception{
        for(int t=2; t<=5; t++){
            ArbolB b=new ArbolB(t);
            TreeSet<Integer> ref=new TreeSet<>();
            Random r=new Random(1210+t);
            for(int k:new int[]{
                Integer.MIN_VALUE,Integer.MAX_VALUE
            }){
                b.insertar(k);
                ref.add(k);
            }
            for(int i=0; i<6000; i++){
                int k=r.nextInt(600)-300;
                if(r.nextBoolean()){
                    b.insertar(k);
                    ref.add(k);
                }else {
                    b.eliminar(k);
                    ref.remove(k);
                }b.verificar();
                exigir(b.orden().equals(new ArrayList<>(ref)),"Contenido B t="+t);
                exigir(b.contiene(k)==ref.contains(k),"B consulta");
            }
            List<Integer> claves=new ArrayList<>(ref);
            Collections.shuffle(claves,r);
            for(int k:claves){
                b.eliminar(k);
                b.verificar();
            }exigir(b.orden().isEmpty(),"B vacío");
        }
        rechaza(IllegalArgumentException.class,()->new ArbolB(1));
        grupo("Árbol B: cuatro grados, 24000 operaciones");
    }
    static void bMas()throws Exception{
        for(int m=3; m<=8; m++){
            ArbolBMas b=new ArbolBMas(m);
            TreeMap<Integer,String> ref=new TreeMap<>();
            Random r=new Random(1220+m);
            for(int k:new int[]{
                Integer.MIN_VALUE,Integer.MAX_VALUE
            }){
                b.poner(k,"extremo");
                ref.put(k,"extremo");
            }
            for(int i=0; i<5000; i++){
                int k=r.nextInt(600)-300;
                if(r.nextBoolean()){
                    String v="v"+i;
                    b.poner(k,v);
                    ref.put(k,v);
                }else {
                    b.eliminar(k);
                    ref.remove(k);
                }
                b.verificar();
                exigir(Objects.equals(b.buscar(k),ref.get(k)),"Consulta B+");
                exigir(b.rango(Integer.MIN_VALUE,Integer.MAX_VALUE).equals(ref),"Mapa B+ M="+m);
                int desde=r.nextInt(600)-300,hasta=desde+r.nextInt(100);
                exigir(b.rango(desde,hasta).equals(ref.subMap(desde,true,hasta,true)),"Rango B+");
            }
            List<Integer> claves=new ArrayList<>(ref.keySet());
            Collections.shuffle(claves,r);
            for(int k:claves){
                b.eliminar(k);
                b.verificar();
            }exigir(b.rango(0,10).isEmpty()&&b.buscar(0)==null,"B+ vacío");
            exigir(b.rango(10,0).isEmpty(),"Rango invertido");
        }
        rechaza(NullPointerException.class,()->new ArbolBMas(3).poner(1,null));
        grupo("B+: seis capacidades, 30000 operaciones y rangos");
    }
    static void hash()throws Exception{
        TablaHash h=new TablaHash();
        Map<Integer,String> ref=new HashMap<>();
        Random r=new Random(1230);
        for(int i=0; i<8000; i++){
            int k=(r.nextInt(400)-200)*64;
            if(r.nextBoolean()){
                String v="v"+i;
                h.poner(k,v);
                ref.put(k,v);
            }else {
                h.eliminar(k);
                ref.remove(k);
            }h.verificar();
            exigir(h.tamano()==ref.size(),"Tamaño hash");
            for(var e:ref.entrySet())exigir(e.getValue().equals(h.buscar(e.getKey())),"Hash colisiones");
        }
        h.poner(Integer.MIN_VALUE,"min");
        h.poner(Integer.MAX_VALUE,"max");
        h.verificar();
        exigir(h.buscar(Integer.MIN_VALUE).equals("min"),"Índice negativo");
        rechaza(NullPointerException.class,()->h.poner(1,null));
        grupo("Hash: tumbas, carga, 8000 operaciones con colisiones");
    }
    static void heapsort(){
        Random r=new Random(1232);
        for(int caso=0; caso<1000; caso++){
            int[] datos=new int[r.nextInt(120)];
            for(int i=0; i<datos.length; i++)datos[i]=r.nextInt();
            int[] ref=datos.clone();
            Arrays.sort(ref);
            OrdenMonticulo.ordenar(datos);
            exigir(Arrays.equals(datos,ref),"Heapsort contra Arrays.sort");
        }
        grupo("Heapsort: 1000 arreglos y duplicados");
    }
    static void encadenada(){
        TablaEncadenada h=new TablaEncadenada(3);
        Map<Integer,String> ref=new HashMap<>();
        Random r=new Random(1233);
        for(int i=0; i<3000; i++){
            int k=r.nextInt(200)-100;
            if(r.nextBoolean()){
                String v="v"+i;
                h.poner(k,v);
                ref.put(k,v);
            }else {
                h.eliminar(k);
                ref.remove(k);
            }
            exigir(h.tamano()==ref.size(),"Tamaño encadenada");
            for(int j=-100; j<100; j++)exigir(Objects.equals(h.buscar(j),ref.get(j)),"Cubetas y actualización");
        }
        grupo("Hash encadenado: 3000 operaciones y carga mayor que uno");
    }
    static void disjuntos(){
        Disjuntos d=new Disjuntos(20);
        int[] etiqueta=new int[20];
        for(int i=0; i<20; i++)etiqueta[i]=i;
        Random r=new Random(1231);
        for(int z=0; z<1000; z++){
            int a=r.nextInt(20),b=r.nextInt(20),vieja=etiqueta[b],nueva=etiqueta[a];
            exigir(d.unir(a,b)==(vieja!=nueva),"Unión DSU");
            for(int i=0; i<20; i++)if(etiqueta[i]==vieja)etiqueta[i]=nueva;
            for(int i=0; i<20; i++)for(int j=0; j<20; j++)exigir((d.representante(i)==d.representante(j))==(etiqueta[i]==etiqueta[j]),"Partición DSU");
            exigir(d.componentes()==Arrays.stream(etiqueta).distinct().count(),"Componentes DSU");
        }grupo("DSU: oráculo de etiquetas y 1000 uniones");
    }
    static long[] bellman(Grafo g,int origen){
        long[] d=new long[g.vertices()];
        Arrays.fill(d,Grafo.INF);
        d[origen]=0;
        for(int z=1; z<g.vertices(); z++){
            boolean cambio=false;
            for(int a=0; a<g.vertices(); a++)for(Grafo.Arista e:g.vecinos(a))if(d[a]!=Grafo.INF&&d[a]+e.peso()<d[e.destino()]){
                d[e.destino()]=d[a]+e.peso();
                cambio=true;
            }if(!cambio)break;
        }return d;
    }
    static long costo(Grafo g,List<Integer> ruta){
        long suma=0;
        for(int i=1; i<ruta.size(); i++){
            int a=ruta.get(i-1),b=ruta.get(i);
            long min=Grafo.INF;
            for(Grafo.Arista e:g.vecinos(a))if(e.destino()==b)min=Math.min(min,e.peso());
            exigir(min!=Grafo.INF,"Paso inexistente");
            suma+=min;
        }return suma;
    }
    static void rutas(){
        Random r=new Random(1240);
        for(int z=0; z<250; z++){
            int n=2+r.nextInt(7);
            Grafo g=new Grafo(n,r.nextBoolean());
            int aristas=r.nextInt(35);
            for(int k=0; k<aristas; k++)g.agregar(r.nextInt(n),r.nextInt(n),r.nextInt(20));
            Grafo.Floyd f=g.floyd();
            for(int a=0; a<n; a++){
                long[] ref=bellman(g,a);
                Grafo.Rutas d=g.dijkstra(a);
                for(int b=0; b<n; b++){
                    exigir(d.distancia(b)==ref[b]&&f.distancia(a,b)==ref[b],"Distancias independientes");
                    for(List<Integer> p:List.of(d.camino(b),f.camino(a,b))){
                        if(ref[b]==Grafo.INF)exigir(p.isEmpty(),"Inaccesible");
                        else {
                            exigir(p.get(0)==a&&p.get(p.size()-1)==b&&p.size()<=n,"Extremos y longitud");
                            exigir(costo(g,p)==ref[b],"Costo reconstruido");
                        }
                    }
                }
            }
        }grupo("Dijkstra/Floyd: 250 grafos contra Bellman–Ford y rutas");
    }
    static void negativos()throws Exception{
        Random r=new Random(1241);
        for(int z=0; z<200; z++){
            Grafo g=new Grafo(7,true);
            for(int a=0; a<7; a++)for(int b=a+1; b<7; b++)if(r.nextBoolean())g.agregar(a,b,r.nextInt(21)-10);
            Grafo.Floyd f=g.floyd();
            for(int a=0; a<7; a++){
                long[] ref=bellman(g,a);
                for(int b=0; b<7; b++){
                    exigir(!f.cicloAfecta(a,b)&&f.distancia(a,b)==ref[b],"Floyd con negativos DAG");
                    List<Integer> p=f.camino(a,b);
                    if(ref[b]!=Grafo.INF)exigir(costo(g,p)==ref[b],"Ruta negativa DAG");
                }
            }
        }
        Grafo g=new Grafo(5,true);
        g.agregar(0,1,2);
        g.agregar(1,2,-3);
        g.agregar(2,1,1);
        g.agregar(2,3,2);
        Grafo.Floyd f=g.floyd();
        exigir(f.cicloAfecta(0,3)&&!f.cicloAfecta(3,0)&&!f.cicloAfecta(4,4),"Afectación localizada");
        rechaza(IllegalStateException.class,()->f.camino(0,3));
        rechaza(IllegalArgumentException.class,()->g.dijkstra(4));
        exigir(f.camino(4,4).equals(List.of(4)),"Trivial ajeno a ciclo");
        Grafo denso=new Grafo(30,true);
        for(int a=0; a<30; a++)for(int b=0; b<30; b++)denso.agregar(a,b,-1000000000L);
        Grafo.Floyd fd=denso.floyd();
        exigir(fd.cicloAfecta(0,29),"Saturación negativa");
        rechaza(IllegalStateException.class,()->fd.distancia(0,29));
        grupo("Floyd: 200 DAG negativos, ciclos localizados y saturación");
    }
    static long minimoBruto(Grafo g,int componentes){
        List<Grafo.Arista> aristas=g.aristas();
        long minimo=Long.MAX_VALUE;
        for(int mascara=0; mascara<(1<<aristas.size()); mascara++){
            if(Integer.bitCount(mascara)!=g.vertices()-componentes)continue;
            int[] c=new int[g.vertices()];
            for(int i=0; i<c.length; i++)c[i]=i;
            long suma=0;
            boolean ciclo=false;
            for(int j=0; j<aristas.size(); j++)if((mascara&(1<<j))!=0){
                Grafo.Arista e=aristas.get(j);
                int a=c[e.origen()],b=c[e.destino()];
                if(a==b){
                    ciclo=true;
                    break;
                }for(int i=0; i<c.length; i++)if(c[i]==b)c[i]=a;
                suma+=e.peso();
            }
            if(!ciclo&&Arrays.stream(c).distinct().count()==componentes)minimo=Math.min(minimo,suma);
        }return minimo;
    }
    static void kruskal()throws Exception{
        int[][] parejas={
            {
                0,1
            },{
                0,2
            },{
                0,3
            },{
                1,2
            },{
                1,3
            },{
                2,3
            }
        };
        for(int mascara=0; mascara<64; mascara++){
            Grafo g=new Grafo(4,false);
            int[] c={
                0,1,2,3
            };
            for(int j=0; j<6; j++)if((mascara&(1<<j))!=0){
                int a=parejas[j][0],b=parejas[j][1];
                g.agregar(a,b,j%3-1);
                int x=c[a],y=c[b];
                for(int i=0; i<4; i++)if(c[i]==y)c[i]=x;
            }int comp=(int)Arrays.stream(c).distinct().count();
            Grafo.Bosque b=g.kruskal();
            exigir(b.componentes()==comp&&b.aristas().size()==4-comp,"Bosque estructural");
            exigir(b.costo()==minimoBruto(g,comp),"MST óptimo exhaustivo");
        }
        Grafo g=new Grafo(2,false);
        g.agregar(0,0,-10);
        g.agregar(0,1,5);
        g.agregar(0,1,2);
        exigir(g.kruskal().costo()==2,"Lazo y paralelas Kruskal");
        rechaza(IllegalArgumentException.class,()->new Grafo(2,true).kruskal());
        exigir(new Grafo(0,false).kruskal().componentes()==0,"Bosque vacío");
        grupo("Kruskal: 64 grafos, costo por enumeración independiente");
    }
    static void limites()throws Exception{
        Grafo g=new Grafo(5,true);
        for(int i=0; i<4; i++)g.agregar(i,i+1,1000000000L);
        exigir(g.dijkstra(0).distancia(4)==4000000000L,"Acumulación long");
        rechaza(IllegalArgumentException.class,()->g.agregar(0,1,1000000001L));
        rechaza(IllegalArgumentException.class,()->g.agregar(-1,1,0));
        rechaza(IllegalArgumentException.class,()->new Grafo(501,true).floyd());
        grupo("Dominio numérico y validación de grafos");
    }
    static void modelos()throws Exception{
        exigir(Modelos.producto(new double[]{
            2,3
        },new double[]{
            4,-1
        },1)==6,"Producto");
        exigir(Modelos.sigmoide(0)==0.5&&Modelos.sigmoide(-1000)==0&&Modelos.sigmoide(1000)==1,"Sigmoide estable");
        double[][] x={
            {
                0,0
            },{
                0,1
            },{
                1,0
            },{
                1,1
            }
        };
        int[] or={
            0,1,1,1
        },xor={
            0,1,1,0
        };
        for(int i=0; i<4; i++){
            double[] h=Modelos.capa(x[i],new double[][]{
                {
                    1,1
                },{
                    1,1
                }
            },new double[]{
                0,-1
            },true);
            double p=Modelos.sigmoide(Modelos.producto(h,new double[]{
                2,-4
            },-1));
            exigir((p>=0.5?1:0)==xor[i],"XOR no lineal fijo");
        }
        Modelos.Perceptron p=new Modelos.Perceptron(2);
        p.entrenar(x,or,1,30);
        for(int i=0; i<4; i++)exigir(p.predecir(x[i])==or[i],"OR aprendido");
        Modelos.Perceptron q=new Modelos.Perceptron(2);
        q.entrenar(x,xor,1,30);
        boolean fallo=false;
        for(int i=0; i<4; i++)fallo|=q.predecir(x[i])!=xor[i];
        exigir(fallo,"XOR no separable");
        rechaza(IllegalArgumentException.class,()->Modelos.producto(new double[]{
            1
        },new double[]{
            1,2
        },0));
        rechaza(IllegalArgumentException.class,()->Modelos.sigmoide(Double.NaN));
        rechaza(IllegalArgumentException.class,()->Modelos.exactitud(new int[0],new int[0]));
        grupo("Modelos: dimensiones, XOR fijo, OR aprendido y límites");
    }
    static void kd(){
        Random r=new Random(1250);
        List<KD.Punto> puntos=new ArrayList<>();
        for(int i=0; i<600; i++)puntos.add(new KD.Punto(String.format(Locale.ROOT,"p%03d",i),r.nextInt(100),r.nextInt(100)));
        KD k=new KD(puntos);
        for(int i=0; i<400; i++){
            KD.Punto q=new KD.Punto("q",r.nextInt(120)-10,r.nextInt(120)-10);
            KD.Punto ref=puntos.stream().min(Comparator.comparingDouble((KD.Punto p)->KD.distancia2(p,q)).thenComparing(KD.Punto::id)).orElseThrow();
            exigir(k.cercano(q).equals(ref),"KD contra búsqueda exhaustiva");
        }
        for(int i=0; i<100; i++){
            double x=r.nextInt(80),y=r.nextInt(80);
            List<String> ref=puntos.stream().filter(p->p.x()>=x&&p.x()<=x+20&&p.y()>=y&&p.y()<=y+20).map(KD.Punto::id).sorted().toList();
            exigir(k.rectangulo(x,y,x+20,y+20).equals(ref),"KD rango");
        }
        exigir(new KD(List.of()).cercano(new KD.Punto("q",0,0))==null,"KD vacío");
        grupo("KD: 400 vecinos y 100 rectángulos contra recorrido lineal");
    }
    static void flujo()throws Exception{
        Map<String,Flujo.Estadistica> m=Flujo.resumir(new StringReader("a;10\na;20\na;30\nb;5\n"));
        exigir(m.get("a").cantidad()==3&&m.get("a").media()==20,"Flujo media");
        exigir(Math.abs(m.get("a").varianzaPoblacional()-200.0/3)<1e-10,"Welford varianza");
        exigir(m.get("b").varianzaPoblacional()==0,"Varianza singleton");
        exigir(Flujo.resumir(new StringReader("")).isEmpty(),"Flujo vacío");
        for(String s:List.of("a;NaN","a;Infinity",";2","a;2;3","a;1000001","a;x"))rechaza(IllegalArgumentException.class,()->Flujo.resumir(new StringReader(s)));
        grupo("Flujo: media, varianza y registros inválidos");
    }
    static void bot()throws Exception{
        Bot b=new Bot(Set.of("a","b"),(a,c)->a+"->"+c);
        exigir(b.responder(" ＲＵＴＡ ").contains("origen")&&b.estado()==Bot.Estado.ORIGEN,"Normalización bot");
        exigir(b.responder("x").equals("Código desconocido")&&b.estado()==Bot.Estado.ORIGEN,"Estado tras inválido");
        b.responder("A");
        exigir(b.estado()==Bot.Estado.DESTINO,"Destino pendiente");
        exigir(b.responder("b").equals("a->b")&&b.estado()==Bot.Estado.INICIO,"Respuesta y reinicio");
        b.responder("ruta");
        b.responder("a");
        b.responder("cancelar");
        exigir(b.estado()==Bot.Estado.INICIO,"Cancelar");
        rechaza(IllegalArgumentException.class,()->b.responder("x".repeat(81)));
        grupo("Bot: estados, normalización, cancelación y límite");
    }
    static void geoYRed()throws Exception{
        rechaza(IllegalArgumentException.class,()->new GeoJSON.Lugar("a",0,95));
        rechaza(IllegalArgumentException.class,()->new GeoJSON.Lugar("a",Double.NaN,0));
        rechaza(IllegalArgumentException.class,()->GeoJSON.exportar(List.of(new GeoJSON.Lugar("a",0,0)),List.of(new GeoJSON.Conexion("a","b",1))));
        Red r=Red.ejemplo();
        exigir(r.ruta("a","d").equals("[a, b, c, d]; costo=9"),"Ruta proyecto");
        exigir(r.bosque().costo()==9&&r.bosque().componentes()==1,"Bosque proyecto");
        rechaza(UnsupportedOperationException.class,()->r.codigos().clear());
        Path carpeta=Files.createTempDirectory("red con espacios ");
        try{
            Path l=carpeta.resolve("lugares.txt"),c=carpeta.resolve("conexiones.txt"),j=carpeta.resolve("mapa.geojson");
            Files.writeString(l,"a;-75.6;6.2\nb;-75.59;6.21\ne;0;0\n",StandardCharsets.UTF_8);
            Files.writeString(c,"a;b;0\n",StandardCharsets.UTF_8);
            Red cargada=Red.cargar(l,c);
            exigir(cargada.ruta("a","b").equals("[a, b]; costo=0")&&cargada.ruta("a","e").equals("Sin ruta"),"Carga cero y aislado");
            exigir(cargada.bosque().componentes()==2,"Proyecto desconectado");
            cargada.exportar(j);
            exigir(Files.readString(j,StandardCharsets.UTF_8).startsWith("{\"type\":\"FeatureCollection\""),"Archivo UTF8");
            for(String invalida:List.of("a;b;1\nb;a;2\n","a;a;1\n","a;z;1\n","a;b;-1\n","a;b;1\ninvalida\n")){
                Files.writeString(c,invalida,StandardCharsets.UTF_8);
                rechaza(IllegalArgumentException.class,()->Red.cargar(l,c));
                exigir(cargada.ruta("a","b").endsWith("costo=0"),"La red previa no cambia");
            }
        }finally{
            try(var archivos=Files.list(carpeta)){
                for(Path f:archivos.toList())Files.delete(f);
            }Files.delete(carpeta);
        }
        grupo("GeoJSON/Red: dominio, carga completa, archivos y vistas");
    }
    public static void main(String[] args)throws Exception{
        if(args.length>0&&args[0].equals("--geo")){
            System.out.println(GeoJSON.exportar(List.of(new GeoJSON.Lugar("Salón \"A\"\n\\",-75.6,6.2),new GeoJSON.Lugar("b",-75.59,6.21)),List.of(new GeoJSON.Conexion("Salón \"A\"\n\\","b",0))));
            return;
        }
        avl();
        monticulo();
        arbolB();
        bMas();
        hash();
        heapsort();
        encadenada();
        disjuntos();
        rutas();
        negativos();
        kruskal();
        limites();
        modelos();
        kd();
        flujo();
        bot();
        geoYRed();
        System.out.println("Grupos comprobados: "+grupos);
    }
}
