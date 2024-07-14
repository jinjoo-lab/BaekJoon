import java.util.*;
import java.io.*;

public class Main {

    static int n, t, r, c;

    static char[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            char[] cur = br.readLine().toCharArray();

            for (int j = 1; j <= n; j++) {
                arr[i][j] = cur[j - 1];
            }
        }

        int[][][] v = new int[n + 1][n + 1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for(int k = 0 ; k <= 1 ; k++) {
                    v[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        v[1][1][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x, y) -> x[3] - y[3]
        );

        pq.add(new int[]{1, 1, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0] == r && cur[1] == c)
                continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isOut(nx, ny))
                    continue;

                if (v[nx][ny][0] > v[cur[0]][cur[1]][cur[2]] + 1) {
                    v[nx][ny][0] = v[cur[0]][cur[1]][cur[2]] + 1;
                    pq.add(new int[]{nx, ny, 0, v[nx][ny][0]});
                }
            }

            for (int i = 0; i < 4; i++) {
                int tmpV = findW(cur[0], cur[1], i);

                if (tmpV == Integer.MAX_VALUE)
                    continue;

                int nx = cur[0] + (tmpV * dx[i]);
                int ny = cur[1] + (tmpV * dy[i]);

                int moveT = cur[2] == 0 ? t : 0;

                if (v[nx][ny][1] > v[cur[0]][cur[1]][cur[2]] + moveT + 1) {
                    v[nx][ny][1] = v[cur[0]][cur[1]][cur[2]] + moveT + 1;
                    pq.add(new int[]{nx, ny, 1, v[nx][ny][1]});
                }
            }

        }

        System.out.println(Math.min(v[r][c][0],v[r][c][1]));

        br.close();
    }

    static boolean isOut(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static int findW(int x, int y, int d) {
        int idx = 1;

        while (true) {
            int nx = x + (idx * dx[d]);
            int ny = y + (idx * dy[d]);

            if (isOut(nx, ny))
                break;

            if (arr[nx][ny] == '#')
                return idx;

            idx++;
        }

        return Integer.MAX_VALUE;
    }
}
