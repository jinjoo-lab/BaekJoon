import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] board;
    static int[] result;

    static int[] past;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n][2];
        result = new int[n];
        past = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> x - y
        );
        Arrays.sort(board,(x,y) -> x[0] - y[0]);


        int i = 1;
        result[0] = board[0][1];
        int j = 0;

        while(i < n){

            if(result[j] < board[i][1]){
                result[j+1] = board[i][1];
                past[i] = j+1;
                j += 1;
            }else{
                int idx = bs(0,j,board[i][1]);
                result[idx] = board[i][1];
                past[i] = idx;
            }

            i += 1;
        }

        sb.append(n - (j+1)+"\n");

        Stack<Integer> stack = new Stack<>();

        int find = j;
        for(int k= n-1;k>=0;k--){
            if(find == past[k]){
                stack.push(board[k][0]);
                find = find - 1;
            }
        }

        boolean[] v = new boolean[500001];

        while(!stack.isEmpty()){
            int cur = stack.pop();
            v[cur] = true;
        }

        for(int a=0;a<n;a++){
            if(!v[board[a][0]]){
                sb.append(board[a][0]+"\n");
            }
        }

        System.out.print(sb);
        bf.close();
    }

    static int bs(int l,int r,int t){
        int mid;

        while(l < r){
            mid = (l + r) / 2;

            if(result[mid] < t){
                l  = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }
}
