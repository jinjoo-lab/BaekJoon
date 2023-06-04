import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board = new int[101][101];
    static int min = 200;
    static int max = 0;
    static int result = 201;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
                min = Math.min(min, board[i][j]);
            }
        }

        int left = 0;
        int right = 200;
        bs(left, right);
        System.out.println(result);

        br.close();
    }

    static void bs(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean tmp = false;

            for(int i=min;i<=max;i++)
            {
                if(i<=board[1][1] && board[1][1]<= i + mid)
                {
                    tmp = travel(i,i+mid);
                    if(tmp)
                        break;
                }
            }

            if (tmp) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean travel(int min,int max) {
        Queue<point> q = new LinkedList<>();
        boolean[][] visit = new boolean[101][101];
        q.add(new point(1, 1));
        visit[1][1] = true;

        while (!q.isEmpty()) {
            point cur = q.poll();
            if (cur.x == n && cur.y == n) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                    if (!visit[nx][ny]&&board[nx][ny]>=min && board[nx][ny]<=max) {
                        visit[nx][ny] = true;
                        q.add(new point(nx, ny));
                    }
                }
            }
        }
        return false;
    }
}

class point {
    int x;
    int y;

    point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}