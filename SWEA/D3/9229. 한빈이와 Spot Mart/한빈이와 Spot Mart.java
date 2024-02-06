import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int t = 0;
    static int n = 0;
    static int m = 0;

    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new int[n];

            st = new StringTokenizer(br.readLine()," ");
            for(int i=0 ;i < n ;i++){
                board[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(board);

            int l = 0;
            int r = n - 1;
            int result = -1;

            while(l < r){
                int tmp = board[l] + board[r];

                if(tmp == m){
                    result = m;
                    break;
                }

                if(tmp < m){
                    result = Math.max(result,tmp);
                    l++;
                }else{
                    r--;
                }
            }

            sb.append("#"+a+" "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }
}
