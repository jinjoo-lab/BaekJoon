import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 1;
        long count = 0;

        for(int i=0;i<n;i++){
            if(start <= arr[i]){
                count += (arr[i] - start);
                start = start + 1;
            }
        }

        System.out.println(count);
        br.close();
    }
}
