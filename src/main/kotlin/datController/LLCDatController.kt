package datController

import arrayBlocks.IArrayBlock
import arrayBlocks.LLCDataArray
import clockModule.GlobalClock
import java.math.BigInteger

class Pdat(
    
)

class LLCDatController(connectedArrays : MutableMap<BigInteger,IArrayBlock>) {
    val llcDat = LLCDataArray()
    fun pdat() {
        llcDat[BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO]
        GlobalClock.pulseClock(3)
    }
}