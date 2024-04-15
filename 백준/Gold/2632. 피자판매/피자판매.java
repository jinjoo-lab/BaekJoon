
import java.util.*;
import java.io.*;

public class Main {

    static int des,n,m;
    static int[] A;
    static int[] B;

    static int[] aCount = new int[1_000_001];
    static int[] bCount = new int[1_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        des = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n+1];
        B = new int[m+1];

        for(int i = 1 ; i <= n ; i++){

            int cur = Integer.parseInt(br.readLine());

            A[i] = A[i-1] + cur;
        }

        for(int j = 1 ; j <= m ; j++){

            int cur = Integer.parseInt(br.readLine());

            B[j] = B[j-1] + cur;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = i ; j <= n ; j++){
                int tmpSum = A[j] - A[i-1];
                aCount[tmpSum]++;
            }
        }

        for(int i = 1 ; i <= m ; i++){
            for(int j = i; j <= m ; j++){
                int tmpSum = B[j] - B[i-1];
                bCount[tmpSum]++;
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = n - 1 ; j >= i + 1; j--){
                int tmpSum = A[i] + A[n] - A[j];
                aCount[tmpSum]++;
            }
        }

        for(int i = 1 ; i <= m ; i++){
            for(int j = m - 1 ; j >= i + 1 ; j--){
                int tmpSum = B[i] + B[m] - B[j];
                bCount[tmpSum]++;
            }
        }

        long result = 0;

        if(des <= 1000_000){
            result += aCount[des] + bCount[des];
        }

        for(int i = 1 ; i < des ; i++){

            if(i > 1000_000 || (des - i) > 1000_000)
                continue;

            result += (aCount[i] * bCount[des - i]);

        }

        System.out.println(result);
        br.close();
    }
}