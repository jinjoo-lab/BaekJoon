import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int m1 = m%100%10;
        int m2 = (m/10)%10;
        int m3 = m/100;
        System.out.println(n*m1);
        System.out.println(n*m2);
        System.out.println(n*m3);
        System.out.println(n*m);

        br.close();
    }
}
