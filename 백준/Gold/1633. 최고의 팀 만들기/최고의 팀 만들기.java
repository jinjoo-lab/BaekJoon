import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;


        while((line = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(line," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list.add(new int[]{v1,v2});
        }

        int[][] dp = new int[16][16];

        for(int i = 0, size = list.size() ; i < size ; i++) {
            int[] curV = list.get(i);

            for(int j = Math.min(15, i + 1) ; j >= 0 ; j--) {
                for(int k = Math.min(15, i + 1); k >= 0 ; k --) {
                    if(j + k > i + 1 || j + k == 0)
                        continue;

                    int tmpV1 = (j == 0 ? 0 : dp[j - 1][k] + curV[0]);
                    int tmpV2 = (k == 0 ? 0 : dp[j][k - 1] + curV[1]);

                    dp[j][k] = Math.max(dp[j][k] , Math.max(tmpV1, tmpV2));
                }
            }
        }

        System.out.println(dp[15][15]);

        br.close();
    }
}
