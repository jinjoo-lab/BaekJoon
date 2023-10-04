import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int[] board;
    static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());

            if(!map.containsKey(board[i])){
                map.put(board[i],1);
            }

            else{
                map.put(board[i],map.get(board[i]) + 1);
            }
        }

        Stack<Node> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int[] result = new int[n+1];
        for(int i=1;i<=n;i++){

            while(!stack.isEmpty()){
                Node tmp = stack.peek();

                if(map.get(tmp.v) < map.get(board[i])){
                    result[tmp.i] = board[i];
                    stack.pop();
                }

                else{
                    break;
                }
            }

            stack.push(new Node(board[i],i));
        }

        while(!stack.isEmpty()){
            Node tmp = stack.pop();
            result[tmp.i] = -1;
        }

        for(int i=1;i<=n;i++){
            sb.append(result[i]+" ");
        }sb.append("\n");

        System.out.print(sb);
        br.close();
    }
}
class Node{
    int v;
    int i;

    Node(int v,int i){
        this.v = v;
        this.i = i;
    }
}
