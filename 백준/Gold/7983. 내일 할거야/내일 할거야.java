import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        int[][] homework = new int[n][2];

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            homework[i][0] = t;
            homework[i][1] = d;
        }

        Arrays.sort(homework,(x,y) ->{
            if(x[1] == y[1])
                return y[0] - x[0];

            return y[1] - x[1];
        });

        int ld = homework[0][1];
        int sd = homework[0][1] - homework[0][0];

        for(int i=1;i<n;i++)
        {
            if(sd > homework[i][1])
            {
                sd = homework[i][1];
            }

            sd = sd - homework[i][0];

        }

        System.out.println(sd);

        br.close();
    }
}