import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    public static int size;
    public static int[][] chess;
    
    public static int[] dy = {-1, -1, 1, 1}; // 좌상, 우상, 좌하, 우하
    public static int[] dx = {-1, 1, -1, 1}; // 좌상, 우상, 좌하, 우하
    
    public static int b_cnt = 0;
    public static int w_cnt = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        size = Integer.parseInt(br.readLine());
        
        chess = new int[size+1][size+1];
        
        // 입력
        for(int i=1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j=1; j <= size; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
 
        boolean[][] black_visited = new boolean[size+1][size+1];
        black_search(black_visited, 1, 1, 0);
        
    
        boolean[][] white_visited = new boolean[size+1][size+1];
        white_search(white_visited, 1, 2, 0);
        

        System.out.println(b_cnt+w_cnt);
    }
 
    public static void black_search(boolean[][] visited, int y, int x, int cnt) {
        // 검은자리에 둘 수 있는 횟수가 기존 횟수보다 크면 값을 변경한다.
        b_cnt = Math.max(cnt, b_cnt);
        
        // x 표가 체스판의 크기보다 크면 좌표를 다음칸의 첫번째 x 좌표로 이동 시켜준다.
        // 단, 흑색 자리는 y가 홀수일 경우 x의 시작 점이 1부터 시작되고
        //    짝수일 경우 2부터 시작된다.
        if(x > size) {
            y += 1;
            x = (y%2 == 0)?2:1;
        }
        
        // y의 좌표가 체스판의 사이즈를 초과할경우 탐색을 종료한다.
        if(y > size) return;
        
        
        // 현재자리에 비숍을 놓을 수 있는지 확인
        // 현재자리에 비숍을 놓을 수 있다면 비숍을 놓고 다음 탐색을 진행한다.
        // 다음 탐색을 진행할 때에는 x좌표를 2증가 시켜줘야 한다. 이유는 옆 자리는 색이 다른 자리니깐
        // 비숍을 둘 경우에는 cnt를 1 증가 시켜줘야한다.
        if(isAble(visited, y, x)) {
            visited[y][x] = true;
            black_search(visited, y, x+2, cnt+1);
            visited[y][x] = false;
        }
        
        // 현재자리에 비숍을 놓을수 있더라도 안놓을수도 있기 때문에 비숍을 놓지 않았을 경우에 대한 탐색도 진행한다.
        // 다음 탐색을 진행할 때에는 x좌표를 2증가 시켜줘야 한다. 이유는 옆 자리는 색이 다른 자리니깐
        // 비숍을 놓지 않았을 경우에는 cnt를 증가시킬 필요가 없다.
        black_search(visited, y, x+2, cnt);
    }
    
    public static void white_search(boolean[][] visited, int y, int x, int cnt) {
        // 백색 자리에 둘 수 있는 횟수가 기존 횟수보다 크면 값을 변경한다.
        w_cnt = Math.max(cnt, w_cnt);
        
        // x 표가 체스판의 크기보다 크면 좌표를 다음칸의 첫번째 x 좌표로 이동 시켜준다.
        // 단, 백색 자리는 y가 홀수일 경우 x의 시작 점이 2부터 시작되고
        //    짝수일 경우 1부터 시작된다.
        if(x > size) {
            y += 1;
            x = (y%2 == 0)?1:2;
        }
        
        // y의 좌표가 체스판의 사이즈를 초과할경우 탐색을 종료한다.
        if(y > size) return;
        
        // 현재자리에 비숍을 놓을 수 있는지 확인
        // 현재자리에 비숍을 놓을 수 있다면 비숍을 놓고 다음 탐색을 진행한다.
        // 다음 탐색을 진행할 때에는 x좌표를 2증가 시켜줘야 한다. 이유는 옆 자리는 색이 다른 자리니깐
        // 비숍을 둘 경우에는 cnt를 1 증가 시켜줘야한다.
        if(isAble(visited, y, x)) {
            visited[y][x] = true;
            white_search(visited, y, x+2, cnt+1);
            visited[y][x] = false;
        }
        
        // 현재자리에 비숍을 놓을수 있더라도 안놓을수도 있기 때문에 비숍을 놓지 않았을 경우에 대한 탐색도 진행한다.
        // 다음 탐색을 진행할 때에는 x좌표를 2증가 시켜줘야 한다. 이유는 옆 자리는 색이 다른 자리니깐
        // 비숍을 놓지 않았을 경우에는 cnt를 증가시킬 필요가 없다.
        white_search(visited, y, x+2, cnt);
    }
    
    
    public static boolean isAble(boolean[][] visited, int y, int x) {
        if(chess[y][x] == 0) return false; // 0이면 놓을 수 없다.
        
        for(int i=0; i < 4; i ++) {
            int yy = dy[i] + y;
            int xx = dx[i] + x;
            
            for(int j=1; j <= size; j++) {
                if(yy > 0 && xx > 0 && yy <= size && xx <= size) {
                    if(visited[yy][xx]) return false;
                    
                    yy += dy[i];
                    xx += dx[i];
                }
            }
        }
        return true;
    }
}
