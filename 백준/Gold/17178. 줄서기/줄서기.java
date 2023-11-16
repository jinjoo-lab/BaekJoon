import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static Stack<Node> stack = new Stack<>();
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Node[] board = new Node[n*5];
        int idx = 0;

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0;j<5;j++){
                StringTokenizer tmp = new StringTokenizer(st.nextToken(),"-");

                char a = tmp.nextToken().charAt(0);
                int num = Integer.parseInt(tmp.nextToken());

                board[idx] = new Node(a,num);
                q.add(new Node(a,num));
                idx += 1;
            }
        }

        Arrays.sort(board,(x,y) ->{
            if(x.a == y.a){
                return x.num - y.num;
            }

            return x.a - y.a;
        });

        int cur = 0;

        while(cur < 5*n){
            boolean find = false;

            if(!stack.isEmpty()){
                if(stack.peek().a == board[cur].a && stack.peek().num == board[cur].num){
                    stack.pop();
                    find = true;
                }
            }

            if(!find) {
                while (!q.isEmpty()) {
                    Node tmp = q.poll();

                    if (tmp.a != board[cur].a || tmp.num != board[cur].num) {
                        stack.push(tmp);
                    } else {
                        break;
                    }
                }
            }

            cur += 1;
        }

        if(stack.isEmpty() && q.isEmpty()){
            System.out.println("GOOD");
        } else{
            System.out.println("BAD");
        }

        br.close();
    }


}
class Node{
    char a;
    int num;

    Node(char a, int num){
        this.a = a;
        this.num = num;
    }

}
