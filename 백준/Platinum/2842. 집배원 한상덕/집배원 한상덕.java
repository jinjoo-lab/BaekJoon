import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static int weight[][];
	static char Map[][];

	public static class XY {
		int y;
		int x;

		public XY(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	static int dy[] = { 0, 0, 1, 1, 1, -1, -1, -1 };
	static int dx[] = { -1, 1, 0, 1, -1, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		weight = new int[N][N];
		Map = new char[N][N];
		XY start = null;
		int pCnt = 0;
		
		for (int i = 0; i < N; i++) {
			Map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (Map[i][j] == 'K') {
					pCnt++;
					
				}else if (Map[i][j] == 'P') {
					start = new XY(i, j);
				}
				
			}
		}
		
		TreeSet<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
				set.add(weight[i][j]);
			}
		}

		Iterator<Integer> iter = set.iterator();
		int Look[] = new int[set.size()];
		for (int i = 0; i < set.size(); i++) {
			Look[i] = iter.next();
		}
		
		int result = Integer.MAX_VALUE;
		
		int left = 0;
		int right = 0;
		
		while(left<=right && right<set.size()) {
			int checkCnt = 0;

			Queue<XY> q =new LinkedList<>();
			boolean check[][] = new boolean[N][N];
			
			if(weight[start.y][start.x]>=Look[left] &&weight[start.y][start.x]<=Look[right]) {
				q.add(start);
				check[start.y][start.x] = true;
				
			}
			
			while(!q.isEmpty()) {
				XY tmp = q.poll();
				
				for(int d=0;d<8;d++) {
					int ny = tmp.y + dy[d];
					int nx = tmp.x + dx[d];
					if(ny<0||nx<0||ny>=N||nx>=N)continue;
					if(check[ny][nx])continue;
					if(weight[ny][nx]<Look[left]||weight[ny][nx]>Look[right])continue;
					
					if(Map[ny][nx] == 'K') checkCnt++;
					check[ny][nx] = true;
					q.add(new XY(ny,nx));
				}
			}
			if(checkCnt == pCnt) {
				
				result = Math.min(result, Look[right]-Look[left]);
				left++;
			}else {
				right++;
			}
			
			
			
			
			
		}
		
		

		System.out.println(result);

	}
}