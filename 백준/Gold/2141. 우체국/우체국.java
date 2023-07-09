
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long result=0;
    static Node node[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N= Integer.parseInt(st.nextToken());
        node = new Node[N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            long X = Long.parseLong(st.nextToken());
            long A = Long.parseLong(st.nextToken());
            node[i] = new Node(X,A);
            result +=A;
        }

        Arrays.sort(node); //입력값이 순서대로 들어온다는 보장 X

        long sum = 0;

        for(Node n : node) {//하나씩 인구 수 계산하여 중간 값과 가장 근접한 마을 찾아서 출력하기
            sum += n.A;
            if(sum >=(result+1)/2) {
                System.out.println(String.valueOf(n.X));
                break;
            }
        }


    }

    static class Node implements Comparable<Node>{
        long X;
        long A;
        public Node(long x, long a) {
            super();
            X = x;
            A = a;
        }


        @Override
        public int compareTo(Node o) {
            if(this.X == o.X) {
                //서로 거리가 같으면
                return (int) (this.A-o.A);
            }
            return (int) (this.X-o.X);
        }
    }
}
