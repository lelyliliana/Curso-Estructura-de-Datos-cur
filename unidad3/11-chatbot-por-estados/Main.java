import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Bot b=Red.ejemplo().bot();
        for(String m:new String[]{
            "RUTA","a","d","cancelar"
        })System.out.println(b.responder(m));
        System.out.println(b.estado());
    }
}
