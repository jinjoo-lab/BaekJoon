import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        arr = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(st.nextToken());

            arr[tmp] = arr[tmp-1] + 1;
        }

        Arrays.sort(arr);

        System.out.println(n - arr[n]);

        br.close();
    }



}
