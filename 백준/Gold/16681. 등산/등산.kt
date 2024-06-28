import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

var n = 0
var m = 0
var d = 0
var e = 0
val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var height : IntArray
lateinit var st : StringTokenizer
lateinit var graph : Array<ArrayList<Node>>
fun main() {
   st = StringTokenizer(br.readLine()," ")

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    d = st.nextToken().toInt()
    e = st.nextToken().toInt()

    height = IntArray(n+1){0}

    graph = Array(n+1){ArrayList<Node>()}

    st = StringTokenizer(br.readLine()," ")
    for(i in 1..n) {
        height[i] = st.nextToken().toInt()
    }

    for(i in 1..m) {
        st = StringTokenizer(br.readLine()," ")
        var v1 = st.nextToken().toInt()
        var v2 = st.nextToken().toInt()
        var c = st.nextToken().toLong()

        graph[v1].add(Node(v2,c))
        graph[v2].add(Node(v1,c))
    }

    var dis1 = search(1)
    var dis2 = goDown()

    var result = Long.MIN_VALUE

    for(i in 2..n -1) {
        if(dis1[i] != Long.MAX_VALUE && dis2[i] != Long.MAX_VALUE) {
            var tmpE = (height[i] * e)
            result = max(result,tmpE - (dis1[i] + dis2[i]))
        }
    }

    if(result == Long.MIN_VALUE) {
        println("Impossible")
    }else {
        println(result)
    }
}

fun goDown() : LongArray{
    var pq : PriorityQueue<Node> = PriorityQueue(
        {x,y -> x.c.compareTo(y.c)}
    )

    pq.add(Node(n,0))

    var dis : LongArray = LongArray(n+1){Long.MAX_VALUE}

    dis[n] = 0

    while(pq.isNotEmpty()) {
        var cur = pq.poll()

        for(next in graph[cur.v]) {
            if(height[next.v] <= height[cur.v])
                continue

            if(dis[next.v] > cur.c + (next.c * d)) {
                dis[next.v] = cur.c + (next.c * d)
                pq.add(Node(next.v,dis[next.v]))
            }
        }
    }

    return dis
}

fun print(arr : LongArray) {
    for(i in 1..n) {
        print(arr[i])
        print(" ")
    }
    println()
}

fun search(start : Int) : LongArray {
    var pq : PriorityQueue<Node> = PriorityQueue(
        {x,y -> x.c.compareTo(y.c)}
    )

    pq.add(Node(start,0))

    var dis : LongArray = LongArray(n+1){Long.MAX_VALUE}

    dis[start] = 0

    while(pq.isNotEmpty()) {
        var cur = pq.poll()

        if(dis[cur.v] < cur.c)
            continue

        for(next in graph[cur.v]) {
            if(height[next.v] <= height[cur.v])
                continue

            var nextD = next.c * d

            if(dis[next.v] > cur.c + (nextD)) {
                dis[next.v] = cur.c + (nextD)
                pq.add(Node(next.v,dis[next.v]))
            }
        }
    }

    return dis
}


data class Node(var v : Int, var c : Long)
