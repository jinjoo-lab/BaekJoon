import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static char[] arr;
    static ArrayList<Integer> rList = new ArrayList<>();
    static ArrayList<Integer> bList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n  = Integer.parseInt(st.nextToken());
        m  = Integer.parseInt(st.nextToken());

        arr = br.readLine().toCharArray();

        for(int i = 0 ; i < n ; i++) {
            if(arr[i] == 'R') {
                rList.add(i);
            }else if(arr[i] == 'B') {
                bList.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        int[] result = new int[4];

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int rIdx = lower(rList,l);

            if(rIdx + 1 >= rList.size() || rList.get(rIdx + 1) > r) {
                sb.append("-1\n");
                continue;
            }

            result[0] = rList.get(rIdx);
            result[1] = rList.get(rIdx + 1);

            int bIdx = lower(bList, result[1]);

            if(bIdx + 1 >= bList.size() || bList.get(bIdx + 1) > r) {
                sb.append("-1\n");
                continue;
            }

            result[2] = bList.get(bIdx);
            result[3] = bList.get(bIdx + 1);

            sb.append(result[0]+" "+result[1]+" "+result[2]+" "+result[3]+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int lower(ArrayList<Integer> list, int target) {
        int l = 0;
        int r = list.size();

        while(l < r) {
            int mid = (l + r) / 2;

            if(list.get(mid) < target) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        return l;
    }
}
