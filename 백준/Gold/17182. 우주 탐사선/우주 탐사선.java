import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[11][11];
    static int result = 100000000;

    static boolean[] visit = new boolean[11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k = 0; k< n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j<n; j++){
                    board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        visit[m] = true;
        travel(1,0,m);
        System.out.println(result);
        br.close();
    }
    static void travel(int k,int v,int x) {
        if(k==n)
        {
            if(result>v)
                result = v;
            return;
        }

        for(int i=0;i<n;i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                travel(k+1,v+board[x][i],i);
                visit[i] = false;
            }
        }
    }
}