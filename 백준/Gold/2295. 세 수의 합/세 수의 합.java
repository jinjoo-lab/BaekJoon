import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int result  = 0;
    static int[] arr;
    static int[] board;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }

        board = new int[n*n];
        int idx = 0;

        for(int i=0;i<n;i ++){
            for(int j=0;j<n;j++){
                int tmp = arr[i] + arr[j];

                board[idx] = tmp;
                idx += 1;
            }
        }

        Arrays.sort(board);
        int max = -1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(bs(arr[j] - arr[i])){
                    max = Math.max(max,arr[j]);
                }
            }
        }

        System.out.println(max);
        br.close();
    }

    static boolean bs(int t){
        int l = 0;
        int r = board.length - 1;

        while(l <= r){
            int mid = (l + r) / 2;

            if(board[mid] < t){
                l = mid + 1;
            }else if(board[mid] > t){
                r = mid - 1;
            }else{
                return true;
            }
        }

        return false;
    }
}
