import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int[] arr = new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[n+1];
        for(int i=1;i<=n;i++){

            int max = 0;
            for(int j=i-1;j>=1;j--){

                if(arr[i] > arr[j])
                    max = Math.max(left[j] + 1 , max);
            }

            left[i] = max;
        }

        int[] right = new int[n+1];
        for(int i=n;i>=1;i--){

            int max = 0;
            for(int j=i+1;j<=n;j++){
                if(arr[i] > arr[j])
                    max = Math.max(right[j] + 1 , max);
            }

            right[i] = max;
        }

        int max = 0;
        for(int i=1;i<=n;i++){
            max = Math.max(max , left[i] + right[i]);
        }

        System.out.println(max+1);
        br.close();
    }
}
