import com.rabbitmq.client.Channel
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Try

class ReceiverIntegrationTest extends AnyFlatSpec {
  "initializeConnection" should "successfully establish connection channel" in {
    val receiver: Receiver = new Receiver
    val channel: Try[Channel] = receiver.initializeConnection()
    assert(channel.isSuccess)
  }

  "consumeMessage" should "successfully consume message" in {
    val receiver: Receiver = new Receiver
    val channel: Try[Channel] = receiver.initializeConnection()
    val expectedConsumerTag: String = "localadminconsumer"
    val actualConsumerTag: String = receiver.consumeMessage(channel)
    assertResult(expectedConsumerTag)(actualConsumerTag)
  }

}
