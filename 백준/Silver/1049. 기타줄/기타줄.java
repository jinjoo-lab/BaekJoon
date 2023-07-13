import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        int mA = 1001;
        int mB = 1001;

        for(int i=0;i<m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            mA = Math.min(mA,x);
            mB = Math.min(mB,y);
        }

        int result = 0;

        if(mB*6 <= mA)
        {
            result = n * mB;
        }

        else{
            result += mA * (n / 6);

            if(mA <= mB*(n % 6))
            {
                result += mA;
            }

            else
            {
                result += mB * (n % 6);
            }

        }

        System.out.println(result);
        br.close();
    }
}