import java.util.*;
class Solution {
    
    static HashMap<String,Integer> count = new HashMap<>();
    static HashMap<String,PriorityQueue<Node>> map = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        for(int i = 0 , size = genres.length; i < size ; i++){
            
            if(!map.containsKey(genres[i])){
                count.put(genres[i],0);
                map.put(genres[i],new PriorityQueue<>(
                (x,y) -> {
                    if(x.num == y.num)
                        return x.idx - y.idx;
                    
                    return y.num - x.num;
                }));
            }
            
            map.get(genres[i]).add(new Node(i,plays[i]));
            count.put(genres[i],count.get(genres[i]) + plays[i]);
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>(
            (x,y) -> y.num - x.num
        );
        
        for(String tmp : count.keySet()){
                pq.add(new Point(tmp,count.get(tmp)));
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            
            PriorityQueue<Node> tmp = map.get(cur.key);
            
            int tmpNum = 2;
            while(!tmp.isEmpty()){
                tmpNum--;
                Node next = tmp.poll();                
                
                list.add(next.idx);
                
                if(tmpNum == 0)
                    break;
            }
        }
        
        answer = new int[list.size()];
        
        for(int i = 0 ; i < list.size() ; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
class Point{
    String key;
    int num;
    
    Point(String key,int num){
        this.key = key;
        this.num = num;
    }
}


class Node{
    int idx;
    int num;
    
    Node(int idx,int num){
        this.idx = idx;
        this.num = num;
    }
}