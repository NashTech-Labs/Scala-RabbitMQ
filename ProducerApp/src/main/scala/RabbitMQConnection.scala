import com.rabbitmq.client.{Connection, ConnectionFactory}

import scala.util.Try



object RabbitMQConnection {
  def getConnection: Try[Connection] = Try {
    val connectionFactory: ConnectionFactory = new ConnectionFactory()
    connectionFactory.setUsername("admin")
    connectionFactory.setPassword("password")
    connectionFactory.newConnection()
  }
}
