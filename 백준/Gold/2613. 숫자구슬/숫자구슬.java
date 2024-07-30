import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];

        int min = 0;
        int max = 0;

        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max+= arr[i];
            min = Math.max(min,arr[i]);
        }

        int mid;
        int result = max;

        while(min <= max) {
            mid = (min + max) / 2;
            int tmpCount = go(mid);

            if(tmpCount <= m) {
                result = Math.min(result,mid);
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }

        System.out.println(result);

        int cnt = 0;
        int tmp = 0;
        int group = m;
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            tmp += arr[i];

            if(tmp > result) {
                tmp = arr[i];
                sb.append(cnt+" ");
                group--;
                cnt = 1;
            }else {
                cnt++;
            }

            if(group == n - i + 1)
                break;
        }

        for(int i = group ; i > 0 ; i--) {
            sb.append(cnt+" ");
            cnt = 1;
        }


        System.out.println(sb.toString());

        br.close();
    }

    static int go(int target) {
        int count = 0;
        int tmpV = 0;

        for(int i = 1 ; i <= n ; i++) {
            tmpV += arr[i];

            if(tmpV > target) {
                tmpV = arr[i];
                count++;
            }
        }
        count++;

        return count;
    }

}
