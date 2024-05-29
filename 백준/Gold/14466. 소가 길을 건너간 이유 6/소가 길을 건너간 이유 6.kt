import java.util.*
import java.io.*
import kotlin.collections.ArrayList


var n = 0
var k = 0
var r = 0
var dx = listOf(0, 0, -1, 1)
var dy = listOf(1, -1, 0, 0)
var graph = Array(101) { Array(101) { ArrayList<NodeF>() } }

var cow = Array(101) { IntArray(3) }

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")

    n = Integer.parseInt(st.nextToken())
    k = Integer.parseInt(st.nextToken())
    r = Integer.parseInt(st.nextToken())

    for (i in 1..r) {
        st = StringTokenizer(br.readLine(), " ")

        var x1 = Integer.parseInt(st.nextToken())
        var y1 = Integer.parseInt(st.nextToken())
        var x2 = Integer.parseInt(st.nextToken())
        var y2 = Integer.parseInt(st.nextToken())

        graph[x1][y1].add(NodeF(x2, y2))
        graph[x2][y2].add(NodeF(x1, y1))
    }

    for (i in 1..k) {
        st = StringTokenizer(br.readLine(), " ")
        var x = Integer.parseInt(st.nextToken())
        var y = Integer.parseInt(st.nextToken())

        cow[i][0] = x
        cow[i][1] = y
    }

    var result = 0

    for (i in 1..k) {
        result += find(i)
    }

    result /= 2

    println(result)

    br.close()
}

fun find(idx: Int): Int {
    var v = Array(101) { BooleanArray(101) }
    var sX = cow[idx][0]
    var sY = cow[idx][1]

    v[sX][sY] = true
    var q = ArrayDeque<NodeF>()
    q.add(NodeF(sX, sY))

    while (!q.isEmpty()) {
        var cur = q.poll()

        for (i in 0..3) {
            var nX = cur.x!! + dx[i]
            var nY = cur.y!! + dy[i]

            if (nX < 1 || nX > n || nY < 1 || nY > n)
                continue

            if (v[nX][nY])
                continue

            var find = false

            for (next in graph[cur.x!!][cur.y!!]) {
                if (next.x!! == nX && next.y!! == nY) {
                    find = true
                    break;
                }
            }

            if (find)
                continue

            v[nX][nY] = true
            q.add(NodeF(nX, nY))
        }
    }

    var count = 0

    for (i in 1..k) {
        if (i == idx)
            continue

        if (!v[cow[i][0]][cow[i][1]]) {
            count++
        }
    }


    return count
}

class NodeF {
    var x: Int? = null
    var y: Int? = null

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}

