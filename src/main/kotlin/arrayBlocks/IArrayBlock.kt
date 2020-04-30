package arrayBlocks

import java.math.BigInteger

interface IArrayBlock {
    val name : String
    operator fun get(fastAddress: BigInteger, bank : BigInteger, dword : BigInteger): BigInteger
    operator fun set(fastAddress : BigInteger, bank : BigInteger, dword : BigInteger, value : BigInteger)
}