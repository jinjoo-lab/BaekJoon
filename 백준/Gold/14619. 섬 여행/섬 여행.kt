import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder


var n : Int = 0
var m : Int = 0
var t : Int = 0
lateinit var h : Array<Int>
lateinit var graph : Array<ArrayList<Int>>
lateinit var dis : Array<Array<Int>>

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")
    n = Integer.parseInt(st.nextToken())
    m = Integer.parseInt(st.nextToken())

    h = Array(101){0}
    dis = Array(101){Array<Int>(501){Int.MAX_VALUE} }
    graph = Array(101){ArrayList<Int>()}

    st = StringTokenizer(br.readLine(), " ")
    for(i in 1..n) {
        h[i] = st.nextToken().toInt()
    }

    for(i in 1..m) {
        st = StringTokenizer(br.readLine(), " ")
        var v1 = Integer.parseInt(st.nextToken())
        var v2 = Integer.parseInt(st.nextToken())

        graph[v1].add(v2)
        graph[v2].add(v1)
    }

    for(i in 1..n) {
        search(i,500)
    }

    st = StringTokenizer(br.readLine(), " ")
    t = st.nextToken().toInt()
    var sb = StringBuilder()
    for(i in 1..t) {
        st = StringTokenizer(br.readLine(), " ")
        var v1 = st.nextToken().toInt()
        var c = st.nextToken().toInt()

        var result = dis[v1][c]
        if(result == Int.MAX_VALUE)
            result = -1

        sb.append(result)

        if(i != t)
            sb.append("\n")
    }

    print(sb)
    br.close()
}

fun search(idx : Int, turn : Int) : Int{
    if(turn == 0)
        return h[idx]

    if(dis[idx][turn] != Int.MAX_VALUE)
        return dis[idx][turn]

    for(next in graph[idx]) {
        dis[idx][turn] = Math.min(dis[idx][turn], search(next,turn - 1))
    }

    return dis[idx][turn]
}

