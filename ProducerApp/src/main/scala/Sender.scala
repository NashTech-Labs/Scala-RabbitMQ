import com.rabbitmq.client.{Channel, Connection}
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

import java.nio.charset.StandardCharsets
import scala.util.{Failure, Success, Try}

class Sender extends LazyLogging {

  def initializeConnection(): Try[Channel] = {
    val rabbitMQConnection: Try[Connection] = RabbitMQConnection.getConnection
    val connectionChannel: Try[Channel] = rabbitMQConnection match {
      case Failure(exception) =>
        logger.error("Could not establish connection with RabbitMQ")
        throw new Exception(exception.getMessage)
      case Success(connection) => Try(connection.createChannel())
    }
    connectionChannel
  }

  def sendMessage(connectionChannel: Try[Channel], message: String): String =  {
    connectionChannel match {
      case Failure(exception) =>
        logger.error(exception.printStackTrace().toString)
        throw new Exception(exception.getMessage)
      case Success(channel) =>
        val config: Config = ConfigFactory.load()
        val queue = config.getString("QUEUE_NAME")
        channel.queueDeclare(queue, false, false, false, null)
        channel.basicPublish("", "greet", null, message.getBytes(StandardCharsets.UTF_8))
        logger.info(s"[X] Sent '$message'")
        //logger.info(s"[X] Sent 'Hello!'")
        channel.close()
        message
    }
  }
}
