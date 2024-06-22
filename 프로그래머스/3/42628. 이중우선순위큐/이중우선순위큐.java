import java.util.*;

class Solution {
    
    static PriorityQueue<Node> max = new PriorityQueue<Node>(
        (x,y) -> y.v - x.v
    );
    
    static PriorityQueue<Node> min = new PriorityQueue<Node>(
        (x,y) -> x.v - y.v
    );
    
    static HashSet<Integer> isDeleted = new HashSet<>();
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        StringTokenizer st;
        String command;
        int tmpV;
        
        for(int i = 0, size = operations.length ; i < size ; i++) {
            st = new StringTokenizer(operations[i]," ");
            
            command = st.nextToken();
            tmpV = Integer.parseInt(st.nextToken());
            
            if(command.equals("I")) {
                max.add(new Node(i,tmpV));
                min.add(new Node(i,tmpV));
            }else{
                if(tmpV == 1) {
                    while(!max.isEmpty()) {
                        if(isDeleted.contains(max.peek().idx)) {
                            max.poll();
                            continue;
                        }
                        
                        Node tmp = max.poll();
                        isDeleted.add(tmp.idx);
                        break;
                    }
                }else {
                    while(!min.isEmpty()) {
                        if(isDeleted.contains(min.peek().idx)) {
                            min.poll();
                            continue;
                        }
                        
                        Node tmp = min.poll();
                        isDeleted.add(tmp.idx);
                        break;
                    }
                }
        
            }
        }
        
        while(!max.isEmpty()) {
            if(!isDeleted.contains(max.peek().idx)) {
                answer[0] = max.peek().v;
                break;
            }
            
            max.poll();
        }
        
        while(!min.isEmpty()) {
            if(!isDeleted.contains(min.peek().idx)) {
                answer[1] = min.peek().v;
                break;
            }
            
            min.poll();
        }
        
        return answer;
    }
}

class Node {
    int idx;
    int v;
    
    Node(int idx,int v) {
        this.idx = idx;
        this.v = v;
    }
}