package clockModule

interface IClockProducer {
    fun connect(connectable : IClockConsumer)
}