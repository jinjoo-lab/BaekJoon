import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        arr = new int[2*n + 1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= 2*n ; i++) {
            arr[i] = st.nextToken().equals("X") ? 0 : 1;
        }

        Queue<int[]> q = new ArrayDeque<>();
        HashMap<String,Integer> set = new HashMap<>();

        set.put(toString(arr),0);
        q.add(arr);

        int result = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();

            int t1= 0;
            int t2 =0;

            for(int i = 1 ; i <= 2*n ; i+= 2) {
                int v1 = tmp[i];
                int v2 = tmp[i+1];

                if(v1 == v2)
                    continue;

                if(v1 == 1)
                    t1 += 1;

                else
                    t2 += 1;
            }

            if(t1 > t2) {
                result = set.get(toString(tmp));
                break;
            }

            for(int i = 2 ; i <= 2*n ; i++) {
                int[] tmpTmp = change(i,tmp);

                if(!set.containsKey(toString(tmpTmp))) {
                    set.put(toString(tmpTmp),set.get(toString(tmp))+1);
                    q.add(tmpTmp);
                }
            }
        }

        System.out.println(result);

        br.close();
    }

    static String toString(int[]arr) {
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= 2*n; i++) {
            sb.append(arr[i]).append(" ");
        }

        return sb.toString();
    }

    static int[] change(int idx,int[] arr) {

        int[] re = new int[2*n+1];
        for(int i = 1 ; i <= 2*n ; i++) {
            re[i] = arr[i];
        }

        int tmp = arr[idx];

        for(int i = idx ; i >= 1 ; i--) {
            re[i] = re[i - 1];
        }

        re[1] = tmp;

        return re;
    }
}
