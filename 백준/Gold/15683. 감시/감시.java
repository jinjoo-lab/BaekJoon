import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[9][9];
    static point[] cctv = new point[9];
    static int cctv_num = 0;
    static int result = 0;
    static int g = 0;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] >= 1 && board[i][j] < 6) {
                    cctv[cctv_num] = new point(i, j, board[i][j], -1);
                    cctv_num++;
                } else if (board[i][j] == 0) {
                    result++;
                    g++;
                }
            }
        }

        travel(0, 0);
        System.out.println(g);

        br.close();
    }

    static int cal() {
        boolean[][] visit = new boolean[9][9];
        int re = 0;
        for (int i = 0; i < cctv_num; i++) {
            visit[cctv[i].x][cctv[i].y] = true;
        }

        for (int i = 0; i < cctv_num; i++) {
            Queue<point> q = new LinkedList<>();
            q.add(cctv[i]);

            while (!q.isEmpty()) {
                point cur = q.poll();

                if (cur.type == 1) {
                    int nx = cur.x + dx[cur.dir];
                    int ny = cur.y + dy[cur.dir];

                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                        if (board[nx][ny] != 6) {
                            if(!visit[nx][ny]) {
                                visit[nx][ny] = true;
                                if (board[nx][ny] == 0) {
                                    re = re + 1;
                                }
                            }
                            q.add(new point(nx, ny, cur.type, cur.dir));
                        } else if (board[nx][ny] == 6) {
                            break;
                        }
                    }
                } else if (cur.type == 2) {
                    if (cur.dir == 0) {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            k++;
                        }
                    } else if (cur.dir == 1) {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            k++;
                        }
                    }
                }
                else if (cur.type == 3) {
                    if(cur.dir==0)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            k++;
                        }
                    }
                    else if(cur.dir==1)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            k++;
                        }
                    }
                    else if(cur.dir==2)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            k++;
                        }
                    }
                    else{
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        while(type1||type2)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            if(type2)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }
                            k++;
                        }
                    }
                }
                else if(cur.type==4)
                {
                    if(cur.dir==0)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        boolean type3 = true;

                        while(type1||type2||type3)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }

                            if(type2)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            if(type3)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type3 = false;
                                    }
                                }
                                else{
                                    type3 = false;
                                }
                            }

                            k++;
                        }
                    }

                    else if(cur.dir==1)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        boolean type3 = true;

                        while(type1||type2||type3)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }

                            if(type2)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            if(type3)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type3 = false;
                                    }
                                }
                                else{
                                    type3 = false;
                                }
                            }

                            k++;
                        }
                    }

                    else if(cur.dir==2)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        boolean type3 = true;

                        while(type1||type2||type3)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[0];
                                int ny = cur.y + k*dy[0];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }

                            if(type2)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            if(type3)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type3 = false;
                                    }
                                }
                                else{
                                    type3 = false;
                                }
                            }

                            k++;
                        }
                    }

                    else if(cur.dir==3)
                    {
                        int k = 1;
                        boolean type1 = true;
                        boolean type2 = true;
                        boolean type3 = true;

                        while(type1||type2||type3)
                        {
                            if(type1)
                            {
                                int nx = cur.x + k*dx[1];
                                int ny = cur.y + k*dy[1];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type1 = false;
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }

                            if(type2)
                            {
                                int nx = cur.x + k*dx[2];
                                int ny = cur.y + k*dy[2];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type2 = false;
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }

                            if(type3)
                            {
                                int nx = cur.x + k*dx[3];
                                int ny = cur.y + k*dy[3];

                                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                    if (board[nx][ny] != 6) {
                                        if(!visit[nx][ny]) {
                                            visit[nx][ny] = true;
                                            if (board[nx][ny] == 0) {
                                                re = re + 1;
                                            }
                                        }
                                    }
                                    else{
                                        type3 = false;
                                    }
                                }
                                else{
                                    type3 = false;
                                }
                            }

                            k++;
                        }
                    }
                }
                else if(cur.type==5)
                {
                    int k = 1;
                    boolean type1 = true;
                    boolean type2 = true;
                    boolean type3 = true;
                    boolean type4 = true;

                    while(type1||type2||type3||type4)
                    {
                        if(type1)
                        {
                            int nx = cur.x + k*dx[0];
                            int ny = cur.y + k*dy[0];

                            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                if (board[nx][ny] != 6) {
                                    if(!visit[nx][ny]) {
                                        visit[nx][ny] = true;
                                        if (board[nx][ny] == 0) {
                                            re = re + 1;
                                        }
                                    }
                                }
                                else{
                                    type1 = false;
                                }
                            }
                            else{
                                type1 = false;
                            }
                        }

                        if(type2)
                        {
                            int nx = cur.x + k*dx[1];
                            int ny = cur.y + k*dy[1];

                            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                if (board[nx][ny] != 6) {
                                    if(!visit[nx][ny]) {
                                        visit[nx][ny] = true;
                                        if (board[nx][ny] == 0) {
                                            re = re + 1;
                                        }
                                    }
                                }
                                else{
                                    type2 = false;
                                }
                            }
                            else{
                                type2 = false;
                            }
                        }

                        if(type3)
                        {
                            int nx = cur.x + k*dx[2];
                            int ny = cur.y + k*dy[2];

                            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                if (board[nx][ny] != 6) {
                                    if(!visit[nx][ny]) {
                                        visit[nx][ny] = true;
                                        if (board[nx][ny] == 0) {
                                            re = re + 1;
                                        }
                                    }
                                }
                                else{
                                    type3 = false;
                                }
                            }
                            else{
                                type3 = false;
                            }
                        }
                        if(type4)
                        {
                            int nx = cur.x + k*dx[3];
                            int ny = cur.y + k*dy[3];

                            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                                if (board[nx][ny] != 6) {
                                    if(!visit[nx][ny]) {
                                        visit[nx][ny] = true;
                                        if (board[nx][ny] == 0) {
                                            re = re + 1;
                                        }
                                    }
                                }
                                else{
                                    type4 = false;
                                }
                            }
                            else{
                                type4 = false;
                            }
                        }

                        k++;
                    }
                }
            }
        }
        int goal = result - re;
        return goal;
    }

    static void travel(int k, int num) {
        if (k == cctv_num) {
            int r = cal();
            if(g>r)
            {
                g=r;
            }
            return;
        }

        if (cctv[num].type == 1) {
            for (int i = 0; i < 4; i++) {
                cctv[num] = new point(cctv[num].x, cctv[num].y, 1, i);
                travel(k + 1, num + 1);
            }
        } else if (cctv[num].type == 2) {
            for (int i = 0; i < 2; i++) {
                cctv[num] = new point(cctv[num].x, cctv[num].y, 2, i);
                travel(k + 1, num + 1);
            }
        } else if (cctv[num].type == 3) {
            for (int i = 0; i < 4; i++) {
                cctv[num] = new point(cctv[num].x, cctv[num].y, 3, i);
                travel(k + 1, num + 1);
            }
        } else if (cctv[num].type == 4) {
            for (int i = 0; i < 4; i++) {
                cctv[num] = new point(cctv[num].x, cctv[num].y, 4, i);
                travel(k + 1, num + 1);
            }
        } else if (cctv[num].type == 5) {
            cctv[num] = new point(cctv[num].x, cctv[num].y, 5, 1);
            travel(k + 1, num + 1);
        }
    }
}

class point {
    int x;
    int y;
    int type;
    int dir;

    point(int x, int y, int type, int dir) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.dir = dir;
    }
}