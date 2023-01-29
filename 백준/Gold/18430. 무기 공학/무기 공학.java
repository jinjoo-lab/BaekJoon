import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[6][6];
    static boolean[][] visit = new boolean[6][6];
    static int result = 0;
    static int n = 0;
    static int m = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        travel(0,1,1);
        System.out.println(result);
        br.close();
    }

    static void travel(int v, int i, int j) {
        // 끝 열인 경우 다음 행으로 탐색할 수 있도록 조정
        if(j>m)
        {
            i =i+1;
            j=1;
        } 
        // 종료 조건 모든 행을 탐색한 경우 !!
        if(i>n)
        {
            if (result < v) {
                result = v;
            }
            return;
        }
        
        // 하나의 중심 좌표( 값이 2배가 되는 곳)를 기준으로 나오는 부메랑의 개수는 최대 4개이다. 해당 경우를 계산
        if (!visit[i][j]) {
            for (int k = 0; k < 4; k++) {
                if (k == 0) {
                    int x1 = i + 1;
                    int y1 = j;
                    int x2 = i;
                    int y2 = j + 1;

                    if (isit(x1, y1, x2, y2) && !visit[x1][y1] && !visit[x2][y2]) {
                        visit[i][j] = true;
                        visit[x1][y1] = true;
                        visit[x2][y2] = true;
                        int nv = 2 * board[i][j] + board[x1][y1] + board[x2][y2];
                        travel(v + nv,i,j+1);
                        visit[i][j] = false;
                        visit[x1][y1] = false;
                        visit[x2][y2] = false;
                    }
                }
                if (k == 1) {
                    int x1 = i + 1;
                    int y1 = j;
                    int x2 = i;
                    int y2 = j - 1;

                    if (isit(x1, y1, x2, y2) && !visit[x1][y1] && !visit[x2][y2]) {
                        visit[i][j] = true;
                        visit[x1][y1] = true;
                        visit[x2][y2] = true;
                        int nv = 2 * board[i][j] + board[x1][y1] + board[x2][y2];
                        travel(v + nv,i,j+1);
                        visit[i][j] = false;
                        visit[x1][y1] = false;
                        visit[x2][y2] = false;
                    }
                }
                if (k == 2) {
                    int x1 = i - 1;
                    int y1 = j;
                    int x2 = i;
                    int y2 = j - 1;

                    if (isit(x1, y1, x2, y2) && !visit[x1][y1] && !visit[x2][y2]) {
                        visit[i][j] = true;
                        visit[x1][y1] = true;
                        visit[x2][y2] = true;
                        int nv = 2 * board[i][j] + board[x1][y1] + board[x2][y2];
                        travel(v + nv,i,j+1);
                        visit[i][j] = false;
                        visit[x1][y1] = false;
                        visit[x2][y2] = false;
                    }
                }
                if (k == 3) {
                    int x1 = i - 1;
                    int y1 = j;
                    int x2 = i;
                    int y2 = j + 1;

                    if (isit(x1, y1, x2, y2) && !visit[x1][y1] && !visit[x2][y2]) {
                        visit[i][j] = true;
                        visit[x1][y1] = true;
                        visit[x2][y2] = true;
                        int nv = 2 * board[i][j] + board[x1][y1] + board[x2][y2];
                        travel(v + nv,i,j+1);
                        visit[i][j] = false;
                        visit[x1][y1] = false;
                        visit[x2][y2] = false;
                    }
                }
            }
        }
        // 이미 방문한 좌표의 경우 다음 열의 좌표로 이동할 수 있도록 처리 ! (이 부분이 살짝 키 포인트)
        travel(v,i,j+1);
    }
    // 유효 값 (배열에서 존재하는 인덱스인지 판단해주는 메소드)
    static boolean isit(int x, int y, int x1, int y1) {
        if (x >= 1 && x <= n && y >= 1 && y <= m && x1 >= 1 && x1 <= n && y1 >= 1 && y1 <= m) {
            return true;
        }
        return false;
    }
}