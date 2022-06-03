import com.typesafe.scalalogging.LazyLogging

import scala.io.StdIn

object DriverApp extends App with LazyLogging {
  val receiver = new Receiver()
  receiver.consumeMessage(receiver.initializeConnection())
  StdIn.readLine()
  logger.info("Exiting....")
}
