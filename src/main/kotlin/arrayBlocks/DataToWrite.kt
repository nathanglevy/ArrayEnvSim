package arrayBlocks

import java.math.BigInteger

data class DataToWrite(val set : Int, val way : Int, val block : Int, val dwordCell : Int, val data : BigInteger, var timeToWrite : Int) {
    fun cycle() { timeToWrite -= 1 }
}