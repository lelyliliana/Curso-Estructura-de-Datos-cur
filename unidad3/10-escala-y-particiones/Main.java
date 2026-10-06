import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int k:new int[]{
            -1,0,1,2,3
        })System.out.println(k+" -> "+Math.floorMod(k,3));
        long n1=2,n2=8;
        double m1=10,m2=20;
        System.out.println("media combinada="+(n1*m1+n2*m2)/(n1+n2));
    }
}
