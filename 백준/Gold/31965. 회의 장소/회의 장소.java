import java.util.*;
import java.io.*;

public class Main {

    static int n,q;
    static long[] sum;
    static long[] num;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        num = new long[n + 1];
        sum = new long[n + 1];

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i <= n; i++) {
            num[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i-1] + num[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= q ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int lIdx = upper(l);
            int rIdx = lower(r);

            if(lIdx >= rIdx) {
                sb.append("0\n");
                continue;
            }

            long tmp1 = getValue(lIdx,lIdx,rIdx);
            long tmp2 = getValue(rIdx,lIdx,rIdx);
            long tmp3 = getValue((lIdx + rIdx) /2 , lIdx, rIdx);

            sb.append(Math.max(tmp1,tmp2) - tmp3).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    static long getValue(int t,int l,int r) {
        long rV = (sum[r] - sum[t - 1]) - (num[t] * (r - t + 1));
        long lV = (num[t] * (t - l + 1)) - (sum[t] - sum[l - 1]);

        return lV + rV;
    }


    static int upper(int target) {
        int l = 1;
        int r = n;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(num[mid] >= target) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return l;
    }

    static int lower(int target) {
        int l = 1;
        int r = n;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(num[mid] > target) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return r;
    }
}
