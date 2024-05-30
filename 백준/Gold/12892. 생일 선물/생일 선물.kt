import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.ArrayList


var n : Int = 0
var m : Long = 0

lateinit var arr : Array<Num>

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")
    n = Integer.parseInt(st.nextToken());
    var mString = st.nextToken()
    m = mString.toLong()

    arr = Array<Num>(n) {Num()}

    for(i in 0..n-1) {
        st = StringTokenizer(br.readLine(), " ")

        var tmpP = st.nextToken().toLong()
        var tmpV = st.nextToken().toLong()

        arr[i] = Num(tmpP,tmpV)
    }

    Arrays.sort(arr, { x : Num , y : Num -> x.p.compareTo(y.p)} )

    var l = 0
    var r = 1

    var tmpV : Long = arr[0].v
    var result : Long = arr[0].v

    while(l <= r && r < n) {
        var curP = arr[r].p
        var curV = arr[r].v

        if(curP - arr[l].p >= m) {
            tmpV -= arr[l].v
            l++
        }else {
            tmpV += curV
            result = Math.max(tmpV,result)
            r++
        }
    }

    result = Math.max(tmpV,result)
    println(result)


    br.close()
}

class Num {
    var p : Long = 0
    var v : Long = 0

    constructor(){

    }

    constructor(p : Long , v : Long) {
        this.p = p
        this.v = v
    }
}


