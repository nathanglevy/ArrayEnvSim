package bitExtensions

import java.math.BigInteger

operator fun BigInteger.get(range : IntRange) : BigInteger {
    val shifted = this.shiftRight(range.first)
    val mask = BigInteger("1".repeat(range.last - range.first),2)
    return shifted.and(mask)
}

operator fun BigInteger.get(range : Int) : BigInteger {
    return if (this.testBit(range)) BigInteger.ONE else BigInteger.ZERO
}

val Int.bigInt : BigInteger
    get() { return this.toBigInteger() }