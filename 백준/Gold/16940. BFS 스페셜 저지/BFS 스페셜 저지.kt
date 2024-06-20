import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.HashSet
import kotlin.math.max


var n : Int = 0
lateinit var graph : Array<HashSet<Int>>
lateinit var st : StringTokenizer
lateinit var v : Array<Int>
lateinit var result : Array<Int>

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))
    n =  br.readLine().toInt()

    graph = Array(n+1){HashSet<Int>()}

    for(i in 1..n-1) {
        st = StringTokenizer(br.readLine()," ")

        var v1 = st.nextToken().toInt()
        var v2 = st.nextToken().toInt()

        graph[v1].add(v2)
        graph[v2].add(v1)
    }

    st = StringTokenizer(br.readLine()," ")
    result = Array(n+1){0}

    for(i in 1..n) {
        result[i] = st.nextToken().toInt()
    }

    if(result[1] != 1) {
        println(0)
    }else {
        println(bfs())
    }

    br.close()
}

fun bfs() : Int {
    var idx = 2
    var q = ArrayDeque<Int>()
    v = Array<Int>(n+1) {0}
    q.add(1)
    v[1] = 1

    while(q.isNotEmpty()) {
        var cur = q.poll()

        while(idx <= n) {
            if(v[result[idx]] != 0)
                continue;

            if(graph[cur].contains(result[idx])) {
                v[result[idx]] = v[cur] + 1
                q.add(result[idx])
            }else {
                break
            }

            idx += 1;
        }
    }

    for(i in 1..n) {
        if(v[i] == 0)
            return 0
    }

    return 1
}

