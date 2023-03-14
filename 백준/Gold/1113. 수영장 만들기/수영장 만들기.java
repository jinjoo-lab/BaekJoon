import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Main{
    public static int N, M, answer, maxHeight, arr[][];
    public static boolean check;
    public static Queue<int[]> q;
    public static boolean vtd[][];
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, -1, 0, 1};
        
    public static void init() {
        for(int i = 0; i < N; i++) 
            for(int j = 0; j < M; j++)
                vtd[i][j] = false;
    }
    
    public static int solve(int value) {
        int size = 1;
        while(!q.isEmpty()) {
            int tmp[] = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) {
                    check = true;
                    continue;
                }
                else if(!vtd[nx][ny] && arr[nx][ny] < value){
                    vtd[nx][ny] = true;
                    q.add(new int[] {nx,ny});
                    size++;
                }
            }
        }
        if(check) size = 0; 
        return size;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
 
        answer = 0;
        maxHeight = -987654321;
        arr = new int[N][M];
        vtd = new boolean[N][M];
        q = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            String str = sc.next();
            for(int j = 0; j < M; j++) {
                arr[i][j] = Character.getNumericValue(str.charAt(j));
                if(maxHeight < arr[i][j]) maxHeight = arr[i][j];
            }
        }
 
        for(int p = 2; p <= maxHeight; p++) {
            init();
            for(int i = 1; i < N-1; i++) {
                for(int j = 1; j < M-1; j++) {
                    check = false;
                    if(arr[i][j] < p && !vtd[i][j]) {
                        vtd[i][j] = true;
                        q.add(new int[] {i, j});
                        answer += solve(p);
                    }
                }
            }
        }
        
        System.out.println(answer);
    }
}
