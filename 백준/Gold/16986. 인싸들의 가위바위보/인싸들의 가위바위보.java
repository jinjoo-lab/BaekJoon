import java.util.*;
import java.io.*;

public class Main {

    static boolean findR = false;
    static int n,m;
    static int[][] ww;

    static int[] s = new int[21];
    static int[] t = new int[21];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ww = new int[n+1][n+1];

        for(int i = 1 ; i<= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++) {
                ww[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= 20 ; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= 20 ; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        isIt = new boolean[n+1];


        go(0,0,0,1,1,1,2);

        if(findR) {
            System.out.println("1");
        }else{
            System.out.println("0");
        }

        br.close();
    }

    static boolean[] isIt;

    static void go(int fWin, int sWin, int tWin,int sT,int tT,int p1,int p2) {

        if(findR)
            return;

        if(fWin == m || sWin == m || tWin == m) {

            if(fWin == m)
                findR = true;

            return;
        }

        if(p1 == 1) {
            for(int i = 1 ; i <= n ; i++) {
                if(!isIt[i]) {
                    isIt[i] = true;
                    if(p2 == 2) {
                        int c2 = s[sT];
                        if(ww[i][c2] == 2) {
                            go(fWin + 1, sWin, tWin,sT + 1, tT, 1, 3);
                        }else {
                            go(fWin,sWin + 1, tWin,sT + 1, tT, 2, 3);
                        }
                    }else{
                        int c3 = t[tT];
                        if(ww[i][c3] == 2) {
                            go(fWin + 1, sWin, tWin,sT, tT + 1, 1, 2);
                        }else {
                            go(fWin,sWin, tWin + 1,sT, tT + 1, 2, 3);
                        }
                    }
                    isIt[i] = false;
                }
            }
        }else if(p1 == 2) {
            if(ww[s[sT]][t[tT]] == 2) {
                go(fWin, sWin + 1, tWin,sT + 1, tT + 1, 1, 2);
            }else {
                go(fWin, sWin, tWin + 1,sT + 1, tT + 1, 1, 3);
            }
        }
    }
}
