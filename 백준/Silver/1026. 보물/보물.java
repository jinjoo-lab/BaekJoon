import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] a = new int[n];
        for(int i=0;i<n;i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        Integer[] b = new Integer[n];
        for(int i=0;i<n;i++)
        {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b , (x,y) -> {
            return (int)y - (int)x;
        });

        int result = 0;
        for(int i=0;i<n;i++)
        {
            result += (a[i] * b[i]);
        }

        System.out.println(result);
        br.close();
    }
}
