
import java.util.*;
import java.io.*;

public class Main {

    static int t = 0;
    static int n = 0;

    static int[] pre;
    static int[] num;
    static int[] in;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
        StringBuilder result = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine().trim()," ");
            n = Integer.parseInt(st.nextToken());
            sb = new StringBuilder();

            pre = new int[1001];
            in = new int[1001];
            num = new int[1001];

            st = new StringTokenizer(br.readLine().trim()," ");
            for(int i = 1 ; i <= n ; i++){
                pre[i] = Integer.parseInt(st.nextToken());
                num[pre[i]] = i;
            }

            st = new StringTokenizer(br.readLine().trim()," ");
            for(int i = 1 ; i <= n ; i++){
                in[i] = Integer.parseInt(st.nextToken());
            }

            TreeNode root = new TreeNode();
            makeTree(1,n,root);
            postOrder(root);

            sb.append("\n");

            result.append(sb.toString());
        }


        System.out.print(result);
        br.close();
    }

    static void postOrder(TreeNode node){
        if (node != null) {

            // 왼쪽 서브트리를 순회
            postOrder(node.left);

            // 오른쪽 서브트리를 순회
            postOrder(node.right);

            if(node.v != 0){
                sb.append(node.v+" ");
            }
        }
    }

    static void makeTree(int l,int r,TreeNode root) {

        if(l > r) {
            return;
        }

        int fIdx = l;
        int fNum = num[in[l]];

        for(int i = l ; i <= r ; i++){
            if(fNum > num[in[i]]){
                fNum = num[in[i]];
                fIdx = i;
            }
        }

        root.v = in[fIdx];
        root.left = new TreeNode();
        root.right = new TreeNode();

        makeTree(l,fIdx-1,root.left);
        makeTree(fIdx+1,r,root.right);
    }

    static class TreeNode{
        int v;
        TreeNode left;
        TreeNode right;

        TreeNode(){}

        TreeNode(int v) {
            this.v = v;
        }
    }
}
