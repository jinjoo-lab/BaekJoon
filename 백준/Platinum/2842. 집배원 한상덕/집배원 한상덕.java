import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

    static TreeSet<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        char[][] board = new char[n + 1][n + 1];
        int[][] numBoard = new int[n + 1][n + 1];

        int count = 0;
        int sx = 0;
        int sy = 0;
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();

            for (int j = 1; j <= n; j++) {
                board[i][j] = line.charAt(j - 1);

                if (board[i][j] == 'K')
                    count = count + 1;

                else if (board[i][j] == 'P') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= n; j++) {
                numBoard[i][j] = Integer.parseInt(st.nextToken());
                set.add(numBoard[i][j]);
            }
        }

        Iterator<Integer> iter = set.iterator();
        int Look[] = new int[set.size()];
        for (int i = 0; i < set.size(); i++) {
            Look[i] = iter.next();
        }


        int t1 = 0;
        int t2 = 0;
        int re = 200000000;


        while (t1 <= t2 && t2 < set.size()) {
            boolean tmp = false;
            if(numBoard[sx][sy] >= Look[t1] && numBoard[sx][sy]<= Look[t2]) {
                tmp = bfs(sx, sy, t1, t2, board, numBoard, count, Look);
            }
            if (tmp) {
                re = Math.min(re, Math.abs(Look[t1] - Look[t2]));
                t1 = t1 + 1;
            } else {
                t2 = t2 + 1;
            }
        }

        System.out.println(re);
        br.close();
    }

    static boolean bfs(int sx, int sy, int min, int max, char[][] board, int[][] numBoard, int result, int[] Look) {
        if (numBoard[sx][sy] < Look[min] || numBoard[sx][sy] > Look[max])
            return false;

        boolean[][] visit = new boolean[n + 1][n + 1];
        Queue<point> q = new LinkedList<>();
        q.add(new point(sx, sy));
        visit[sx][sy] = true;

        int count = 0;
        while (!q.isEmpty()) {
            point cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                if (visit[nx][ny])
                    continue;


                if (numBoard[nx][ny] >= Look[min] && numBoard[nx][ny] <= Look[max]) {
                    if (board[nx][ny] == 'K')
                        count = count + 1;

                    visit[nx][ny] = true;
                    q.add(new point(nx, ny));
                }

            }
        }

        if (result == count)
            return true;

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
