import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static Node[] tree= new Node[27];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            char p = st.nextToken().charAt(0);

            int idx = p - 'A' + 1;

            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);

            tree[idx] = new Node(l,r);
        }

        StringBuilder sb = new StringBuilder();
        preOrder(sb,1);
        System.out.println(sb);
        sb = new StringBuilder();
        inOrder(sb,1);
        System.out.println(sb);
        sb = new StringBuilder();
        postOrder(sb,1);
        System.out.println(sb);
        br.close();
    }

    static void preOrder(StringBuilder sb,int idx)
    {
        if(tree[idx] == null)
            return;

        char p = (char)('A' + idx - 1);
        sb.append(p);

        if(tree[idx].l != '.')
            preOrder(sb,tree[idx].l - 'A' + 1);

        if(tree[idx].r != '.')
            preOrder(sb,tree[idx].r - 'A' + 1);
    }

    static void inOrder(StringBuilder sb,int idx)
    {
        if(tree[idx] == null)
            return;

        if(tree[idx].l != '.')
            inOrder(sb,tree[idx].l - 'A' + 1);

        char p = (char)('A' + idx - 1);
        sb.append(p);

        if(tree[idx].r != '.')
            inOrder(sb,tree[idx].r - 'A' + 1);
    }

    static void postOrder(StringBuilder sb,int idx)
    {
        if(tree[idx] == null)
            return;

        if(tree[idx].l != '.')
            postOrder(sb,tree[idx].l - 'A' + 1);

        if(tree[idx].r != '.')
            postOrder(sb,tree[idx].r - 'A' + 1);

        char p = (char)('A' + idx - 1);
        sb.append(p);
    }
}
class Node
{
    char l;
    char r;

    Node(char l, char r)
    {
        this.l = l;
        this.r = r;
    }
}