import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[31][31];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int num = 0;
    static int re = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int case_num = 1;
        while(true) {
            String input= "";
            input = br.readLine();
            if(input==null)
                break;

            re = -1;
            num = 0;

            StringTokenizer st = new StringTokenizer(input, " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[31][31];
            for (int i = 1; i <= n; i++) {
                String[] line = br.readLine().split("");
                for (int j = 1; j <= m; j++) {
                    if (line[j - 1].equals("*"))
                        board[i][j] = 1;

                    else {
                        num = num + 1;
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (board[i][j] == 0) {
                        boolean[][] cur = new boolean[31][31];
                        cur[i][j] = true;
                        travel(i, j, cur, 0);
                    }
                }
            }
            System.out.println("Case "+case_num+": "+re);
            case_num = case_num+1;
        }
        br.close();
    }

    static boolean can(int x, int y, boolean[][] visit) {
        boolean go = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > m)
                continue;

            if (visit[nx][ny] || board[nx][ny] == 1)
                continue;

            go = true;
        }

        return go;
    }

    static boolean result(boolean[][] visit) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == 1)
                    continue;

                if (visit[i][j])
                    count = count + 1;
            }
        }

        if (count == num)
            return true;

        return false;
    }

    static void travel(int x, int y, boolean[][] visit, int count) {
        if(re!=-1&&re<=count)
            return;

        if (!can(x, y, visit)) {
            if (result(visit)) {
                if (re == -1 || re > count)
                    re = count;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int c = 0;
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (true) {
                if (nx < 1 || nx > n || ny < 1 || ny > m)
                    break;

                if (visit[nx][ny] || board[nx][ny] == 1)
                    break;

                c = c+1;
                visit[nx][ny] = true;
                nx = nx + dx[i];
                ny = ny + dy[i];
            }
            nx = nx - dx[i];
            ny = ny - dy[i];
            if (nx == x && ny == y)
                continue;

            travel(nx, ny, visit, count + 1);
            for(int j=1;j<=c;j++)
            {
                int tx = x + dx[i]*j;
                int ty = y + dy[i]*j;

                visit[tx][ty] = false;
            }
        }

    }

}