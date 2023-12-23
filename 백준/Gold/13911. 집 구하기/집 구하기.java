import java.io.*;
import java.util.*;
public class Main {
	public static int V,E;
	public static int X,Y;
	public static int distM[];
	public static int distS[];
	public static boolean visited[];
	public static int building[]; //-1 : 맥도날드, 0 : 집, 1 : 스타벅스
	public static class Node{
		int num;
		int cost;
		public Node(){}
		public Node(int a,int b){
			num = a;
			cost = b;
		}
	}
	public static ArrayList<Node> adj[];
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new ArrayList[V+1];
		distM = new int[V+1];
		visited = new boolean[V+1];
		distS = new int[V+1];
		building = new int[V+1];
		for(int i=1;i<=V;i++){
			adj[i] = new ArrayList<Node>();
			distM[i] = 100000001;
			distS[i] = 100000001;
		}
		for(int i=0;i<E;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[s].add(new Node(e,c));
			adj[e].add(new Node(s,c));
		}
		st = new StringTokenizer(br.readLine());
		int macNum = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> macStarts = new ArrayList<Integer>();
		ArrayList<Integer> sbuckStarts = new ArrayList<Integer>();
		for(int i=0;i<macNum;i++){
			int mac = Integer.parseInt(st.nextToken());
			macStarts.add(mac);
			building[mac] = -1;
		}
		
		st = new StringTokenizer(br.readLine());
		int startbucksNum = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<startbucksNum;i++){
			int starbucks = Integer.parseInt(st.nextToken());
			sbuckStarts.add(starbucks);
			building[starbucks] = 1;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1,o2) -> o1.cost-o2.cost);

		for(int j=0;j<macStarts.size();j++){
			int macStart = macStarts.get(j);
			if(visited[macStart]) continue;
			visited[macStart] = true;
			pq.add(new Node(macStart,0));
			distM[macStart] = 0;
			while(!pq.isEmpty()){
				Node curNode = pq.poll();
				int curNum = curNode.num;
				int curCost = curNode.cost;
				for(int i=0;i<adj[curNum].size();i++){
					int nextNum = adj[curNum].get(i).num;
					int nextCost = adj[curNum].get(i).cost;
			
					if(building[nextNum]==-1 && !visited[nextNum]){

						visited[nextNum] = true;
						distM[nextNum]  = 0;
						pq.add(new Node(nextNum,0));
					}else{
						if(nextCost + curCost > X)continue;
						if(distM[nextNum] < nextCost + curCost) continue;
						distM[nextNum] = nextCost + curCost;
						pq.add(new Node(nextNum,nextCost+curCost));
					}
				}
			}
		}
		
		pq.clear();
		for(int j=0;j<sbuckStarts.size();j++){
			int sbuckStart = sbuckStarts.get(j);
			if(visited[sbuckStart]) continue;
			visited[sbuckStart] = true;
			pq.add(new Node(sbuckStart,0));
			while(!pq.isEmpty()){
				Node curNode = pq.poll();
				int curNum = curNode.num;
				int curCost = curNode.cost;
				for(int i=0;i<adj[curNum].size();i++){
					int nextNum = adj[curNum].get(i).num;
					int nextCost = adj[curNum].get(i).cost;
					//1.목적지가 맥도날드면 그 가중치랑 curCost랑 비교해서 짧은 것 갱신 및 nextCost = 0, 2.X보다 큰 거리는 안됨 
					if(building[nextNum]==1 && !visited[nextNum]){
//						distM[curNum] = Math.min(distM[curNum], nextCost);
						visited[nextNum] = true;
						distS[nextNum]  = 0;
						pq.add(new Node(nextNum,0));
					}else{
						if(nextCost + curCost > Y)continue;
						if(distS[nextNum] < nextCost + curCost) continue;
						distS[nextNum] = nextCost + curCost;
						pq.add(new Node(nextNum,nextCost+curCost));
					}
				}
			}
		}
		
		int minDist = Integer.MAX_VALUE;
		for(int i=1;i<=V;i++){
			if(building[i]!=0) continue;
			if(distM[i] == 100000001 || distS[i] == 100000001) continue;
			minDist = Math.min(minDist, distM[i] + distS[i]);
		}
		if(minDist== Integer.MAX_VALUE){
			System.out.println(-1);
		}else{
			System.out.println(minDist);
		}
		
	}

}