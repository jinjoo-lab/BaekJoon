import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long count = 0;
        long past = 0;
        int result = -1;

        for(int i=1;i<=n;i++){

            past = count;
            count += (int)(Math.log10(i)+1);

            if(past <= m && count >= m){
                StringBuilder tmp = new StringBuilder();
                tmp.append(i-1);
                tmp.append(i);


                long start = past - (int)(Math.log10(i-1)+1) + 1;
                if(past == 0)
                    start = 0;

                for(int j=0;j<tmp.length();j++){
                    if(start == m){
                        result = tmp.charAt(j);
                        break;
                    }

                    start = start + 1;
                }
                break;
            }

        }

        if(result == -1)
            System.out.println(result);

        else
            System.out.println(result - '0');
        br.close();
    }
}
