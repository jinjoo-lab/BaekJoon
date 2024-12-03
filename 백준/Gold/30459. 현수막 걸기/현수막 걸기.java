import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static double rr;

    static int[] row;
    static int[] height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        rr = Double.parseDouble(st.nextToken());

        row = new int[n];
        height = new int[m];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n; i++){
            row[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < m; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(row);
        Arrays.sort(height);


        double result = -1;

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                int l = 0;
                int r = m - 1;

                while(l <= r) {
                    int mid = (l + r) / 2;
                    double tmp = (double)Math.abs(row[i] - row[j]) * height[mid] / 2;
                    if(tmp <= rr) {
                        result = Math.max(result,tmp);
                        l = mid + 1;
                    }else {
                        r = mid - 1;
                    }
                }
            }
        }

        if(result == -1.0){
            System.out.println(-1);
        }else {
            System.out.printf("%.1f", result);
        }
        br.close();
    }

}
