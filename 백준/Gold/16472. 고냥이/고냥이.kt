import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

var n = 0
val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var arr : CharArray
fun main() {
    n = br.readLine().toInt()

    arr = br.readLine().toString().toCharArray()

    var l = 0
    var r = 0
    var result = 0

    var map : HashMap<Char,Int> = HashMap()

    while (l <= r && r < arr.size) {

        var next : Char = arr[r]

        if(!map.containsKey(next)) {
            map.put(next,1)
        }else {
            map.put(next,map.get(next)!!.plus(1))
        }

        if(map.size <= n) {
            result = max(r - l + 1,result)
        }else {
            while(map.size > n) {
                if(map.containsKey(arr[l])) {
                    var nextV = map.get(arr[l])!!.minus(1)

                    if(nextV == 0){
                        map.remove(arr[l])
                    }else {
                        map.put(arr[l],nextV)
                    }

                    l++
                }
            }
        }

        r++
    }

    println(result)

}


