import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        num = new int[n*2];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0;i<n*2;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        int l = 0;
        int r = n*2 -1;

        int sum = 200001;

        while(l<r){
            sum = Math.min(sum,num[l] + num[r]);
            l = l + 1;
            r = r - 1;
        }

        System.out.println(sum);
    }
}
