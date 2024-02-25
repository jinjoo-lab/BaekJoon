import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
 
    private static int n, k;
    private static int[][] visit = new int[2][500001];
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
 
        Arrays.fill(visit[0], -1);
        Arrays.fill(visit[1], -1);
 
        visit[0][n] = 0;
 
        System.out.println((n == k) ? 0 : solution());
    }
 
    private static int solution() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
 
        int len, mod, time = 0;
        while (!queue.isEmpty()) {
            len = queue.size();
            time++;
            mod = time % 2; // 홀수, 짝수 판단
 
            for (int i = 0; i < len; i++) {
                int sb = queue.poll();
 
                if (sb - 1 >= 0 && visit[mod][sb - 1] == -1) {
                    queue.add(sb - 1);
                    visit[mod][sb - 1] = time;
                }
                if (sb + 1 <= 500000 && visit[mod][sb + 1] == -1) {
                    queue.add(sb + 1);
                    visit[mod][sb + 1] = time;
                }
                if (sb * 2 <= 500000 && visit[mod][sb * 2] == -1) {
                    queue.add(sb * 2);
                    visit[mod][sb * 2] = time;
                }
            }
 
            int bro = getBro(time); // 동생의 위치
            if (bro > 500000) break; // 동생이 500000보다 넘어간다면 -1
            if (visit[mod][bro] != -1) return time; // 동생의 위치에 형이 있다면 time 반환
        }
 
        return -1;
    }
 
    private static int getBro(int n) {
        return k + (n * (n + 1) / 2);
    }
}