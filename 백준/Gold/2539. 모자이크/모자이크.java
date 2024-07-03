import java.util.*;
import java.io.*;

public class Main {

    static int n,m,num,k;

    static int[][] board;

    static int max1,max2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim()," ");
        num = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim()," ");
        k = Integer.parseInt(st.nextToken());

        board = new int[k][2];

        for(int i = 0 ; i < k ; i++) {
            st = new StringTokenizer(br.readLine().trim()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            board[i][0] = v1;
            board[i][1] = v2;

            max1 = Math.max(max1,board[i][0]);
            max2 = Math.max(max2,board[i][1]);
        }

        Arrays.sort(board,(x,y) -> x[1] - y[1]);

        int l = max1;
        int r = Math.min(n,m);
        int mid = 0;

        int result = r;

        while(l <= r) {
            mid = (l + r) / 2;

            if(go(mid)) {
                result = Math.min(result,mid);
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }


        System.out.println(result);
        br.close();
    }

    static void print() {
        for(int i = 0 ; i < k ; i++) {
            System.out.print(board[i][0]+" "+board[i][1]+"\n");
        }
    }

    static boolean go(int target) {
        int l = 0;
        int r = 1;
        int tmpNum = 0;

        while(l <= r && r < k) {
            int tmpL = board[l][1];
            int tmpR = board[r][1];

            if(tmpR - tmpL + 1 > target) {
                tmpNum += 1;
                l = r;
            }

            r++;
        }
        tmpNum += 1;

        return tmpNum <= num;
    }
}
