import java.io.*;
import java.util.*;

public class Main {

    static int t = 0;
    static int n = 0;
    static int m = 0;

    static Node[] nodes;

    static ArrayList<Integer> ancestorA;
    static ArrayList<Integer> ancestorB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            nodes = new Node[n+1];

            for(int i=1;i<=n;i++){
                nodes[i] = new Node();
            }

            for(int i=1;i<=n-1;i++){
                st = new StringTokenizer(br.readLine(), " ");

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                nodes[child].parent = parent;
            }

            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            ancestorA = new ArrayList<>();
            ancestorB = new ArrayList<>();

            traverse(A,ancestorA);
            traverse(B,ancestorB);


            int result = A;

            for(int i=0 ;i < ancestorA.size(); i++){

                if(ancestorB.size() < i + 1)
                    break;

                if(!ancestorA.get(i).equals(ancestorB.get(i)))
                    break;

                result = ancestorA.get(i);
            }

            sb.append(result+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void traverse(int cur, ArrayList<Integer> ancestor){
        int parent = nodes[cur].parent;

        if(parent != 0){
            traverse(parent, ancestor);
        }

        ancestor.add(cur);
    }
}
class Node{
    int parent;

    Node(){
        this.parent = 0;
    }
}
