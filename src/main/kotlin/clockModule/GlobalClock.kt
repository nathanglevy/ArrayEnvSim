package clockModule

object GlobalClock : IClockProducer {
    private val connectedItems  = mutableListOf<IClockConsumer>()
    override fun connect(connectable : IClockConsumer) {
        connectedItems.add(connectable)
    }
    fun pulseClock(cycles : Int = 1) {
        repeat(cycles) {
            connectedItems.forEach { it.clock() }
        }
    }
}

