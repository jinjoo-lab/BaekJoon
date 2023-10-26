import java.io.*;
import java.util.*;

public class Solution {
    static double[] num = new double[3];
    static int t = 0;
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=t;i++){

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<3;j++){
                num[j] = Double.parseDouble(st.nextToken());
            }

            double result = find();
            sb.append("#"+i+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static double find(){
        double d1 = num[1] - num[0];
        double d2 = num[2] - num[1];

        double tmp = Math.abs(d2 - d1) / 2;

        return tmp;
    }
}

