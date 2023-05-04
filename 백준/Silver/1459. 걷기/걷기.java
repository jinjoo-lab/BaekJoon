import java.io.*;
import java.util.*;

public class Main {
    static long x = 0;
    static long y = 0;
    static long w = 0;
    static long s = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());
        w = Long.parseLong(st.nextToken());
        s = Long.parseLong(st.nextToken());

        long first = (x+y) * w;

        long second= 0;

        long min = Math.min(x,y) * s;
        long rest = (Math.max(x,y) - Math.min(x,y)) * w;

        second = min + rest;

        long third = 0;

        if((x+y)%2==0)
        {
            third = Math.max(x,y) * s;
        }

        else{
            third = (Math.max(x,y) -1) * s + w;
        }

        long result = Math.min( Math.min(first,second),third);
        System.out.println(result);
        br.close();
    }
}