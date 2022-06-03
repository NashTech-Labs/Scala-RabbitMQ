import com.rabbitmq.client.Connection
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Try

class RabbitMQConnectionTest extends AnyFlatSpec {
  "Rabbit MQ Connection" should "be established" in {
    val connection: Try[Connection] = RabbitMQConnection.getConnection
    assert(connection.isSuccess)
  }
}
