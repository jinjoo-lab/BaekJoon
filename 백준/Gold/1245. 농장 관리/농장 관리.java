import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static boolean[][] v;
    static int[][] b;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        b = new int[n + 1][m + 1];
        v = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!v[i][j]) {
                    v[i][j] = true;
                    search(i, j);
                }
            }
        }

        System.out.println(result);
        br.close();
    }


    static void search(int x, int y) {
        int startNum = b[x][y];
        boolean find = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;


                if (!v[nx][ny] && b[nx][ny] == startNum) {
                    v[nx][ny] = true;
                    q.add(new Node(nx, ny));
                } else if (b[nx][ny] > startNum) {
                    find = false;
                }

            }
        }

        if(find) {
            result = result + 1;
        }
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
