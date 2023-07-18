import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int[][] day = new int[n][2];

        int[] height = new int[366];

        int max = 0;
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            max = Math.max(max,y);

            for(int j = x; j<= y; j++)
                height[j] +=1;
        }

        int sum = 0;
        int maxHeight = 0;
        int width = 0;
        for (int i = 0; i <= 365; i++) {
            if (height[i] == 0) {
                sum+=maxHeight*width;
                maxHeight = width = 0;
                continue;
            }
            width++;
            maxHeight = Math.max(maxHeight, height[i]);
        }
        sum+=maxHeight*width;
        System.out.println(sum);

        br.close();
    }
}
