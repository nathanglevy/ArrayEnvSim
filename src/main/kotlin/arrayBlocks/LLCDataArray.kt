package arrayBlocks

import bitExtensions.bigInt
import bitExtensions.get
import clockModule.GlobalClock
import clockModule.IClockConsumer
import clockModule.IClockProducer
import java.math.BigInteger

class LLCDataArray(clock : IClockProducer = GlobalClock) : IArrayBlock, IClockConsumer {
    init { clock.connect(this) }
    private val writeDelay : Int = 4
    override val name: String = "LLC_DAT"
    private val dataMap  = Array(2) { Array(256) { Array(16) { Array(16) { BigInteger.ZERO }  } } }
    private val dataToWrite = mutableListOf<DataToWrite>()

    override fun clock() {
        dataToWrite.forEach { it.cycle() }
        val writeNow = dataToWrite.filter { it.timeToWrite <= 0 }
        writeNow.forEach { (block,set,way,dwordCell,data) ->
            dataMap[block][set][way][dwordCell] = data
        }
        dataToWrite.removeAll(writeNow)
    }

    operator fun get(fastAddress: Int, bank : Int, dword : Int): Int {
        return get(fastAddress.bigInt, bank.bigInt, dword.bigInt).toInt()
    }

    override operator fun get(fastAddress: BigInteger, bank : BigInteger, dword : BigInteger): BigInteger {
        val set = fastAddress[0..7].toInt()
        val way = fastAddress[8..11].toInt()
        val block = bank[0..3].toInt()
        val dwordCell = dword[0..3].toInt()
        return dataMap[block][set][way][dwordCell]
    }

    operator fun set(fastAddress: Int, bank : Int, dword : Int, value: Int) {
        set(fastAddress.bigInt, bank.bigInt, dword.bigInt, value.bigInt)
    }

    override fun set(fastAddress: BigInteger, bank : BigInteger, dword : BigInteger, value: BigInteger) {
        val set = fastAddress[0..7].toInt()
        val way = fastAddress[8..11].toInt()
        val block = bank[0..3].toInt()
        val dwordCell = dword[0..3].toInt()
//        dataMap[block][set][way][dwordCell] = value
        dataToWrite.add(DataToWrite(set,way,block,dwordCell,value,writeDelay))
    }

    fun reset() {
        dataMap.forEach { bankArray ->
            bankArray.forEach { rowArray ->
                rowArray.forEach { cacheLine ->
                    cacheLine.forEachIndexed { dword,_ ->
                        cacheLine[dword] = BigInteger.ZERO
                    }
                }
            }
        }
    }
}