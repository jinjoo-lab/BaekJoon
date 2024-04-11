import java.util.*;

class Node{
    int v;
    int c;
    
    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}

class Solution {
    
    static ArrayList<Node>[] graph;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 , size = paths.length ; i < size ; i++){
            int v1 = paths[i][0];
            int v2 = paths[i][1];
            int c = paths[i][2];
            
            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }
        
        answer = search(n,gates,summits);
        
        return answer;
    }
    
    static int[] search(int n,int[] gates, int[] summits){
        
        boolean[] isGates = new boolean[n+1];
        for(int i = 0 , size = gates.length ; i < size ; i++){
            isGates[gates[i]] = true;
        }
        
        
        boolean[] isSummits = new boolean[n+1];
        for(int i = 0 , size = summits.length ; i < size ; i++){
            isSummits[summits[i]] = true;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (x,y) -> x.c - y.c
        );
        
        int[] dis = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0 ; i < gates.length ; i++){
            dis[gates[i]] = 0;
            pq.add(new Node(gates[i],0));
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dis[cur.v] < cur.c)
                continue;
            
            
            for(Node next : graph[cur.v]){
                if(dis[next.v] > Math.max(cur.c,next.c)){
                    dis[next.v] = Math.max(cur.c,next.c);
                    
                    if(isSummits[next.v] || isGates[next.v]){
                        continue;
                    }
                    
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }
        
        int idx = 100;
        int v = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < summits.length ; i++){
            int curIdx = summits[i];
            
            if(v > dis[curIdx]){
                v = dis[curIdx];
                idx = curIdx;
            }else if(v == dis[curIdx]){
                if(idx > curIdx){
                    v = dis[curIdx];
                    idx = curIdx;
                }
            }
        }
        
        return new int[]{idx,v};
    }
    
    static void print(int[] dis,int n){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(dis[i]+" ");
        }System.out.println();
    }
}
