import com.rabbitmq.client.{Channel, ConnectionFactory}
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Try

class SenderIntegrationTest extends AnyFlatSpec {

  def initializeConnectionFactory(): ConnectionFactory = {
    val connectionFactory: ConnectionFactory = new ConnectionFactory()
    connectionFactory.setUsername("admin")
    connectionFactory.setPassword("password")
    connectionFactory
  }

  "initializeConnection" should "successfully establish connection channel" in {
    val sender: Sender = new Sender
    val channel: Try[Channel] = sender.initializeConnection()
    assert(channel.isSuccess)
  }

  "sendMessage" should "successfully send the message" in {
    val sender: Sender = new Sender
    val channel: Try[Channel] = sender.initializeConnection()
    val testMessage = "Test Message!"
    val sentMessage = sender.sendMessage(channel, testMessage)
    assertResult(testMessage)(sentMessage)
  }

}
