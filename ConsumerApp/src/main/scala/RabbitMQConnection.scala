import com.rabbitmq.client.{Connection, ConnectionFactory}

import scala.util.Try

class RabbitMQConnection {

}

object RabbitMQConnection {
  def getConnection: Try[Connection] = Try {
    val connectionFactory = new ConnectionFactory()
    connectionFactory.setUsername("admin")
    connectionFactory.setPassword("password")
    connectionFactory.newConnection()
  }
}
