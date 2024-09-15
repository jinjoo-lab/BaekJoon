import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long m;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        arr = new long[n+1];
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = arr[i-1] + Long.parseLong(st.nextToken());
        }

        long result = 0;
        HashMap<Long,Integer> map = new HashMap<>();

        for(int i = 1 ; i <= n ; i++) {
            long tmp = arr[i] - (m * i);

            if(map.containsKey(tmp)) {
                result += map.get(tmp);
                map.put(tmp,map.get(tmp)+1);
            }else {
                map.put(tmp,1);
            }
        }


        System.out.println(result + map.getOrDefault(0l,0));
        br.close();
    }
}
