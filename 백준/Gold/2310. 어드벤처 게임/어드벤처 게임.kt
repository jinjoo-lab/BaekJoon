import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

var n = 0
val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var st : StringTokenizer
lateinit var room : Array<Room>
lateinit var graph : Array<ArrayList<Int>>
fun main() {

    var sb = StringBuilder()

    while(init()) {
        room = Array(n+1){Room('E',0)}
        graph = Array(n+1){ArrayList<Int>()}

        for(i in 1..n) {
            st = StringTokenizer(br.readLine()," ")

            var w = st.nextToken()[0]
            var v = st.nextToken().toInt()

            room[i] = Room(w,v)

            while(true) {
                var nextV = st.nextToken().toInt()

                if(nextV == 0)
                    break

                graph[i].add(nextV)
            }
        }

        var tmp = if(search()) "Yes" else "No"
        sb.append(tmp).append("\n")
    }

    print(sb)

}

fun search() : Boolean{
    if(room[1].w == 'T')
        return false

    var visit = Array<Int>(n+1) {-1}

    var pq : PriorityQueue<MyNode> = PriorityQueue(
        {x,y -> y.v - x.v}
    )

    visit[1] = 0
    pq.add(MyNode(1,0 + if(room[1].w == 'E') room[1].v else 0))

    while(!pq.isEmpty()) {
        var cur = pq.poll()

        if(cur.v == n) {
            return true
        }

        for(next in graph[cur.v]) {
            if(room[next].w == 'E') {
                if(visit[next] < cur.c) {
                    visit[next] = cur.c
                    pq.add(MyNode(next,visit[next]))
                }
            }else if(room[next].w == 'L') {
                var nextC = if(cur.c > room[next].v) cur.c else room[next].v

                if(visit[next] < nextC) {
                    visit[next] = nextC
                    pq.add(MyNode(next,nextC))
                }
            } else {
                var nextC = cur.c - room[next].v

                if(nextC >= 0 && visit[next] < nextC) {
                    visit[next] = nextC
                    pq.add(MyNode(next,nextC))
                }
            }
        }
    }

    return false
}
data class MyNode (var v : Int, var c : Int)
data class Room (var w : Char, var v : Int)

fun init() : Boolean {
    n = br.readLine().toInt()

    if(n == 0)
        return false
    return true
}
