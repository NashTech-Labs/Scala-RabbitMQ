import com.rabbitmq.client._
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

class Receiver extends LazyLogging {

  def initializeConnection(): Try[Channel] = {
    val rabbitMQConnection: Try[Connection] = RabbitMQConnection.getConnection()

    val connectionChannel: Try[Channel] = rabbitMQConnection match {
      case Failure(exception) =>
        logger.error("Receiver was unable tp establish connection with RabbitMQ")
        throw exception
      case Success(connection) => Try(connection.createChannel())
    }
    connectionChannel
  }

  val deliverCallback: DeliverCallback = (consumerTag: String, message: Delivery) => {
    val messageBody = new String(message.getBody, "UTF-8")
    logger.info(s"[x] Received '$messageBody'")
  }

  val cancelCallback: CancelCallback = (consumerTag: String) => logger.info("Cancelled")

  def consumeMessage(connectionChannel: Try[Channel]): String = {
    connectionChannel match {
      case Failure(exception) =>
        logger.error(exception.printStackTrace().toString)
        throw new Exception(exception.getMessage)
      case Success(channel) =>
        val configFactory: Config = ConfigFactory.load()
        val rabbitMQConfiguration = configFactory.getConfig("rabbit-mq-configuration")
        val queue = rabbitMQConfiguration.getString("QUEUE_NAME")
        channel.queueDeclare(queue, false, false, false, null)
        logger.info("[*] Waiting for message")
        channel.basicConsume("greet", true, "localadminconsumer", deliverCallback, cancelCallback)
    }
  }
}



