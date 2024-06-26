import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.ArrayList


var n : Int = 0
var s : Int = 0
var t : Int = 0

lateinit var graph : Array<ArrayList<Node>>

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")
    n = Integer.parseInt(st.nextToken())
    s = Integer.parseInt(st.nextToken())
    t = Integer.parseInt(st.nextToken())

    graph = Array(n + 1) {ArrayList<Node>()}

    for(i in 1..n-1) {
        st = StringTokenizer(br.readLine(), " ")
        var v1 = Integer.parseInt(st.nextToken())
        var v2 = Integer.parseInt(st.nextToken())
        var c = Integer.parseInt(st.nextToken())

        graph[v1].add(Node(v2,c))
        graph[v2].add(Node(v1,c))
    }

    var result = search();
    println(result)

    br.close()
}

fun search() : Int {
    var pq = PriorityQueue<Node>(
        {x : Node , y : Node -> x.c - y.c}
    )
    pq.add(Node(s,0))

    var dis = Array(n+1) {Int.MAX_VALUE}
    var max = Array(n + 1) {0}

    dis[s] = 0

    var result = 0

    while(!pq.isEmpty()) {
        var cur = pq.poll();

        if(cur.v == t) {
            result = dis[cur.v] - cur.max
            break
        }

        for(next in graph[cur.v]) {
            if(dis[next.v] > cur.c + next.c) {
                dis[next.v] = cur.c + next.c
                var tmp = Node(next.v,dis[next.v],Math.max(cur.max,next.c))
                pq.add(tmp)
            }
        }
    }


    return result
}

class Node {
    var v : Int = 0
    var c : Int = 0
    var max : Int = 0

    constructor(v : Int , c : Int) {
        this.v = v
        this.c = c
    }

    constructor(v : Int , c : Int , max : Int) {
        this.v = v
        this.c = c
        this.max = max
    }
}

