import java.io.*;
import java.util.*;

public class Main {

    static long l = 0;
    static int n = 0;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine()) != null) {
            l = Long.parseLong(input) * 10000000;
            n = Integer.parseInt(br.readLine());

            long[] arr = new long[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(br.readLine());
            }
            Arrays.sort(arr);
            find(0, arr.length - 1, arr);

        }
        System.out.println(sb);
        br.close();
    }

    static void find(int left,int right,long[] arr){
        while(left < right){

            long sum = arr[left] + arr[right];

            if(sum == l){
                sb.append("yes "+arr[left]+" "+arr[right]+"\n");
                return;
            }

            else if(sum > l){
                right = right - 1;
            }

            else{
                left = left + 1;
            }
        }

        sb.append("danger\n");
    }
}
