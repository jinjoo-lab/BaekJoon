import java.util.*;
import java.io.*;
public class Main {

    static int n,m;
    static char[][] board;

    static boolean[] pOver = new boolean[100_001];
    static ArrayList<int[]> chair = new ArrayList<>();
    static ArrayList<int[]> people = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];


        int pIdx = 1;
        int cIdx = 1;

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = arr[j-1];

                if(board[i][j] == 'L'){
                    chair.add(new int[]{i,j,cIdx});
                    cIdx++;
                }else if(board[i][j] == 'X'){
                    people.add(new int[]{i,j,pIdx});
                    pIdx++;
                }
            }
        }

        ArrayList<int[]> dis = new ArrayList<>();

        for(int[] curP : people){
            for(int[] curC : chair){
                int tmpDis = dis(curP[0],curP[1],curC[0],curC[1]);
                dis.add(new int[]{tmpDis,curP[2],curC[2]});
            }
        }

        Collections.sort(dis,(x,y) -> x[0] - y[0]);

        int[][] dp = new int[cIdx + 1][2];
        for(int i = 1 ; i <= cIdx ; i++){
            dp[i][0] = Integer.MAX_VALUE;
        }

        for(int[] curInfo : dis){
            int tmpDis = curInfo[0];
            int p = curInfo[1];
            int c = curInfo[2];

            if(dp[c][0] > tmpDis){

                if(pOver[p])
                    continue;

                dp[c][0] = tmpDis;
                dp[c][1] = 1;
                pOver[p] = true;
            }else if(dp[c][0] == tmpDis){

                if(pOver[p])
                    continue;

                dp[c][1] += 1;
                pOver[p] = true;
            }
        }

        int count = 0;
        for(int i = 1 ; i <= cIdx ; i++){
            if(dp[i][1] >= 2){
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }

    static int dis(int sx,int sy,int lx,int ly){
        int tmpX = (sx - lx) * (sx - lx);
        int tmpY = (sy - ly) * (sy - ly);

        return tmpX + tmpY;
    }
}
