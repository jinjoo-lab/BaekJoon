import java.util.*;
import java.io.*;

public class Main {

	static int n,m;
	static char[][] board;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n+1][m+1];
		v = new boolean[n+1][m+1];
		
		for(int i = 1 ; i <= n ; i++) {
			char[] line = br.readLine().toCharArray();
			
			for(int j = 1 ; j <= m ; j++) {
				board[i][j] = line[j-1];
			}
		}
		
		
		for(int i = 1 ; i <= n ; i++) {
			for(int j =1  ; j<= m ; j++) {
				erase(i,j);
			}
		}
		
		boolean result = false;
		
		loop : for(int i =1  ; i <= n ; i++) {
			for(int j =1 ; j <= m ; j++) {
				if(!v[i][j] && board[i][j] != '.') {
					int tmpC = go(i,j);
					
					if(tmpC >= 4) {
						result = true;
					}
					
					if(result)
						break;
				}
			}
		}
		
		if(result) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		
		br.close();
	}
	
	static int go(int x,int y) {
		int count = 1;

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		v[x][y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i = 0 ; i < 4 ; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(isOut(nx,ny) || board[nx][ny] == '.')
					continue;
				
				if(!v[nx][ny] && board[nx][ny] == board[x][y]) {
					v[nx][ny] = true;
					count++;
					q.add(new Node(nx,ny));
				}
			}
		}
		
		return count;
	}
	

	static boolean isOut(int x,int y) {
		if(x < 1 || x > n || y < 1 || y > m)
			return true;
		
		return false;
	}
	
	static void erase(int x,int y) {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] v = new boolean[n+1][m+1];
		v[x][y] = true;
		q.add(new Node(x,y));
		char curNum = board[x][y];
		
		while(!q.isEmpty()){
			Node cur = q.poll();
			int count = 0;
			
			for(int i = 0 ; i < 4 ; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(isOut(nx,ny))
					continue;
				
				if(board[nx][ny] == curNum) {
					count++;
				}
			}
			
			if(count < 2) {
				for(int i = 0 ; i < 4 ; i++) {
					int n2x = cur.x + dx[i];
					int n2y = cur.y + dy[i];
					
					if(isOut(n2x,n2y))
						continue;
					
					if(board[n2x][n2y] == curNum && !v[n2x][n2y]) {
						v[n2x][n2y] = true;
						q.add(new Node(n2x,n2y));
					}
				}
				
				board[cur.x][cur.y] = '.';
			}
		}
		
	}
	
	
	static void print() {
		for(int i =1 ; i <= n ; i++) {
			for(int j = 1 ;j <= m ; j++) {
				System.out.print(board[i][j]+" ");
			}System.out.println();
		}System.out.println();
	}
	
}
class Node{
	int x;
	int y;
	
	Node(int x,int y){
		this.x = x;
		this.y = y;
	}
}