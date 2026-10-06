import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String,Flujo.Estadistica> r=Flujo.resumir(new StringReader("norte;10\nnorte;20\nsur;5\n"));
        for(var e:r.entrySet())System.out.println(e.getKey()+": "+e.getValue()+", varianza="+e.getValue().varianzaPoblacional());
    }
}
