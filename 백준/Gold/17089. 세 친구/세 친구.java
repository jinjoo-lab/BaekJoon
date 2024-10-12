import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static boolean[][] isFriend;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        isFriend = new boolean[n+1][n+1];
        count = new int[n+1];

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            isFriend[v1][v2] = true;
            isFriend[v2][v1] = true;

            count[v1]++;
            count[v2]++;
        }

        int result = -1;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = i + 1 ; j <= n ; j++) {
                if(!isFriend[i][j])
                    continue;

                for(int k = j + 1 ; k  <= n ; k++) {
                    if(isFriend[i][k] && isFriend[k][j]) {
                        if(result == -1) {
                            result = count[i] + count[j] + count[k] - 6;
                        }else {
                            result = Math.min(result,count[i] + count[j] + count[k] - 6);
                        }
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}
