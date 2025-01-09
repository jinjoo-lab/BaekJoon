import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int min = 10000000,max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min,board[i][j]);
                max = Math.max(max,board[i][j]);
            }
        }

        int result = 10000000;

        while(min <= max) {
            int mid = (min + max) / 2;

            int tmp = search(mid);

            if(tmp >= k) {
                result = mid;
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }


        System.out.println(result);

        br.close();
    }

    static int search(int target) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n+1][m+1];
        int count = 0;

        for(int i = 1 ; i <= n ; i++) {
            if(!v[i][1] && board[i][1] <= target) {
                v[i][1] = true;
                q.add(new int[]{i,1});
                count++;
            }

            if(!v[i][m] && board[i][m] <= target) {
                v[i][m] = true;
                q.add(new int[]{i,m});
                count++;
            }
        }

        for(int i = 1 ; i <= m ; i++) {
            if(!v[1][i] && board[1][i] <= target) {
                v[1][i] = true;
                q.add(new int[]{1,i});
                count++;
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny] || board[nx][ny] > target)
                    continue;

                v[nx][ny] = true;
                q.add(new int[]{nx,ny});
                count++;
            }
        }


        return count;
    }
}
