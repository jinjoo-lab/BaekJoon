import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] map;
    static boolean[] visit = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 1;

    public static void dfs(int x, int y, int count) {

        ans = Math.max(ans,count);

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < R && cy < C) {
                if (!visit[map[cx][cy]]) {
                    visit[map[cx][cy]] = true;
                    dfs(cx, cy, count + 1);
                    visit[map[cx][cy]] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        visit[map[0][0]] = true;
        dfs(0, 0, 1);
        // (0,0)부터 시작하며, 현재 이동한 위치는 0회

        System.out.println(ans);
    }
}

