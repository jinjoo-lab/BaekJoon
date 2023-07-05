import java.io.*;
import java.util.*;

public class Main {
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(",");

        for(String cur : st)
        {
            result += Integer.parseInt(cur);
        }
        System.out.println(result);
        
        br.close();
    }
}