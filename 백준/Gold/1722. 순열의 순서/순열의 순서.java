import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long m = 0;
    static long[] fact = new long[21];
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        result = new int[n+1];

        fact[0] = 1;
        for(int i=1;i<=20;i++){
            fact[i] = i * fact[i-1];
        }

        st = new StringTokenizer(br.readLine(), " ");
        int w = Integer.parseInt(st.nextToken());

        if(w == 1){
            m = Long.parseLong(st.nextToken());
            find();
        }
        else{
            for(int i=1;i<=n;i++){
                result[i] = Integer.parseInt(st.nextToken());
            }

            find2();
        }


        System.out.println();

        br.close();
    }

    static void find(){
        StringBuilder sb = new StringBuilder();
        boolean[] v = new boolean[n+1];

        for(int i=1;i<=n;i++){

            long cur = fact[n - i];

            for(int j=1;j<=n;j++){

                if(v[j])
                    continue;

                if(m <= cur)
                {
                    v[j] = true;
                    sb.append(j+" ");
                    break;
                }

                else{
                    m -= cur;
                }
            }
        }
        System.out.println(sb);
    }

    static void find2(){
        long re = 0;
        boolean[] v = new boolean[n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<result[i];j++){
                if(v[j])
                {
                    continue;
                }

                re += fact[n-i];
            }

            v[result[i]] = true;
        }

        System.out.println(re + 1);
    }
}
