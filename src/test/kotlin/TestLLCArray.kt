import arrayBlocks.LLCDataArray
import bitExtensions.bigInt
import clockModule.GlobalClock
import org.junit.Assert.assertEquals
import org.junit.Test

class TestLLCArray {
    @Test
    fun testLLCArrayWriteRead_Wait4CyclesExpectCorrectResult() {
        val arrayUnderTest = LLCDataArray()
        arrayUnderTest[0,0,0] = 3
        GlobalClock.pulseClock(4)
        assertEquals(3,arrayUnderTest[0,0,0])
    }

    @Test
    fun testLLCArrayWriteRead_MakeSureValueDoesNotChangeBefore4Cycles() {
        val arrayUnderTest = LLCDataArray()
        arrayUnderTest[0,0,0] = 3
        GlobalClock.pulseClock(4)
        arrayUnderTest[0,0,0] = 2
        repeat(4) {
            assertEquals(3,arrayUnderTest[0,0,0])
            GlobalClock.pulseClock(1)
        }
        assertEquals(2,arrayUnderTest[0,0,0])
    }
}