import com.typesafe.scalalogging.LazyLogging

object DriverApp extends App with LazyLogging {
  val sender = new Sender()
  // Pass "Hello" message to the queue
  sender.sendMessage(sender.initializeConnection(), "Hello!")

}
