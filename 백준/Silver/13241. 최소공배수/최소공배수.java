import java.io.*;
import java.util.*;

public class Main {
    static long a = 0;
    static long b = 0;
    static long c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        // LCM
        long lcm = GCD(a,b);
        long gcm = (a * b) / lcm;


        System.out.println(gcm);
        br.close();
    }

    static long GCD(long a,long b){
        if(b == 0)
            return a;

        return GCD(b,a % b);
    }
}
