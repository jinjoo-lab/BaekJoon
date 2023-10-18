import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    static String[] max = new String[101];
    static long[] min = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        max[2] = "1";
        max[3] = "7";
        min[1] = 9;
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;

        int[] num = {0,0,1,7,4,2,0,8};

        for(int i=4;i<=100;i++){
            max[i] = max[i-2] + "1";
        }


        for(int i=8;i<=100;i++){

            for(int j=2;j<8;j++){
                if(min[i] == 0)
                    min[i] = min[i-j]*10 + num[j];

                else
                    min[i] = Math.min(min[i],min[i-j]*10 + num[j]);
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            int cur = Integer.parseInt(br.readLine());
            sb.append(min[cur]+" "+max[cur]+"\n");
        }
        System.out.print(sb);
        br.close();
    }
}