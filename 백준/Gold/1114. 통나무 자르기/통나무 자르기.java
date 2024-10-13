import java.util.*;
import java.io.*;

public class Main {

    static int L,K,C;

    static int[] arr;

    static int[] dif;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[K+1];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < K ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[K] = L;

        Arrays.sort(arr);

        dif = new int[K+1];
        dif[0] = arr[0];

        for(int i = 1 ; i <= K ; i++) {
            dif[i] = arr[i] - arr[i-1];
        }

        int l = 0;
        int r = L;

        int mid = 0;

        int re = 0;
        int reIdx = 0;

        while(l <= r) {

            mid = (l + r) / 2;
            Node tmp = go(mid);

            if(tmp.result) {
                re = mid;
                reIdx = tmp.count == C ? arr[tmp.idx] : arr[0];
                r = mid - 1;
            }else {
                l = mid + 1;
            }

        }

        System.out.println(re+" "+reIdx);
        br.close();
    }

    static Node go(int target) {
        int cur = 0;
        int count = 0;
        int idx = 0;

        for(int i = K ; i >= 0 ; i--) {
            if(dif[i] > target) {
                return new Node(false,-1,-1);
            }

            if(cur + dif[i] > target) {
                cur = 0;
                count++;
                idx = i;
            }

            cur += dif[i];
        }

        return new Node(count <= C, count ,idx);
    }

    static class Node {
        boolean result;
        int count;
        int idx;

        Node(boolean result, int count, int idx) {
            this.result = result;
            this.count = count;
            this.idx = idx;
        }
    }
}
