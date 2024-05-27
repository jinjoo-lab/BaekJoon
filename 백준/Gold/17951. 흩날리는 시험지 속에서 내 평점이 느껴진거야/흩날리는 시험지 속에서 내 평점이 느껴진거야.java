import java.util.*;
import java.io.*;

public class Main {

    static int result = -1;
    static int n,k;

    static int[] board;

    static int sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ") ;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        st = new StringTokenizer(br.readLine()," ") ;
        for(int i = 1 ; i <= n ; i++) {
            board[i] = Integer.parseInt(st.nextToken());

            sum += board[i];
        }

        int l = 0;
        int r = sum;
        int mid = 0;

        while(l <= r) {
            mid = (l+r) / 2;

            if(go(mid)) {
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        result = r;

        System.out.println(result);
        br.close();
    }

    static boolean go(int target) {
        int tmpSum = 0;
        int count = 0;

        for(int i = 1 ; i <= n ; i++) {
            tmpSum += board[i];

            if(tmpSum  >= target) {
                count+=1;
                tmpSum = 0;
            }
        }

        if(count >= k){
            return true;
        }
        return false;
    }

}
